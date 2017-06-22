package comics.com.app.domain.repositories;

import android.support.annotation.NonNull;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.DetailedComic;
import io.reactivex.Observable;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */

public interface DetailedComicRepository {

    /**
     * @return Display a list of the first {@param count} comics
     */
    Observable<DetailedComic> comic(@NonNull Comic comic);

}
