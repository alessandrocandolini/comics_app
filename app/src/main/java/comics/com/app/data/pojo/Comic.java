package comics.com.app.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class Comic {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnail")
    private ComicImage thumbnail;

    @SerializedName("description")
    private String description;

    @SerializedName("pageCount")
    private int pageCount;

    @SerializedName("prices")
    private List<ComicPrice> prices;

    @SerializedName("creators")
    private ComicCreators creators;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ComicImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ComicImage thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<ComicPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ComicPrice> prices) {
        this.prices = prices;
    }

    public ComicCreators getAuthors() {
        return creators;
    }

    public void setAuthors(ComicCreators creators) {
        this.creators = creators;
    }
}
