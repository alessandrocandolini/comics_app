package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */

public interface GetComicsFilteredByTotalAmount {


    /**
     * Given a list {@code comics} of {@link Comic} and a {@code maximumAmount}, returns the sublist
     * of {@code comics} obtained by looping through the list and keeping all the comics until the
     * total is less than {@code maximumAmount}
     *
     * @param comics Original list of comincs
     * @param maximumAmount Threshold for the total
     *
     * @return maximum ordered sublist of {@code comics} whose total is less or equal to {@code maximumAmount}
     */
    Observable<List<Comic>> execute(@NonNull Iterable<? extends Comic> comics, @NonNull BigDecimal maximumAmount);


}
