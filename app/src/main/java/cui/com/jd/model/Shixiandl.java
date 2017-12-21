package cui.com.jd.model;

import android.util.Log;

import cui.com.jd.api.Api;
import cui.com.jd.bean.Bean;
import cui.com.jd.bean.Dlbean;
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

public class Shixiandl implements Dlmodel {
    String mobile;
    String pwd;

    public Shixiandl(String mobile, String pwd) {
        this.mobile = mobile;
        this.pwd = pwd;
    }

    Dlbean b;
    @Override
    public void getdata(Observer<Dlbean> o) {
        Observable.create(new Observable.OnSubscribe<Dlbean>() {
            @Override
            public void call(final Subscriber<? super Dlbean> s) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://www.zhaoapi.cn/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api=retrofit.create(Api.class);
                Call<Dlbean> call=api.getdl(mobile,pwd);
                call.enqueue(new Callback<Dlbean>() {
                    @Override
                    public void onResponse(Call<Dlbean> call, Response<Dlbean> response) {
                        b=response.body();
                        s.onNext(b);
                        Log.i("bean=============",b.toString());
                    }

                    @Override
                    public void onFailure(Call<Dlbean> call, Throwable t) {
                        System.out.println("连接失败");
                    }
                });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(o);
    }
}
