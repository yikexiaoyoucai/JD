package cui.com.jd.model;

import cui.com.jd.bean.Baba;
import cui.com.jd.bean.Xq;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.15.
 */

public interface Xqmodel {
    void getxq(Observer<Xq> o);
}
