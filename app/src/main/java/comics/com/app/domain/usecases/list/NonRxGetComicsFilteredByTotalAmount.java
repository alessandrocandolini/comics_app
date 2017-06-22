package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.utilities.ExtractItemsByTotalAmount;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Implementation of {@link GetComicsFilteredByTotalAmount} that delegates to {@link
 * ExtractItemsByTotalAmount}, using imerative style
 * Created by alessandro.candolini on 21/06/2017.
 */

public class NonRxGetComicsFilteredByTotalAmount implements GetComicsFilteredByTotalAmount {

    @NonNull
    private final ExtractItemsByTotalAmount extractItemsByTotalAmount;

    @Inject
    public NonRxGetComicsFilteredByTotalAmount(@NonNull ExtractItemsByTotalAmount extractItemsByTotalAmount) {
        this.extractItemsByTotalAmount = extractItemsByTotalAmount;
    }

    @Override
    public Observable<List<Comic>> execute(@NonNull final Iterable<? extends Comic> comics, @NonNull final BigDecimal maximumAmount) {
        return
                Observable.fromIterable(comics)
                        .toList()
                        .toObservable()
                        .map(new Function<List<Comic>, List<Comic>>() {
                            @Override
                            public List<Comic> apply(@io.reactivex.annotations.NonNull List<Comic> comics) throws Exception {
                                return extractItemsByTotalAmount.filterListByTotalAmount(comics, maximumAmount);
                            }
                        });
    }


}
