package comics.com.app.di.module;

import android.support.annotation.NonNull;

import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.DetailedComic;
import comics.com.app.domain.repositories.ComicsRepository;
import comics.com.app.domain.repositories.DetailedComicRepository;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

/**
 * Provides implementations of the repositories in the business layer
 * Created by alessandro.candolini on 22/06/2017.
 */
@Module
public class RepositoryModule {

    @Provides
    ComicsRepository providesComicsRepository() {
        return new ComicsRepository() {
            @Override
            public Observable<List<? extends Comic>> comics(int count) {
                return Observable.empty();
            }
        }; // TODO replace this mock with real data
    }

    @Provides
    DetailedComicRepository providesDetailedComicRepository() {
        return new DetailedComicRepository() {

            @Override
            public Observable<DetailedComic> comic(@NonNull Comic comic) {
                return Observable.empty();
            }
        }; // TODO replace this mock with real data
    }

}
