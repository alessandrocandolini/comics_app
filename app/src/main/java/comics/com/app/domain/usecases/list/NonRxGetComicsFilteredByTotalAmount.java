package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.repositories.ComicsRepository;
import comics.com.app.domain.utilities.ExtractItemsByTotalAmount;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Retrieve list of 100 comics.
 * THe usecase delegates the request to {@link ComicsRepository}.
 * Created by alessandro.candolini on 21/06/2017.
 */

public class NonRxGetComicsFilteredByTotalAmount implements GetComicsFilteredByTotalAmount{

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
