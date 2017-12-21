package cui.com.jd.model;

import cui.com.jd.bean.Dlbean;
import cui.com.jd.bean.Regbean;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.16.
 */

public interface Dlmodel {
    void getdata(Observer<Dlbean> o);
}
