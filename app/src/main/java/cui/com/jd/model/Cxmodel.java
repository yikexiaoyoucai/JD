package cui.com.jd.model;

import cui.com.jd.bean.Addgwc;
import cui.com.jd.bean.Cxgwc;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.18.
 */

public interface Cxmodel {
    void cx(Observer<Cxgwc> o);
}
