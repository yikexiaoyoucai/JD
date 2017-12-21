package cui.com.jd.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;

import cui.com.jd.R;
import cui.com.jd.fragment.F1;
import cui.com.jd.fragment.F2;
import cui.com.jd.fragment.F3;
import cui.com.jd.fragment.F4;
import cui.com.jd.fragment.F5;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FrameLayout mFl;
    private RadioButton mB1;
    private RadioButton mB2;
    private RadioButton mB3;
    private RadioButton mB4;
    private RadioButton mB5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();
        mB1.setChecked(true);
        mB2.setChecked(false);
        mB3.setChecked(false);
        mB4.setChecked(false);
        mB5.setChecked(false);
        mB1.setTextColor(Color.RED);
        mB2.setTextColor(Color.BLACK);
        mB3.setTextColor(Color.BLACK);
        mB4.setTextColor(Color.BLACK);
        mB5.setTextColor(Color.BLACK);
        F1 f1=new F1();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl,f1).commit();

    }

    private void initView() {
        mFl = (FrameLayout) findViewById(R.id.fl);
        mB1 = (RadioButton) findViewById(R.id.b1);
        mB1.setOnClickListener(this);
        mB2 = (RadioButton) findViewById(R.id.b2);
        mB2.setOnClickListener(this);
        mB3 = (RadioButton) findViewById(R.id.b3);
        mB3.setOnClickListener(this);
        mB4 = (RadioButton) findViewById(R.id.b4);
        mB4.setOnClickListener(this);
        mB5 = (RadioButton) findViewById(R.id.b5);
        mB5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                mB1.setChecked(true);
                mB2.setChecked(false);
                mB3.setChecked(false);
                mB4.setChecked(false);
                mB5.setChecked(false);
                mB1.setTextColor(Color.RED);
                mB2.setTextColor(Color.BLACK);
                mB3.setTextColor(Color.BLACK);
                mB4.setTextColor(Color.BLACK);
                mB5.setTextColor(Color.BLACK);
                F1 f1=new F1();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,f1).commit();
                break;
            case R.id.b2:
                mB2.setChecked(true);
                mB1.setChecked(false);
                mB3.setChecked(false);
                mB4.setChecked(false);
                mB5.setChecked(false);
                mB1.setTextColor(Color.BLACK);
                mB2.setTextColor(Color.RED);
                mB3.setTextColor(Color.BLACK);
                mB4.setTextColor(Color.BLACK);
                mB5.setTextColor(Color.BLACK);
                F2 f2=new F2();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,f2).commit();
                break;
            case R.id.b3:
                mB3.setChecked(true);
                mB2.setChecked(false);
                mB1.setChecked(false);
                mB4.setChecked(false);
                mB5.setChecked(false);
                mB1.setTextColor(Color.BLACK);
                mB2.setTextColor(Color.BLACK);
                mB3.setTextColor(Color.RED);
                mB4.setTextColor(Color.BLACK);
                mB5.setTextColor(Color.BLACK);
                F3 f3=new F3();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,f3).commit();
                break;
            case R.id.b4:
                mB4.setChecked(true);
                mB2.setChecked(false);
                mB3.setChecked(false);
                mB1.setChecked(false);
                mB5.setChecked(false);
                mB1.setTextColor(Color.BLACK);
                mB2.setTextColor(Color.BLACK);
                mB3.setTextColor(Color.BLACK);
                mB4.setTextColor(Color.RED);
                mB5.setTextColor(Color.BLACK);
                F4 f4=new F4();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,f4).commit();
                break;
            case R.id.b5:
                mB5.setChecked(true);
                mB2.setChecked(false);
                mB3.setChecked(false);
                mB4.setChecked(false);
                mB1.setChecked(false);
                mB1.setTextColor(Color.BLACK);
                mB2.setTextColor(Color.BLACK);
                mB3.setTextColor(Color.BLACK);
                mB4.setTextColor(Color.BLACK);
                mB5.setTextColor(Color.RED);
                F5 f5=new F5();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,f5).commit();
                break;
            default:
                break;
        }
    }
}
