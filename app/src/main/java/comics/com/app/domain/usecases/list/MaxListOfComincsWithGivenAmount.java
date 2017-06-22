package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.repositories.ComicsRepository;
import comics.com.app.domain.utilities.ExtractItemsByTotalAmount;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Sort and filter the list of {@link Comic} by total amount.
 * Delegates the sorting to {@link SortComicsByPrice} and the filter to {@link GetComicsFilteredByTotalAmount}.
 * Composition allows to switch between different implemnetations of the usecases.
 * Created by alessandro.candolini on 21/06/2017.
 */

public class MaxListOfComincsWithGivenAmount implements GetComicsFilteredByTotalAmount{

    @NonNull
    private final GetComicsFilteredByTotalAmount getComicsFilteredByTotalAmount;

    @NonNull
    private final SortComicsByPrice sortComicsByPrice;

    @Inject
    public MaxListOfComincsWithGivenAmount(@NonNull GetComicsFilteredByTotalAmount getComicsFilteredByTotalAmount, @NonNull SortComicsByPrice sortComicsByPrice) {
        this.getComicsFilteredByTotalAmount = getComicsFilteredByTotalAmount;
        this.sortComicsByPrice = sortComicsByPrice;
    }

    @Override
    public Observable<List<Comic>> execute(@NonNull final Iterable<? extends Comic> comics, @NonNull final BigDecimal maximumAmount) {
        return
                sortComicsByPrice.execute(comics)
                .flatMap(new Function<List<Comic>, ObservableSource<List<Comic>>>() {
                    @Override
                    public ObservableSource<List<Comic>> apply(@io.reactivex.annotations.NonNull List<Comic> comics) throws Exception {
                        return getComicsFilteredByTotalAmount.execute(comics,maximumAmount);
                    }
                });
    }


}
