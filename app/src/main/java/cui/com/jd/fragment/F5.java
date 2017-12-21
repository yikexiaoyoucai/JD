package cui.com.jd.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cui.com.jd.R;
import cui.com.jd.activity.LoginActivity;

/**
 * Created by ZMoffice on 2017.12.12.
 */

public class F5 extends Fragment implements View.OnClickListener {
    private ImageView mIv;
    private TextView mLogin;
    private ImageView mDingdan;
    private TextView mUsername;
    SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.f5, null);
        initView(v);
        sp=getActivity().getSharedPreferences("info",getActivity().MODE_PRIVATE);
        if(sp.getBoolean("isone",false)==true)
        {
            Toast.makeText(getActivity(),"您已经登陆了",Toast.LENGTH_SHORT).show();
            mUsername.setText(sp.getString("username",null));
        }

        return v;
    }

    private void initView(View v) {
        mIv = (ImageView) v.findViewById(R.id.iv);
        mIv.setOnClickListener(this);
        mLogin = (TextView) v.findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mDingdan = (ImageView) v.findViewById(R.id.dingdan);
        mDingdan.setOnClickListener(this);
        mUsername = (TextView) v.findViewById(R.id.username);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv:

                break;
            case R.id.login:
                if(sp.getBoolean("isone",false)!=true)
                {
                    Intent in = new Intent(getActivity(), LoginActivity.class);
                    startActivity(in);
                }
                break;
            case R.id.dingdan:
                // TODO 17/12/18
                break;
            default:
                break;
        }
    }
}
