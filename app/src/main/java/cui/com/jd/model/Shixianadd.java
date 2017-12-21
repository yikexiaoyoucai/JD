package cui.com.jd.model;


import android.util.Log;

import cui.com.jd.api.Api;
import cui.com.jd.bean.Addgwc;
import cui.com.jd.bean.Baba;
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
 * Created by ZMoffice on 2017.12.18.
 */

public class Shixianadd implements Addmodel {
    String uid;
    String pid;
    String token;
    Addgwc a;
    public Shixianadd(String uid, String pid, String token) {
        this.uid = uid;
        this.pid = pid;
        this.token = token;
    }

    @Override
    public void add(Observer<Addgwc> o) {
        Observable.create(new Observable.OnSubscribe<Addgwc>() {
            @Override
            public void call(final Subscriber<? super Addgwc> s) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://www.zhaoapi.cn/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api=retrofit.create(Api.class);
                Call<Addgwc> call=api.add(uid,pid,token);
                call.enqueue(new Callback<Addgwc>() {
                    @Override
                    public void onResponse(Call<Addgwc> call, Response<Addgwc> response) {
                        a=response.body();
                        s.onNext(a);
                        Log.i("a=============",a.getMsg());
                    }

                    @Override
                    public void onFailure(Call<Addgwc> call, Throwable t) {
                        System.out.println("连接失败");
                    }
                });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(o);
    }
}
