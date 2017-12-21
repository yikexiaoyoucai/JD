package cui.com.jd.model;

import android.util.Log;

import cui.com.jd.api.Api;
import cui.com.jd.bean.Baba;
import cui.com.jd.bean.Xq;
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
 * Created by ZMoffice on 2017.12.15.
 */

public class Shixianxq implements Xqmodel{
    String pid;
Xq xq;
    public Shixianxq(String pid) {
        this.pid = pid;
    }

    @Override
    public void getxq(Observer<Xq> o) {
        Observable.create(new Observable.OnSubscribe<Xq>() {
            @Override
            public void call(final Subscriber<? super Xq> s) {
                Retrofit retrofit=new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .baseUrl("https://www.zhaoapi.cn/")
                        .build();
                Api api=retrofit.create(Api.class);
                Call<Xq> call=api.getxq(pid);
                call.enqueue(new Callback<Xq>() {
                    @Override
                    public void onResponse(Call<Xq> call, Response<Xq> response) {
                        xq=response.body();
                        s.onNext(xq);
                    }

                    @Override
                    public void onFailure(Call<Xq> call, Throwable t) {
                        System.out.println("连接失败");
                    }
                });
            }

        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(o);
    }
}
