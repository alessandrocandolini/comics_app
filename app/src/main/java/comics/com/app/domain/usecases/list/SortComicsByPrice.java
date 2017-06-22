package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.DetailedComic;
import comics.com.app.domain.repositories.ComicsRepository;
import io.reactivex.Observable;

/**
 * Retrieve list of 100 comics.
 * THe usecase delegates the request to {@link ComicsRepository}.
 * Created by alessandro.candolini on 21/06/2017.
 */

public class SortComicsByPrice {

    @NonNull
    private final Comparator<Comic> comparator;

    @Inject
    public SortComicsByPrice(@NonNull Comparator<Comic> comparator) {
        this.comparator = comparator;
    }

    public Observable<List<Comic>> execute(@NonNull Iterable<? extends Comic> unsortedComics) {
        return Observable.fromIterable(unsortedComics)
                .sorted(comparator)
                .toList()
                .toObservable();
    }

}
