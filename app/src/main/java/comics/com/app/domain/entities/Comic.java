package comics.com.app.domain.entities;

import android.support.annotation.Nullable;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */

public interface Comic extends Entity {

    @Nullable
    String title();

    @Nullable
    String thumbnail();

    @Nullable
    int pageCount();

    @Nullable
    Price price();
}
