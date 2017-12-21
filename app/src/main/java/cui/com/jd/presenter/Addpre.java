package cui.com.jd.presenter;

import android.content.Context;
import android.widget.Toast;

import cui.com.jd.bean.Addgwc;
import cui.com.jd.model.Addmodel;
import cui.com.jd.model.Shixianadd;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.18.
 */

public class Addpre {
    Context context;
    Addmodel addmodel;
    String uid;
    String pid;
    String token;

    public Addpre(Context context, String uid, String pid, String token) {
        this.context = context;
        this.uid = uid;
        this.pid = pid;
        this.token = token;
        addmodel=new Shixianadd(uid,pid,token);
    }
    public void add()
    {
        addmodel.add(new Observer<Addgwc>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Addgwc addgwc) {
                Toast.makeText(context,addgwc.getMsg(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
