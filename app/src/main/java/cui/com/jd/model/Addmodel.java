package cui.com.jd.model;

import cui.com.jd.bean.Addgwc;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.18.
 */

public interface Addmodel {
    void add(Observer<Addgwc> o);
}
