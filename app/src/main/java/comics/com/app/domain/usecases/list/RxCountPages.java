package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Similar to {@link NonRxCountPages} but using more Rx-ish stuff
 * Created by alessandro.candolini on 22/06/2017.
 */

public class RxCountPages implements CountPages {

    @Override
    public Observable<Integer> execute(@NonNull final Iterable<? extends Comic> comics) {
        return Observable.fromIterable(comics)
                .map(new Function<Comic, Integer>() {
                    @Override
                    public Integer apply(@io.reactivex.annotations.NonNull Comic comic) throws Exception {
                        return comic.pageCount();
                    }
                })
                .reduce(0, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@io.reactivex.annotations.NonNull Integer page1, @io.reactivex.annotations.NonNull Integer page2) throws Exception {
                        return page1 + page2; // unnecessary unboxing
                    }
                })
                .toObservable();
    }

}
