package comics.com.app.domain.utilities;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.Price;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */

public class TotalPriceCalculator {

    @Inject
    public TotalPriceCalculator() {
    }

    /**
     * @param comics List of comics
     *
     * @return Total price of all comics.
     */
    public BigDecimal calculateTotal(@NonNull Iterable<? extends Comic> comics) {
        BigDecimal accumulator = BigDecimal.ZERO;
        for (Comic comic : comics) {
            final Price price = comic.price();
            if ( price != null ) {
                BigDecimal amount = price.amount();
                if ( amount != null) {
                    accumulator = accumulator.add(amount);
                }
            }
        }
        return accumulator;
    }

}
