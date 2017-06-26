package comics.com.app.data.pojo;

import android.support.annotation.Nullable;

import java.util.List;

import comics.com.app.domain.entities.*;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ComicOutput implements comics.com.app.domain.entities.Comic {

    private final String id;
    private final String title;
    private final String thumbnail;
    private final int pageCount;
    private final Price price;
    private final String description;
    private final List<String> authors;

    public ComicOutput(String id, String title, String thumbnail, int pageCount, Price price, String description, List<String> authors) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.pageCount = pageCount;
        this.price = price;
        this.description = description;
        this.authors = authors;
    }

    @Nullable
    @Override
    public String id() {
        return id;
    }

    @Nullable
    @Override
    public String title() {
        return title;
    }

    @Nullable
    @Override
    public String thumbnail() {
        return thumbnail;
    }

    @Nullable
    @Override
    public int pageCount() {
        return pageCount;
    }

    @Nullable
    @Override
    public Price price() {
        return price;
    }

    @Nullable
    @Override
    public String description() {
        return description;
    }

    @Nullable
    @Override
    public List<String> authors() {
        return authors;
    }
}
