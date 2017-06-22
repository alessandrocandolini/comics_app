package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * functional-flavoured implementation of {@link CalculateTotal}.
 * For an imperative approach, it is possible to rely on {@link comics.com.app.domain.utilities.TotalPriceCalculator}
 * Created by alessandro.candolini on 22/06/2017.
 */

public class RxCalculateTotal implements CalculateTotal {

    @Override
    public Observable<BigDecimal> execute(@NonNull final Iterable<? extends Comic> comics) {
        return Observable.fromIterable(comics)
                .filter(new Predicate<Comic>() {
                    @Override
                    public boolean test(@io.reactivex.annotations.NonNull Comic comic) throws Exception {
                        return comic.price() != null && comic.price().amount() != null;
                    }
                })
                .map(new Function<Comic, BigDecimal>() {
                    @Override
                    public BigDecimal apply(@io.reactivex.annotations.NonNull Comic comic) throws Exception {
                        return comic.price().amount();
                    }
                })
                .reduce(BigDecimal.ZERO, new BiFunction<BigDecimal, BigDecimal, BigDecimal>() {
                    @Override
                    public BigDecimal apply(@io.reactivex.annotations.NonNull BigDecimal amount1, @io.reactivex.annotations.NonNull BigDecimal amount2) throws Exception {
                        return amount1.add(amount2);
                    }
                })
                .toObservable();
    }

}
