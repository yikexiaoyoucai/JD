package cui.com.jd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cui.com.jd.R;
import cui.com.jd.adp.Rv_adp4;
import cui.com.jd.bean.Baba;
import cui.com.jd.presenter.Babaresenter;
import cui.com.jd.view.Babaview;

/**
 * Created by ZMoffice on 2017.12.14.
 */

public class Right extends Fragment implements Babaview{
    private RecyclerView mFlRv;
    int cid=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.right, null);
        mFlRv = v.findViewById(R.id.rv_fl);
        cid=getArguments().getInt("cid");
        Log.i("===cid",cid+"");
        Babaresenter p=new Babaresenter(this,getActivity(),cid+"");
        p.getdata();
        return v;
    }

    @Override
    public void getbaba(List<Baba.DataBean> list) {
        LinearLayoutManager l=new LinearLayoutManager(getActivity());
        mFlRv.setLayoutManager(l);
        Rv_adp4 adp=new Rv_adp4(list,getActivity());
        mFlRv.setAdapter(adp);
    }
}
