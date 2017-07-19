package comics.com.app.presentation.list;

import java.math.BigDecimal;
import java.util.List;

import comics.com.app.domain.entities.Comic;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ComicInfoDisplay {

    private String totalAmount;
    private String totalPages;
    private List<ListComic> comics;

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

    public List<ListComic> getComics() {
        return comics;
    }

    public void setComics(List<ListComic> comics) {
        this.comics = comics;
    }
}
