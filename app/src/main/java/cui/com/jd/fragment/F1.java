package cui.com.jd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import cui.com.jd.R;
import cui.com.jd.activity.DetailActivity;
import cui.com.jd.activity.SelActivity;
import cui.com.jd.adp.Gv1adp;
import cui.com.jd.adp.Rv_adp1;
import cui.com.jd.adp.Rv_adp2;
import cui.com.jd.bean.Bean;
import cui.com.jd.bean.Fl;
import cui.com.jd.presenter.Flpresenter;
import cui.com.jd.presenter.Mpresenter;
import cui.com.jd.view.Flview;
import cui.com.jd.view.Mview;

/**
 * Created by ZMoffice on 2017.12.12.
 */

public class F1 extends Fragment implements Mview, View.OnClickListener,Flview {
    private View v;
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;

    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                if (mHour < 10) {
                    mHourTv.setText("0" + mHour + "");
                } else {
                    mHourTv.setText("0" + mHour + "");
                }
                if (mMin < 10) {
                    mMinuteTv.setText("0" + mMin + "");
                } else {
                    mMinuteTv.setText(mMin + "");
                }
                if (mSecond < 10) {
                    mSecondTv.setText("0" + mSecond + "");
                } else {
                    mSecondTv.setText(mSecond + "");
                }
            }
        }
    };
    private ImageView mSysHead;
    private RelativeLayout mSysRl;
    private EditText mSouHead;
    private RelativeLayout mRl;
    private ImageView mXiaoHead;
    private LinearLayout mLll;
    private XBanner mBan;
    private GridView mGv1;
    private ImageView mMiaosha;
    private TextView mJidianTv;
    private TextView mHourTv;
    private TextView mMinuteTv;
    private TextView mSecondTv;
    private LinearLayout mJdLl;
    private RecyclerView mShouye1Rv;
    private RecyclerView mShouye2Rv;
    private ScrollView mSr;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = View.inflate(getActivity(), R.layout.f1, null);
        initView();
        Mpresenter p = new Mpresenter(this, getActivity());
        p.getdata();
        Flpresenter f=new Flpresenter(this,getActivity());
        f.getfl();
        startRun();
        return v;
    }

    private void initView() {
        mSysHead =v.findViewById(R.id.head_sys);
        mSysRl =v.findViewById(R.id.rl_sys);
        mSysRl.setOnClickListener(this);
        mSouHead = v.findViewById(R.id.head_sou);
        mSouHead.setOnClickListener(this);
        mRl = v.findViewById(R.id.rl);
        mXiaoHead =v.findViewById(R.id.head_xiao);
        mLll =v.findViewById(R.id.lll);
        mBan =v.findViewById(R.id.ban);
        mGv1 =v.findViewById(R.id.gv1);
        mMiaosha =v.findViewById(R.id.miaosha);
        mJidianTv =v.findViewById(R.id.tv_jidian);
        mHourTv =v.findViewById(R.id.tv_hour);
        mMinuteTv =v.findViewById(R.id.tv_minute);
        mSecondTv =v.findViewById(R.id.tv_second);
        mJdLl = v.findViewById(R.id.ll_jd);
        mShouye1Rv =v.findViewById(R.id.rv_shouye1);
        mShouye2Rv =v.findViewById(R.id.rv_shouye2);
        mSr = v.findViewById(R.id.sr);
    }

    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }

    @Override
    public void setban(Bean bean) {
        final List<String> imgs = new ArrayList<>();
        for (int i = 0; i < bean.getData().size(); i++) {
            imgs.add(bean.getData().get(i).getIcon());
        }
        mBan.setData(imgs);
        mBan.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Picasso.with(getActivity()).load(imgs.get(position)).into((ImageView) view);
            }
        });
    }

    @Override
    public void setbean(Bean bean) {
        LinearLayoutManager g = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        Rv_adp1 adp = new Rv_adp1(getActivity(), bean);
        mShouye1Rv.setLayoutManager(g);
        mShouye1Rv.setAdapter(adp);
    }

    @Override
    public void setbean2(final Bean bean) {
        GridLayoutManager g = new GridLayoutManager(getActivity(), 2);
        Rv_adp2 adp = new Rv_adp2(getActivity(), bean);
        mShouye2Rv.setLayoutManager(g);
        mShouye2Rv.setAdapter(adp);
        adp.setLi(new Rv_adp2.setlisten() {
            @Override
            public void setonclick(View v, int i) {
                Intent in = new Intent(getActivity(), DetailActivity.class);
                int pid = bean.getTuijian().getList().get(i).getPid();
                in.putExtra("pid", pid);
                startActivity(in);
            }
        });
    }

    @Override
    public void onClick(View view) {
        //扫一扫的点击事件
        switch (view.getId()) {
            case R.id.rl_sys:
                break;
            case R.id.head_sou:
                Intent in = new Intent(getActivity(), SelActivity.class);
                startActivity(in);
                break;
        }
    }

    @Override
    public void getfl(List<Fl.DataBean> list) {
        Gv1adp gv1adp=new Gv1adp(list,getActivity());
        mGv1.setAdapter(gv1adp);
    }
}
