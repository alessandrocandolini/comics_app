package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import comics.com.app.di.module.UsecaseModule;
import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;

/**
 * Base usecase for counting the total cost of a list of {@link Comic}.
 * We provide one implementation of this interface, {@link RxCalculateTotal}.
 * A more imperative style is possible using {@link comics.com.app.domain.utilities.TotalPriceCalculator}
 * Dagger allows you to swipe implementation, see {@link UsecaseModule#providesCalculateTotal()} ()}
 * Created by alessandro.candolini on 22/06/2017.
 */

public interface CalculateTotal {

    /**
     * @param comics List of comics
     *
     * @return total nuumber of pages
     */
    Observable<BigDecimal> execute(@NonNull final Iterable<? extends Comic> comics);

}
