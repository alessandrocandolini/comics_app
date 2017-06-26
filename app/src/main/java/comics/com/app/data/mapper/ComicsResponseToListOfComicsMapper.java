package comics.com.app.data.mapper;

import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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

            ComicOutput comicOutput = new ComicOutput(
                    String.valueOf(comic.getId()),
                    comic.getTitle(),
                    comic.getThumbnail().getUrl(),
                    comic.getPageCount(),
                    null,
                    comic.getDescription(),
                    null
            );

            output.add(comicOutput);

        }

        return output;
    }
}
