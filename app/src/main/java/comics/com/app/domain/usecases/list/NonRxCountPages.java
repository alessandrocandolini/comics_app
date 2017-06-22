package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.utilities.PageCounter;
import io.reactivex.Observable;

/**
 * Usecase: given a list of comics, compute the total number of pages.
 * The actual computation is delegated to a {@link PageCounter} object, this usecase wraps
 * the behaviour in a rx-fashion (allowing us to chain usecases nicely)
 * Created by alessandro.candolini on 22/06/2017.
 */

public class NonRxCountPages implements CountPages {

    @NonNull
    private final PageCounter pageCounter;

    public NonRxCountPages(@NonNull PageCounter pageCounter) {
        this.pageCounter = pageCounter;
    }

    @Override
    public Observable<Integer> execute(@NonNull final Iterable<? extends Comic> comics) {
        return Observable.fromCallable(new Callable<Integer>() {
                                           @Override
                                           public Integer call() throws Exception {
                                               return pageCounter.countPages(comics);
                                           }
                                       }
        );
    }

}
