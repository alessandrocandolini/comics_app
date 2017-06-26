package comics.com.app.domain.entities;

import android.support.annotation.Nullable;

import java.math.BigDecimal;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */

public interface Price {

    @Nullable
    BigDecimal amount();

    @Nullable
    String currency();

}
