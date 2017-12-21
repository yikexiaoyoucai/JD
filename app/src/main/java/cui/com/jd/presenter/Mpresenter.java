package cui.com.jd.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cui.com.jd.bean.Bean;
import cui.com.jd.model.Mmodel;
import cui.com.jd.model.Shixian;
import cui.com.jd.view.Mview;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.13.
 */

public class Mpresenter {
    Mview mview;
    Mmodel mmodel;
    Context context;

    public Mpresenter(Mview mview, Context context) {
        this.mview = mview;
        this.context = context;
        mmodel=new Shixian();
    }
    public void getdata()
    {
        mmodel.getdata(new Observer<Bean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Bean bean) {

               mview.setban(bean);
               mview.setbean(bean);
                mview.setbean2(bean);
            }
        });
    }
}
