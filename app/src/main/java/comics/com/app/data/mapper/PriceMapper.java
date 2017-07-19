package comics.com.app.data.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.inject.Inject;

import comics.com.app.data.pojo.ComicPrice;
import comics.com.app.domain.entities.Price;

/**
 * Created by alessandro.candolini on 27/06/2017.
 */

public class PriceMapper {

    @Inject
    public PriceMapper() {
    }

    @NonNull
    Price convert(@NonNull ComicPrice comicPrice) {

        final BigDecimal amount = new BigDecimal(comicPrice.getPrice(), MathContext.DECIMAL64);
        final String currency = "£"; // hardcoded

        return new Price() {
            @Nullable
            @Override
            public BigDecimal amount() {
                return amount;
            }

            @Nullable
            @Override
            public String currency() {
                return currency;
            }
        };

    }
}
