package cui.com.jd.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import cui.com.jd.R;
import cui.com.jd.presenter.Dlpre;
import cui.com.jd.presenter.Regpre;
import cui.com.jd.view.Dlview;
import cui.com.jd.view.Regview;

public class LoginActivity extends AppCompatActivity implements Dlview, Regview, View.OnClickListener {

    private ImageView mTui;
    private EditText mMobileEt;
    private EditText mPwdEt;
    private Button mLoadBut;
    private Button mRegBut;
    String m;
    String p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        getSupportActionBar().hide();
    }

    private void initView() {
        mTui = (ImageView) findViewById(R.id.tui);
        mTui.setOnClickListener(this);
        mMobileEt = (EditText) findViewById(R.id.et_mobile);
        mPwdEt = (EditText) findViewById(R.id.et_pwd);
        mLoadBut = (Button) findViewById(R.id.but_load);
        mLoadBut.setOnClickListener(this);
        mRegBut = (Button) findViewById(R.id.but_reg);
        mRegBut.setOnClickListener(this);

    }

    @Override
    public void dl() {

    }

    @Override
    public void reg() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tui:
                // TODO 17/12/18
                break;
            case R.id.but_load:
                m=mMobileEt.getText().toString();
                p=mPwdEt.getText().toString();
                Log.i("m========",m);
                Dlpre d=new Dlpre(this,this,m,p);
                d.dl();
                break;
            case R.id.but_reg:
                m=mMobileEt.getText().toString();
                p=mPwdEt.getText().toString();
                Log.i("p========",p);
                Regpre r=new Regpre(this,this,m,p);
                r.reg();
                break;
            default:
                break;
        }
    }
}
