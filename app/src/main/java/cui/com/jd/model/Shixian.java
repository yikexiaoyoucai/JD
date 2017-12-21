package cui.com.jd.model;

import android.util.Log;

import cui.com.jd.api.Api;
import cui.com.jd.bean.Baba;
import cui.com.jd.bean.Bean;
import cui.com.jd.bean.Fl;
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
 * Created by ZMoffice on 2017.12.13.
 */

public class Shixian implements Mmodel{
    Bean bean;
    Fl fl;
    @Override
    public void getdata(Observer<Bean> o) {
        Observable.create(new Observable.OnSubscribe<Bean>() {
            @Override
            public void call(final Subscriber<? super Bean> s) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://www.zhaoapi.cn/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api=retrofit.create(Api.class);
                Call<Bean> call=api.getbean();
                call.enqueue(new Callback<Bean>() {
                    @Override
                    public void onResponse(Call<Bean> call, Response<Bean> response) {
                           bean=response.body();
                           s.onNext(bean);
                        Log.i("bean=============",bean.toString());
                    }

                    @Override
                    public void onFailure(Call<Bean> call, Throwable t) {
                        System.out.println("连接失败");
                    }
                });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(o);

    }

    @Override
    public void getfldata(Observer<Fl> o) {
        Observable.create(new Observable.OnSubscribe<Fl>() {
            @Override
            public void call(final Subscriber<? super Fl> s) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://www.zhaoapi.cn/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api=retrofit.create(Api.class);
                Call<Fl> call=api.getfl();
                call.enqueue(new Callback<Fl>() {
                    @Override
                    public void onResponse(Call<Fl> call, Response<Fl> response) {
                        fl=response.body();
                        s.onNext(fl);
                        Log.i("fl=============",fl.getData().toString());
                    }

                    @Override
                    public void onFailure(Call<Fl> call, Throwable t) {
                        System.out.println("连接失败");
                    }
                });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(o);

    }



}
