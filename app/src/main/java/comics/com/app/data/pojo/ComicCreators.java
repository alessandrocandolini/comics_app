package comics.com.app.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ComicCreators {

    @SerializedName("items")
    private List<ComicCreator> items;

    public List<ComicCreator> getItems() {
        return items;
    }

    public void setItems(List<ComicCreator> items) {
        this.items = items;
    }
}
