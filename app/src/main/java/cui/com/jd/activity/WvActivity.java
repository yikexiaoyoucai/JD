package cui.com.jd.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import cui.com.jd.R;

public class WvActivity extends AppCompatActivity {

    private WebView mWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wv);
        getSupportActionBar().hide();
        initView();
        mWv= (WebView) findViewById(R.id.wv);
        String s=getIntent().getStringExtra("url");
        String url=s.substring(2,s.length());
        Log.i("uyuyuy",url);
        mWv.loadUrl(url);

    }

    private void initView() {
        mWv = (WebView) findViewById(R.id.wv);
    }
}
