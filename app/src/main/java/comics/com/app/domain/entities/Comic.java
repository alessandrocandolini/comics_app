package comics.com.app.domain.entities;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */

public interface Comic extends Entity {

    @Nullable
    String id();

    @Nullable
    String title();

    @Nullable
    String thumbnail();

    @Nullable
    int pageCount();

    @Nullable
    Price price();

    @Nullable
    String description();

    @Nullable
    List<String> authors();
}
