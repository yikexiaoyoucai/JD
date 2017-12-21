package cui.com.jd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.List;

import cui.com.jd.R;
import cui.com.jd.adp.Lvadp;
import cui.com.jd.bean.Fl;
import cui.com.jd.presenter.Flpresenter;
import cui.com.jd.view.Flview;

/**
 * Created by ZMoffice on 2017.12.12.
 */

public class F2 extends Fragment implements Flview {
    private ListView mLv;
    private FrameLayout mFl2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.f2, null);
        mLv =v.findViewById(R.id.lv);
        mFl2 =v.findViewById(R.id.fl2);
        Flpresenter p=new Flpresenter(this,getActivity());
        p.getfl();
        return v;
    }


    @Override
    public void getfl(final List<Fl.DataBean> list) {
        Lvadp adp=new Lvadp(list,getActivity());
        mLv.setAdapter(adp);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Right f=new Right();
                Bundle b=new Bundle();
                b.putInt("cid",list.get(i).getCid());
                f.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl2,f).commit();
            }
        });

    }

}
