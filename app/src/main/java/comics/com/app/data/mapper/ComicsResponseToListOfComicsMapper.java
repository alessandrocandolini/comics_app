package comics.com.app.data.mapper;

import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import comics.com.app.data.pojo.ComicCreator;
import comics.com.app.data.pojo.ComicOutput;
import comics.com.app.data.pojo.ComicsResponse;
import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.Price;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ComicsResponseToListOfComicsMapper implements Function<ComicsResponse, List<Comic>> {

    @Inject
    public ComicsResponseToListOfComicsMapper() {
    }

    @Override
    public List<Comic> apply(@NonNull ComicsResponse comicsResponse) throws Exception {

        List<comics.com.app.data.pojo.Comic> comics = comicsResponse.getData().getResults();

        List<Comic> output = new ArrayList<>();

        for (final comics.com.app.data.pojo.Comic comic : comics) {

//            private final String id;
//            private final String title;
//            private final String thumbnail;
//            private final int pageCount;
//            private final Price price;
//            private final String description;
//            private final List<String> authors;

            final Price price;

            if ( comic.getPrices() != null && comic.getPrices().size() > 0 ) {
                price = new Price() {
                    @Nullable
                    @Override
                    public BigDecimal amount() {
                        return new BigDecimal(comic.getPrices().get(0).getPrice(), MathContext.DECIMAL64);
                    }

                    @Nullable
                    @Override
                    public String currency() {
                        return "£"; // hardcoced
                    }
                };
            } else {
                price = null;
            }

            final List<String> authors;
            if (comic.getAuthors() != null && comic.getAuthors().getItems() != null && comic.getAuthors().getItems().size() > 0 ) {
                authors = new ArrayList<>();
                for (ComicCreator creator : comic.getAuthors().getItems()) {
                    String name = creator.getName();
                    if ( name != null ) {
                        authors.add(name);
                    }
                }
            } else {
                authors = null;
            }

            ComicOutput comicOutput = new ComicOutput(
                    String.valueOf(comic.getId()),
                    comic.getTitle(),
                    comic.getThumbnail().getUrl(),
                    comic.getPageCount(),
                    price,
                    comic.getDescription(),
                    authors
            );

            output.add(comicOutput);

        }

        return output;
    }
}
