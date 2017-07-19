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

    @android.support.annotation.NonNull
    private final AuthorsMapper authorsMapper;

    @android.support.annotation.NonNull
    private final ThumbnailMapper thumbnailMapper;

    @android.support.annotation.NonNull
    private final PriceMapper priceMapper;

    @Inject
    public ComicsResponseToListOfComicsMapper(@android.support.annotation.NonNull AuthorsMapper authorsMapper,
                                         @android.support.annotation.NonNull ThumbnailMapper thumbnailMapper,
                                         @android.support.annotation.NonNull PriceMapper priceMapper) {
        this.authorsMapper = authorsMapper;
        this.thumbnailMapper = thumbnailMapper;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<Comic> apply(@NonNull ComicsResponse comicsResponse) throws Exception {

        List<comics.com.app.data.pojo.Comic> comics = comicsResponse.getData().getResults();

        List<Comic> output = new ArrayList<>();

        for (final comics.com.app.data.pojo.Comic comic : comics) {

            Price price = null;
            if ( comic.getPrices() != null && comic.getPrices().size() > 0 ) {
                price = priceMapper.convert(comic.getPrices().get(0));
            }

            List<String> authors = null;
            if ( comic.getAuthors() != null ) {
                authors = authorsMapper.convert(comic.getAuthors());
            }

            String thumbnail = thumbnailMapper.convert(comic.getThumbnail());

            ComicOutput comicOutput = new ComicOutput(
                    String.valueOf(comic.getId()),
                    comic.getTitle(),
                    thumbnail,
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
