package comics.com.app.data.repository;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import comics.com.app.data.ApiService;
import comics.com.app.data.mapper.ComicsResponseToListOfComicsMapper;
import comics.com.app.data.pojo.ComicsResponse;
import comics.com.app.domain.entities.Comic;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ComicsRepository implements comics.com.app.domain.repositories.ComicsRepository{

    @NonNull
    private final ApiService apiService;

    @NonNull
    private final ComicsResponseToListOfComicsMapper comicsResponseToListOfComicsMapper;

    @Inject
    public ComicsRepository(@NonNull ApiService apiService, @NonNull ComicsResponseToListOfComicsMapper comicsResponseToListOfComicsMapper) {
        this.apiService = apiService;
        this.comicsResponseToListOfComicsMapper = comicsResponseToListOfComicsMapper;
    }

    @Override
    public Completable clean() {
        return Completable.complete();
    }

    @Override
    public Observable<List<Comic>> comics(@IntRange(from = 0) int count) {
        return Observable.concat(cache(count), network(count))
                .firstElement()
                .toObservable()
                .map(comicsResponseToListOfComicsMapper);
    }

    private Observable<ComicsResponse> network(@IntRange(from = 0) int count) {
        return apiService.comics(count);
    }

    private Observable<ComicsResponse> cache(@IntRange(from = 0) int count) {
        return Observable.empty(); // TODO implement cache
    }
}
