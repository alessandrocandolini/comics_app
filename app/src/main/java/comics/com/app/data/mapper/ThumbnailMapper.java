package comics.com.app.data.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.inject.Inject;

import comics.com.app.data.pojo.ComicImage;
import comics.com.app.data.pojo.ComicPrice;
import comics.com.app.domain.entities.Price;

/**
 * Created by alessandro.candolini on 27/06/2017.
 */

public class ThumbnailMapper {

    @Inject
    public ThumbnailMapper() {
    }

    @Nullable
    String convert(@NonNull ComicImage comicImage) {

        return comicImage.getUrl() + "." + comicImage.getExtension();
    }
}
