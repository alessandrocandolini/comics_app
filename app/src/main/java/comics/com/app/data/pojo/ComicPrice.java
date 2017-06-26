package comics.com.app.data.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ComicPrice {

    @SerializedName("price")
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
