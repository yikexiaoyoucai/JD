package cui.com.jd.presenter;

import android.content.Context;

import cui.com.jd.bean.Xq;
import cui.com.jd.model.Shixianxq;
import cui.com.jd.model.Xqmodel;
import cui.com.jd.view.Xqview;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.15.
 */

public class Xqpresenter {
    String pid;
    Xqmodel xqmodel;
    Xqview xqview;
    Context context;

    public Xqpresenter(String pid, Xqview xqview, Context context) {
        this.pid = pid;
        this.xqview = xqview;
        this.context = context;
        xqmodel=new Shixianxq(pid);
    }
    public void setdata()
    {
        xqmodel.getxq(new Observer<Xq>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Xq xq) {
               xqview.setXq(xq);
            }
        });
    }
}
