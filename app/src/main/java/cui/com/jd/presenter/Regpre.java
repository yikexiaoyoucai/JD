package cui.com.jd.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import cui.com.jd.bean.Dlbean;
import cui.com.jd.bean.Regbean;
import cui.com.jd.model.Dlmodel;
import cui.com.jd.model.Regmodel;
import cui.com.jd.model.Shixiandl;
import cui.com.jd.model.Shixianreg;
import cui.com.jd.view.Dlview;
import cui.com.jd.view.Regview;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.18.
 */

public class Regpre {
    Context context;
    Regmodel regmodel;
    Regview regview;
    String m;
    String p;

    public Regpre(Context context, Regview regview, String m, String p) {
        this.context = context;
        this.regview = regview;
        this.m = m;
        this.p = p;
        regmodel=new Shixianreg(m,p);
    }
    public void reg()
    {
        if(TextUtils.isEmpty(m)||TextUtils.isEmpty(p))
        {
            Toast.makeText(context,"注册信息不完整",Toast.LENGTH_SHORT).show();
        }
        else
        {
            regmodel.getdata(new Observer<Regbean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Regbean regbean) {
                  if (regbean.getCode().equals("0"))
                  {
                      Toast.makeText(context,regbean.getMsg(),Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                      Toast.makeText(context,regbean.getMsg(),Toast.LENGTH_SHORT).show();
                  }
                }
            });
        }
    }

}
