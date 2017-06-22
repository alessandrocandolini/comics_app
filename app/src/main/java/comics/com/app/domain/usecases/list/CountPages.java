package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import comics.com.app.di.module.UsecaseModule;
import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;

/**
 * Base usecase for counting the sum of pages from a list of {@link Comic}.
 * We provide two implementation of this interface, {@link NonRxCountPages} is imperative (wrapped inside Rx), the other one {@link RxCountPages} is more functional (using Rx map-reduce)
 * Dagger allows you to swipe between the two, see {@link UsecaseModule#providesCountPages()}
 * Created by alessandro.candolini on 22/06/2017.
 */

public interface CountPages {

    /**
     * @param comics List of comics
     *
     * @return total nuumber of pages
     */
    Observable<Integer> execute(@NonNull final Iterable<? extends Comic> comics);

}
