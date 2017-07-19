package comics.com.app.presentation.details;

import java.util.List;

import comics.com.app.presentation.base.View;

/**
 * Created by alessandro.candolini on 27/06/2017.
 */

public interface DetailView extends View{

    void showImage(String imageUrl);
    void showTitle(String title);
    void showDescription(String description);
    void showPageCount(String pageCount);
    void showPrice(String price);
    void showAuthor(String authors);
}
