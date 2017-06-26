package comics.com.app.data.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ComicCreator {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
