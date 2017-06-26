package comics.com.app.presentation.list;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.Price;
import io.reactivex.functions.Function;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ListComicViewMapper implements Function<List<Comic>, List<ListComic>> {

    @Inject
    public ListComicViewMapper() {
    }

    @Override
    public List<ListComic> apply(@io.reactivex.annotations.NonNull List<Comic> comics) throws Exception {
        List<ListComic> output = new ArrayList<ListComic>();
        for (Comic comic : comics) {
            ListComic listComic = new ListComic();
            listComic.setId(comic.id());
            listComic.setTitle(comic.title());
            listComic.setThumbnail(comic.thumbnail());

            String priceString;
            final Price price = comic.price();
            if (price != null ) {
                final BigDecimal amount = price.amount();
                if ( amount != null ) {
                    priceString = "£ " + amount.toString();
                } else {
                    priceString = "";
                }
            } else {
                priceString = "";
            }
            listComic.setPrice(priceString);
            output.add(listComic);
        }
        return output;
    }
}
