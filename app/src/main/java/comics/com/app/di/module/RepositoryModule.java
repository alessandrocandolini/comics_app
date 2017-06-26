package comics.com.app.di.module;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import comics.com.app.data.ApiService;
import comics.com.app.data.mapper.ComicsResponseToListOfComicsMapper;
import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.Price;
import comics.com.app.domain.repositories.ComicsRepository;
import comics.com.app.domain.repositories.DetailedComicRepository;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Provides data layer implementations of the repositories defined in the business layer
 * Created by alessandro.candolini on 22/06/2017.
 */
@Module
public class RepositoryModule {
//
//    @Provides
//    ComicsRepository providesComicsRepository() {
//        ComicsRepository oneValue = new ComicsRepository() {
//
//            boolean error = false;
//
//            @Override
//            public Completable clean() {
//                return null;
//            }
//
//            @Override
//            public Observable<List<Comic>> comics(@IntRange(from = 0) int count) {
//
//                Comic comic = new Comic() {
//                    @Nullable
//                    @Override
//                    public String id() {
//                        return null;
//                    }
//
//                    @Nullable
//                    @Override
//                    public String title() {
//                        return "title";
//                    }
//
//                    @Nullable
//                    @Override
//                    public String thumbnail() {
//                        return "http://syndication.andrewsmcmeel.com/uu_site/ad/image_path/41/marmaduke_spotlight.jpg";
//                    }
//
//                    @Nullable
//                    @Override
//                    public int pageCount() {
//                        return 110;
//                    }
//
//                    @Nullable
//                    @Override
//                    public Price price() {
//                        return new Price() {
//                            @Nullable
//                            @Override
//                            public BigDecimal amount() {
//                                return BigDecimal.valueOf(10);
//                            }
//
//                            @Nullable
//                            @Override
//                            public String currency() {
//                                return "£";
//                            }
//
//                            @Nullable
//                            @Override
//                            public String printPrice() {
//                                return "£ 10.00";
//                            }
//                        };
//                    }
//
//                    @Nullable
//                    @Override
//                    public String description() {
//                        return "description";
//                    }
//
//                    @Nullable
//                    @Override
//                    public List<String> authors() {
//                        return null;
//                    }
//                };
//
//                List<Comic> list = Collections.singletonList(comic);
//
//                if (error) {
//                    error = false;
//                    return Observable.just(list).delay(5, TimeUnit.SECONDS);
//                } else {
//                    error = true;
//                    return Observable.<List<Comic>>error(new CustomException());
//                }
//
//
//            }
//        }; // TODO replace this mock with real data
//
//
//        ComicsRepository empty = new ComicsRepository() {
//            @Override
//            public Completable clean() {
//                return Completable.complete();
//            }
//
//            @Override
//            public Observable<List<Comic>> comics(@IntRange(from = 0) int count) {
//                return Observable.empty();
//            }
//        };
//
//        return oneValue;
//    }

    @Provides
    ComicsRepository providesComicsRepository(@NonNull ApiService apiService,
                                              @NonNull ComicsResponseToListOfComicsMapper comicsResponseToListOfComicsMapper) {
        return new comics.com.app.data.repository.ComicsRepository(apiService,comicsResponseToListOfComicsMapper);
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

    public static class CustomException extends IOException {

    }

}
