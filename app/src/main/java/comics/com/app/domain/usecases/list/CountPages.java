package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.utilities.PageCounter;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Usecase: given a list of comics, compute the total number of pages.
 * The actual computation is delegated to a {@link PageCounter} object, this usecase wraps
 * the behaviour in a rx-fashion (allowing us to chain usecases nicely)
 * <p>
 * This method does suppress errors.
 * Is this the expected behaviour? It depends on business requirements.
 * This behaviour can be chanced later at any time.
 * At the moment, we don't want to break other usescases just
 * because of errors in total page count. It's also useful to illustrate how to do that with Rx
 * Created by alessandro.candolini on 22/06/2017.
 */

public class CountPages {

    @NonNull
    private final PageCounter pageCounter;

    @Inject
    public CountPages(@NonNull PageCounter pageCounter) {
        this.pageCounter = pageCounter;
    }

    Observable<Integer> execute(@NonNull final List<Comic> comics) {
        return Observable.fromCallable(new Callable<Integer>() {
                                           @Override
                                           public Integer call() throws Exception {
                                               return pageCounter.countPages(comics);
                                           }
                                       }
        ).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                return 0;
            }
        });
    }

}
