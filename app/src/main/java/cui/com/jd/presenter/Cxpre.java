package cui.com.jd.presenter;

import android.content.Context;
import android.widget.Toast;

import cui.com.jd.bean.Addgwc;
import cui.com.jd.bean.Cxgwc;
import cui.com.jd.model.Addmodel;
import cui.com.jd.model.Cxmodel;
import cui.com.jd.model.Shixianadd;
import cui.com.jd.model.Shixiancx;
import cui.com.jd.view.Cxview;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.18.
 */

public class Cxpre {
    Context context;
    Cxmodel cxmodel;
    Cxview cxview;
    String uid;
    String token;

    public Cxpre(Context context, Cxview cxview,String uid,String token) {
        this.context = context;
        this.cxview = cxview;
        this.uid = uid;
        this.token = token;
        cxmodel=new Shixiancx(uid,token);
    }
    public void cx()
    {
        cxmodel.cx(new Observer<Cxgwc>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Cxgwc cxgwc) {
                cxview.getcx(cxgwc);
            }
        });
    }
}
