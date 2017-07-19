package comics.com.app.presentation.list;

import java.util.List;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ComicInfoDisplay {

    private String totalAmount;
    private String totalPages;
    private List<ViewComic> comics;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<ViewComic> getComics() {
        return comics;
    }

    public void setComics(List<ViewComic> comics) {
        this.comics = comics;
    }
}
