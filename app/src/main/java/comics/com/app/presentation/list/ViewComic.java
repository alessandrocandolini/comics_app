package comics.com.app.presentation.list;

import org.parceler.Parcel;

import java.util.List;

/**
 * Model for the data to be displayed on the view to avoid processing of information in the view
 * (so, all fields are strings, are supposed to be formatted correctly etc)
 * Created by alessandro.candolini on 26/06/2017.
 */
@Parcel
public class ViewComic {

    private String id;
    private String title;
    private String price;
    private String thumbnail;
    private String description;
    private List<String> authors;
    private int pageCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
