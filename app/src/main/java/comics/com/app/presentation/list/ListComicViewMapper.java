package comics.com.app.presentation.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.Price;
import io.reactivex.functions.Function;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ListComicViewMapper implements Function<ComicInfo, ComicInfoDisplay> {

    @Inject
    public ListComicViewMapper() {
    }

    @Override
    public ComicInfoDisplay apply(@io.reactivex.annotations.NonNull ComicInfo comicInfo) throws Exception {
        ComicInfoDisplay comicInfoDisplay = new ComicInfoDisplay();

        if (comicInfo.getComics() != null) {

            List<ListComic> output = new ArrayList<>();
            for (Comic comic : comicInfo.getComics()) {
                ListComic listComic = new ListComic();
                listComic.setId(comic.id());
                listComic.setTitle(comic.title());
                listComic.setThumbnail(comic.thumbnail());
                final Price price = comic.price();
                if ( price != null ) {
                    final BigDecimal amount = price.amount();
                    listComic.setPrice(printPrice(amount));
                }
                output.add(listComic);
            }
            comicInfoDisplay.setComics(output);
        }

        BigDecimal totalAmount = comicInfo.getTotalAmount();
        comicInfoDisplay.setTotalAmount(printPrice(totalAmount));

        comicInfoDisplay.setTotalPages(Integer.toString(comicInfo.getTotalPages()));

        return comicInfoDisplay;
    }

    @NonNull
    String printPrice(@Nullable BigDecimal amount) {
        if (amount != null) {
            return String.format(Locale.UK, "$%.2f", amount.doubleValue());
        }
        return "";
    }
}
