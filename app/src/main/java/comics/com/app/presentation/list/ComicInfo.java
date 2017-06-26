package comics.com.app.presentation.list;

import java.math.BigDecimal;
import java.util.List;

import comics.com.app.domain.entities.Comic;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ComicInfo {

    private BigDecimal totalAmount;
    private int totalPages;
    private List<Comic> comics;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Comic> getComics() {
        return comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }
}
