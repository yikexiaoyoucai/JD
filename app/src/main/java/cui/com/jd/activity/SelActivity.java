package cui.com.jd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import cui.com.jd.R;

public class SelActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mFanhui;
    private EditText mSouTwoHead;
    private ImageView mShousuo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel);
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        mFanhui = findViewById(R.id.fanhui);
        mFanhui.setOnClickListener(this);
        mSouTwoHead =findViewById(R.id.head_sou_two);
        mShousuo =findViewById(R.id.shousuo);
        mShousuo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                Intent in=new Intent(SelActivity.this,MainActivity.class);
                startActivity(in);
                break;
            case R.id.shousuo:
                Intent in2=new Intent(SelActivity.this,SSJGActivity.class);
                String name=mSouTwoHead.getText().toString();
                in2.putExtra("name",name);
                startActivity(in2);
                break;
            default:
                break;
        }
    }
}
