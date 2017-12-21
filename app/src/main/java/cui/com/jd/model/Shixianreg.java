package cui.com.jd.model;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import cui.com.jd.api.Api;
import cui.com.jd.bean.Dlbean;
import cui.com.jd.bean.Regbean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZMoffice on 2017.12.16.
 */

public class Shixianreg implements Regmodel {
    String mobile;
    String pwd;
    Regbean b;
    public Shixianreg(String mobile, String pwd) {
        this.mobile = mobile;
        this.pwd = pwd;
    }
    @Override
    public void getdata(Observer<Regbean> o) {
        Observable.create(new Observable.OnSubscribe<Regbean>() {
            @Override
            public void call(final Subscriber<? super Regbean> s) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://www.zhaoapi.cn/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api=retrofit.create(Api.class);
                Call<Regbean> call=api.getreg(mobile,pwd);
                call.enqueue(new Callback<Regbean>() {
                    @Override
                    public void onResponse(Call<Regbean> call, Response<Regbean> response) {
                        b=response.body();
                        s.onNext(b);
                        Log.i("bean=============",b.toString());
                    }

                    @Override
                    public void onFailure(Call<Regbean> call, Throwable t) {
                        System.out.println("连接失败");
                    }
                });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(o);
    }
}
