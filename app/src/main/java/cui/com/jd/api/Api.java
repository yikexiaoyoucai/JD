package cui.com.jd.api;

import cui.com.jd.bean.Addgwc;
import cui.com.jd.bean.Baba;
import cui.com.jd.bean.Bean;
import cui.com.jd.bean.Cxgwc;
import cui.com.jd.bean.Dlbean;
import cui.com.jd.bean.Fl;
import cui.com.jd.bean.Regbean;
import cui.com.jd.bean.Xq;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ZMoffice on 2017.12.13.
 */

public interface Api {
    @GET("ad/getAd")
    Call<Bean> getbean();
    @GET("product/getCatagory")
    Call<Fl> getfl();
    @GET("/product/getProductCatagory?")
    Call<Baba> getbaba(@Query("cid") String cid);
    @GET("product/getProductDetail?&code=1")
    Call<Xq> getxq(@Query("pid") String pid);
    @GET("user/reg?")
    Call<Regbean> getreg(@Query("mobile") String mobile,@Query("password") String pwd);
    @GET("user/login?")
    Call<Dlbean> getdl(@Query("mobile") String mobile, @Query("password") String pwd);
    @GET("product/addCart?&&")
    Call<Addgwc> add(@Query("uid") String uid,@Query("pid") String pid,@Query("token") String token);
    @GET("product/getCarts?&&code=1")
    Call<Cxgwc> cx(@Query("uid") String uid, @Query("token") String token);
}
