package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;

/**
 * Base usecase for counting pages of a list of {@link Comic}.
 * We provide two implementation, one more imperative (wrapped inside Rx), the other more functional (using Rx map-reduce)
 * Dagger allows you to swipe between the two.
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
