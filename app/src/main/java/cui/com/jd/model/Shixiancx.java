package cui.com.jd.model;


import android.util.Log;

import cui.com.jd.api.Api;
import cui.com.jd.bean.Addgwc;
import cui.com.jd.bean.Cxgwc;
import cui.com.jd.view.Cxview;
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

public class Shixiancx implements Cxmodel {
    String uid;
    String token;
    Cxgwc a;
    public Shixiancx(String uid,String token) {
        this.uid = uid;
        this.token = token;
    }

    @Override
    public void cx(Observer<Cxgwc> o) {
        Observable.create(new Observable.OnSubscribe<Cxgwc>() {
            @Override
            public void call(final Subscriber<? super Cxgwc> s) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://www.zhaoapi.cn/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api=retrofit.create(Api.class);
                Call<Cxgwc> call=api.cx(uid,token);
                call.enqueue(new Callback<Cxgwc>() {
                    @Override
                    public void onResponse(Call<Cxgwc> call, Response<Cxgwc> response) {
                        a=response.body();
                        s.onNext(a);
                        Log.i("a=============",a.getMsg());
                    }

                    @Override
                    public void onFailure(Call<Cxgwc> call, Throwable t) {
                        System.out.println("连接失败");
                    }
                });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(o);
    }
}
