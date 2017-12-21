package cui.com.jd.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import cui.com.jd.R;
import cui.com.jd.adp.Expadp;
import cui.com.jd.bean.Cxgwc;
import cui.com.jd.presenter.Cxpre;
import cui.com.jd.view.Cxview;
import cui.com.jd.zhifu.PayDemoActivity;

/**
 * Created by ZMoffice on 2017.12.12.
 */

public class F4 extends Fragment implements Cxview, View.OnClickListener {
    private ExpandableListView listview;
    private Expadp adpater;
    private TextView checked_shop;
    private TextView price;
    private TextView pay;
    CheckBox checkAll;
    SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=View.inflate(getActivity(), R.layout.f4,null);
        sp=getActivity().getSharedPreferences("info",getActivity().MODE_PRIVATE);
        String uid=sp.getInt("uid",0)+"";
        String token=sp.getString("token",null);
        Cxpre p=new Cxpre(getActivity(),this,uid,token);
        p.cx();
        listview = v.findViewById(R.id.listview);
        checkAll =v.findViewById(R.id.checkAll);
        price =v.findViewById(R.id.price);
        checked_shop =v.findViewById(R.id.checked_shop);
        pay=v.findViewById(R.id.pay);
        pay.setOnClickListener(this);
        return v;
    }

    @Override
    public void getcx(Cxgwc o) {
        adpater = new Expadp(getActivity(),o);
        listview.setAdapter(adpater);
        adpater.getAdapterData(new Expadp.AdapterData() {
            @Override
            public void Data(View v, String str, boolean b) {
                String[] split = str.split(",");
                price.setText(split[0]);
                checked_shop.setText(split[1]);
                checkAll.setChecked(b);
            }
        });

        checkAll.setChecked(adpater.selectAll());
        adpater.notifyDataSetChanged();
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置商品全部选中
                adpater.checkAllShop(checkAll.isChecked());
                //计算选中的价格和数量
                String shopPrice = adpater.getShopPrice();
                //判断商品是否全部选中
                boolean b = adpater.selectAll();

                String[] split = shopPrice.split(",");
                price.setText(split[0]);
                checked_shop.setText(split[1]);
                checkAll.setChecked(b);
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity(), PayDemoActivity.class);
        startActivity(intent);
    }
}
