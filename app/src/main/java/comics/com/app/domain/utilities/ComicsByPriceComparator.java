package comics.com.app.domain.utilities;

import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Comparator;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.Price;

/**
 * Compare two {@link Comic} by price.
 */

public class ComicsByPriceComparator implements Comparator<Comic> {

    /**
     * @param comic1 First comic
     * @param comic2 Second comic
     *
     * {@code 1} if {@code this > val}, {@code -1} if {@code this < val}, {@code 0 otherwise}
     *
     * @return If prices are not null, delegates to comparison between amounts; return 0 in all
     * other cases.
     *
     */
    @Override
    public int compare(@Nullable Comic comic1, @Nullable Comic comic2) {

        if (comic1 != null && comic2 != null) {

            final Price price1 = comic1.price();
            final Price price2 = comic2.price();

            if (price1 != null && price2 != null) {

                final BigDecimal amount1 = price1.amount();
                final BigDecimal amount2 = price2.amount();

                if (amount1 != null && amount2 != null) {
                    return amount1.compareTo(amount2);
                }
            }
        }

        return 0;
    }

}
