package comics.com.app.domain.repositories;

import android.support.annotation.NonNull;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */

public interface DetailedComicRepository {

    /**
     * @return Display a list of the first {@param count} comics
     */
    Observable<Comic> comic(@NonNull String id);

}
