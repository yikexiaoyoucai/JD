package cui.com.jd.model;

import android.util.Log;

import cui.com.jd.api.Api;
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
 * Created by ZMoffice on 2017.12.14.
 */

public class Shixianbaba implements Babamodel{
    String cid;

    public Shixianbaba(String cid) {
        this.cid = cid;
    }

    Baba baba;
    @Override
    public void getbaba(Observer<Baba> o) {
        Observable.create(new Observable.OnSubscribe<Baba>() {
            @Override
            public void call(final Subscriber<? super Baba> s) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://www.zhaoapi.cn/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api=retrofit.create(Api.class);
                Call<Baba> call=api.getbaba(cid);
                call.enqueue(new Callback<Baba>() {
                    @Override
                    public void onResponse(Call<Baba> call, Response<Baba> response) {
                        baba=response.body();
                        s.onNext(baba);
                        Log.i("baba=============",baba.getData().get(2).getName());
                    }

                    @Override
                    public void onFailure(Call<Baba> call, Throwable t) {
                        System.out.println("连接失败");
                    }
                });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(o);

    }
}
