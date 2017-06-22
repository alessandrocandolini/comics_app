package comics.com.app.domain.usecases.list;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.repositories.ComicsRepository;
import io.reactivex.Observable;

/**
 * Usecase with package visibility to retrieve the list of the first {@code number} items.
 * THe usecase delegates the request to {@link ComicsRepository}.
 * Created by alessandro.candolini on 21/06/2017.
 */

class GetGivenNumberOfComics {

    @NonNull
    private final ComicsRepository comicsRepository;

    @Inject
    public GetGivenNumberOfComics(@NonNull ComicsRepository comicsRepository) {
        this.comicsRepository = comicsRepository;
    }

    public Observable<List<? extends Comic>> execute(@IntRange(from = 0) int number, boolean refresh) {
        if ( refresh ) {
            return comicsRepository.clean().andThen(comicsRepository.comics(number));
        } else {
            return comicsRepository.comics(number);
        }
    }

}
