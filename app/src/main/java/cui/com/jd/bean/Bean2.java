package cui.com.jd.bean;

/**
 * Created by ZMoffice on 2017.12.18.
 */

public class Bean2 {
    private String price;
    private String number;

    public Bean2(String price, String number) {
        this.price = price;
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
