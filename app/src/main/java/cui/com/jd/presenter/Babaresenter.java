package cui.com.jd.presenter;

import android.content.Context;

import cui.com.jd.bean.Baba;
import cui.com.jd.bean.Bean;
import cui.com.jd.model.Babamodel;
import cui.com.jd.model.Mmodel;
import cui.com.jd.model.Shixian;
import cui.com.jd.model.Shixianbaba;
import cui.com.jd.view.Babaview;
import cui.com.jd.view.Mview;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.13.
 */

public class Babaresenter {
    Babaview mview;
    Babamodel mmodel;
    Context context;
    String cid;

    public Babaresenter(Babaview mview, Context context, String cid) {
        this.mview = mview;
        this.context = context;
        this.cid = cid;
        mmodel=new Shixianbaba(cid);
    }

    public void getdata()
    {
       mmodel.getbaba(new Observer<Baba>() {
           @Override
           public void onCompleted() {

           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onNext(Baba baba) {
             mview.getbaba(baba.getData());
           }
       });
    }
}
