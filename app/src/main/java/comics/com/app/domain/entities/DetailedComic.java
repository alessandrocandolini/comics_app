package comics.com.app.domain.entities;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */

public interface DetailedComic extends Comic {

    @Nullable
    Price price();

    @Nullable
    String description();

    @Nullable
    List<String> authors();

    @Nullable
    int pageCount();
}
