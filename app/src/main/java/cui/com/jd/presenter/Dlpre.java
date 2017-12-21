package cui.com.jd.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import cui.com.jd.bean.Dlbean;
import cui.com.jd.model.Dlmodel;
import cui.com.jd.model.Shixiandl;
import cui.com.jd.view.Dlview;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.18.
 */

public class Dlpre {
    Context context;
    Dlmodel dlmodel;
    Dlview dlview;
    String m;
    String p;

    public Dlpre(Context context, Dlview dlview, String m, String p) {
        this.context = context;
        this.dlview = dlview;
        this.m = m;
        this.p = p;
        dlmodel=new Shixiandl(m,p);
    }
    public void dl()
    {
        if(TextUtils.isEmpty(m)||TextUtils.isEmpty(p))
        {
            Toast.makeText(context,"登录信息不完整",Toast.LENGTH_SHORT).show();
        }
        else
        {
            dlmodel.getdata(new Observer<Dlbean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Dlbean dlbean) {
                  if (dlbean.getCode().equals("0"))
                  {
                      Toast.makeText(context,dlbean.getMsg(),Toast.LENGTH_SHORT).show();
                      SharedPreferences sp=context.getSharedPreferences("info",Context.MODE_PRIVATE);
                      sp.edit().putInt("uid",dlbean.getData().getUid()).commit();
                      sp.edit().putString("token",dlbean.getData().getToken()).commit();
                      sp.edit().putString("username",dlbean.getData().getUsername()).commit();
                      sp.edit().putBoolean("isone",true).commit();
                  }
                  else
                  {
                      Toast.makeText(context,dlbean.getMsg(),Toast.LENGTH_SHORT).show();
                  }
                }
            });
        }
    }

}
