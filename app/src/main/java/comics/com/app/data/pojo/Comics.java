package comics.com.app.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class Comics {

    @SerializedName("results")
    private List<Comic> results;

    public List<Comic> getResults() {
        return results;
    }

    public void setResults(List<Comic> results) {
        this.results = results;
    }
}
