package cui.com.jd.model;

import cui.com.jd.bean.Baba;
import cui.com.jd.bean.Bean;
import cui.com.jd.bean.Fl;
import rx.Observer;

/**
 * Created by ZMoffice on 2017.12.13.
 */

public interface Mmodel {
    void getdata(Observer<Bean> o);
    void getfldata(Observer<Fl> o);

}
