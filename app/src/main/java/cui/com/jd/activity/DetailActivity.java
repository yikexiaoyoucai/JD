package cui.com.jd.activity;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;

import cui.com.jd.R;
import cui.com.jd.bean.Xq;
import cui.com.jd.presenter.Addpre;
import cui.com.jd.presenter.Xqpresenter;
import cui.com.jd.view.Xqview;

public class DetailActivity extends AppCompatActivity implements Xqview, View.OnClickListener {
    private int pid;
    private XBanner mBan2;
    private TextView mNameXq;
    private TextView mContentXq;
    private TextView mPrice1Xq;
    private TextView mOldmoneyDesc;
    private Button mGoodcarAdd;
    private PopupWindow pop;
    private SimpleDraweeView mImagePw;
    private TextView mTextPw;
    private TextView shangjia;
    private Button mPopAddAddcart;
    private TextView mPopGoodnumAddcart;
    private Button mPopJianAddcart;
    private Button mAddGwc;
    private View v;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        initView();
        pid = getIntent().getIntExtra("pid", 0);
        Log.i("pid==========", pid + "");
        Xqpresenter p = new Xqpresenter(pid + "", this, this);
        p.setdata();

    }

    private void initView() {
        v = View.inflate(DetailActivity.this, R.layout.poplayout, null);
        pop = new PopupWindow(v, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        mAddGwc =v.findViewById(R.id.gwc_add);
        mPopGoodnumAddcart =v.findViewById(R.id.addcart_pop_goodnum);
        mPopJianAddcart = v.findViewById(R.id.addcart_pop_jian);
        mImagePw =v.findViewById(R.id.pw_image);
        shangjia=v.findViewById(R.id.shangjia);
        mTextPw =v.findViewById(R.id.pw_text);
        mPopAddAddcart =v.findViewById(R.id.addcart_pop_add);
        mBan2 = (XBanner) findViewById(R.id.ban2);
        mNameXq = (TextView) findViewById(R.id.xq_name);
        mContentXq = (TextView) findViewById(R.id.xq_content);
        mPrice1Xq = (TextView) findViewById(R.id.xq_price1);
        mOldmoneyDesc = (TextView) findViewById(R.id.desc_oldmoney);
        mGoodcarAdd = (Button) findViewById(R.id.add_goodcar);
        mGoodcarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    showpop();

            }
        });
    }

    @Override
    public void setXq(Xq xq) {
        String[] s = xq.getData().getImages().split("\\|");
        final ArrayList<String> imgs = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            imgs.add(s[i]);
        }
        mBan2.setData(imgs);
        mBan2.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Picasso.with(DetailActivity.this).load(imgs.get(position)).into((ImageView) view);
            }
        });
        mNameXq.setText(xq.getData().getTitle());
        mContentXq.setText(xq.getData().getSubhead());
        mPrice1Xq.setText(xq.getData().getPrice() + "");
        mOldmoneyDesc.setText(xq.getData().getBargainPrice() + "");
        mImagePw.setImageURI(Uri.parse(imgs.get(0)));
        mTextPw.setText(xq.getData().getTitle());
        shangjia.setText(xq.getSeller().getName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addcart_pop_add:// TODO 17/12/16
                int num = Integer.parseInt(mPopGoodnumAddcart.getText().toString());
                num++;
                mPopGoodnumAddcart.setText(num+"");
                break;
            case R.id.addcart_pop_jian:// TODO 17/12/16
                int num1=Integer.parseInt(mPopGoodnumAddcart.getText().toString());
                num1--;
                if(num1<1)
                {
                    Toast.makeText(getApplicationContext(),"已经减到0了",Toast.LENGTH_SHORT).show();
                    return;
                }
                mPopGoodnumAddcart.setText(num1+"");
            case R.id.gwc_add:// TODO 17/12/16
                sp=getSharedPreferences("info",MODE_PRIVATE);
                Log.i("uid=====",sp.getInt("uid",0)+"");
                Log.i("token=====",sp.getString("token",null));
                String uid=sp.getInt("uid",0)+"";
                String token=sp.getString("token",null);
                Addpre p=new Addpre(this,uid,pid+"",token);
                p.add();
                break;
            default:
                break;
        }
    }

    private void showpop() {

        //设置背景
        pop.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        //设置焦点
        pop.setFocusable(true);
        //可触摸
        pop.setTouchable(true);
        pop.setOnDismissListener(new poponDismissListener());
        mPopAddAddcart.setOnClickListener(this);
        mPopJianAddcart.setOnClickListener(this);
        mAddGwc.setOnClickListener(this);
        backgroundAlpha(0.5f);
        pop.showAsDropDown(mGoodcarAdd, 0, -350);
    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }
}
