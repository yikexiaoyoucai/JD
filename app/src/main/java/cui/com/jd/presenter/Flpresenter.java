package cui.com.jd.presenter;

import android.content.Context;

import cui.com.jd.bean.Bean;
import cui.com.jd.bean.Fl;
import cui.com.jd.model.Mmodel;
import cui.com.jd.model.Shixian;
import cui.com.jd.view.Flview;
import cui.com.jd.view.Mview;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.14.
 */

public class Flpresenter {
    Flview mview;
    Mmodel mmodel;
    Context context;

    public Flpresenter(Flview mview, Context context) {
        this.mview = mview;
        this.context = context;
        mmodel=new Shixian();
    }
    public void getfl()
    {
        mmodel.getfldata(new Observer<Fl>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Fl bean) {

                mview.getfl(bean.getData());
            }
        });
    }
}
