package cui.com.jd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;

import cui.com.jd.R;
import cui.com.jd.adp.Rv_adp3;
import cui.com.jd.bean.Sousuo;
import cui.com.jd.utils.OkHttp3Utils;
import cui.com.jd.utils.Sou_utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SSJGActivity extends AppCompatActivity{
    String name;
    private RecyclerView mRvSsjg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssjg);
        getSupportActionBar().hide();
        initView();
        GridLayoutManager g=new GridLayoutManager(this,2);
        mRvSsjg.setLayoutManager(g);
        name=getIntent().getStringExtra("name");
        String sname = Sou_utils.jiami(Sou_utils.convertStringToUTF8(name));
        String url = "http://list.mogujie.com/search?_version=61&ratio=3%3A4&q="+sname+"&cKey=46&minPrice=&_mgjuuid=dbcc6b5c-fcf7-49f4-b807-3e85fbcccc5b&ppath=&page=1&maxPrice=&sort=pop&userId=&cpc_offset=&priceList=&_=1504520539364&callback=jsonp1";
        OkHttp3Utils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful())
                {
                    String s=response.body().string();
                    final String result=s.substring(11, s.length() - 2);
                    System.out.println("商品json======="+s);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getjson2(result);
                        }
                    });
                }
            }
        });
}

    private void initView() {
        mRvSsjg =findViewById(R.id.ssjg_rv);
    }
    public void getjson2(String s)
    {
        Gson g=new Gson();
        final Sousuo l=g.fromJson(s,Sousuo.class);
        Rv_adp3 adp=new Rv_adp3(this,l);
        mRvSsjg.setAdapter(adp);
        adp.setLi(new Rv_adp3.setlisten() {
            @Override
            public void setonclick(View v, int i) {
                Intent in=new Intent(SSJGActivity.this,WvActivity.class);
                in.putExtra("url",l.getResult().getWall().getDocs().get(i).getLink());
                startActivity(in);
            }
        });
    }

}
