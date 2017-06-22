package comics.com.app.domain.usecases.comics;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.repositories.ComicsRepository;
import io.reactivex.Observable;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */

public class GetComics {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    static final int NUMBER_OF_COMICS = 100;

    @NonNull
    private final ComicsRepository comicsRepository;

    public GetComics(@NonNull ComicsRepository comicsRepository) {
        this.comicsRepository = comicsRepository;
    }

    public Observable<List<? extends Comic>> execute() {
        return comicsRepository.comics(NUMBER_OF_COMICS);
    }

}
