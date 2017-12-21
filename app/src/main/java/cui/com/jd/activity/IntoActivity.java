package cui.com.jd.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cui.com.jd.R;

public class IntoActivity extends AppCompatActivity {
   Handler handler=new Handler()
   {
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           if(msg.what==100)
           {
               Intent in=new Intent(IntoActivity.this,MainActivity.class);
               startActivity(in);
           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_into);
        handler.sendEmptyMessageDelayed(100,1000);
    }
}
