package comics.com.app.di.module;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.repositories.ComicsRepository;
import comics.com.app.domain.repositories.DetailedComicRepository;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Provides data layer implementations of the repositories defined in the business layer
 * Created by alessandro.candolini on 22/06/2017.
 */
@Module
public class RepositoryModule {

    @Provides
    ComicsRepository providesComicsRepository() {
        return new ComicsRepository() {
            @Override
            public Completable clean() {
                return null;
            }

            @Override
            public Observable<List<Comic>> comics(@IntRange(from = 0) int count) {
                return Observable.empty();
            }
        }; // TODO replace this mock with real data
    }

    @Provides
    DetailedComicRepository providesDetailedComicRepository() {
        return new DetailedComicRepository() {

            @Override
            public Observable<Comic> comic(@NonNull String id) {
                return Observable.empty();
            }
        }; // TODO replace this mock with real data
    }

}
