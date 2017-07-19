package comics.com.app.data.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ComicImage {

    @SerializedName("path")
    private String url;

    @SerializedName("extension")
    private String extension;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
