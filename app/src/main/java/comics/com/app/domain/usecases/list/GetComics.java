package comics.com.app.domain.usecases.list;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;

/**
 * Retrieve list of 100 comics.
 * THe usecase delegates the request to the more generic usecase {@link GetGivenNumberOfComics}.
 * Created by alessandro.candolini on 21/06/2017.
 */

public class GetComics {

    @IntRange(from = 0)
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    static final int NUMBER_OF_COMICS = 100;

    @NonNull
    private final GetGivenNumberOfComics getGivenNumberOfComics;

    @Inject
    public GetComics(@NonNull GetGivenNumberOfComics getGivenNumberOfComics) {
        this.getGivenNumberOfComics = getGivenNumberOfComics;
    }

    public Observable<List<Comic>> execute() {
        return getGivenNumberOfComics.execute(NUMBER_OF_COMICS, false);
    }

}
