package cui.com.jd.model;

import cui.com.jd.bean.Baba;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.14.
 */

public interface Babamodel {
    void getbaba(Observer<Baba> o);
}
