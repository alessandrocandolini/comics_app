package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Same as {@link CountPages} but suppressing errors.
 * Is this the expected behaviour? It depends on business requirements.
 * This behaviour can be chanced later at any time.
 * Sometimes, we might want to fail silently, emitting 0, instead of breaking the entire chain just
 * because of errors in counting the total number of pages .
 * It's a business question if you want to enable this or not.
 * It's also useful to illustrate how to do that with Rx.
 * We prefer here composition over inheritance to avoid troubles when combining multiple usecases
 * Created by alessandro.candolini on 22/06/2017.
 */

public class CountPagesDoNotThrowError {

    @NonNull
    private final CountPages countPages;

    @Inject
    public CountPagesDoNotThrowError(@NonNull CountPages countPages) {
        this.countPages = countPages;
    }

    public Observable<Integer> execute(@NonNull final Iterable<? extends Comic> comics) {
        return countPages.execute(comics)
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        return 0;
                    }
                });

    }

}
