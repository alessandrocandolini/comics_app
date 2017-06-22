package comics.com.app.domain.utilities;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.Price;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */

public class ExtractItemsByTotalAmount {

    @Inject
    public ExtractItemsByTotalAmount() {
    }

    /**
     * @param comics List of comics
     *
     * @return Loop through the list and keep all items until the total is higher that the threshold
     */
    public List<Comic> filterListByTotalAmount(@NonNull Iterable<? extends Comic> comics,
                                               @NonNull BigDecimal threashold
    ) {
        List<Comic> filteredList = new ArrayList<>();
        BigDecimal accumulator = BigDecimal.ZERO;
        for (Comic comic : comics) {
            final Price price = comic.price();
            if (price != null) {
                BigDecimal amount = price.amount();
                if (amount != null) {
                    accumulator = accumulator.add(amount);
                }
            }
            if (accumulator.compareTo(threashold) <= 0) {
                filteredList.add(comic);
            } else {
                break; // try to comment this and run ExtractItemsByTotalAmountSpeedTest
            }
        }
        return filteredList;
    }

}
