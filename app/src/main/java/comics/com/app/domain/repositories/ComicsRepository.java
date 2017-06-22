package comics.com.app.domain.repositories;

import android.support.annotation.IntRange;

import java.util.List;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */

public interface ComicsRepository {

    /** Clean cache (if any) */
    Completable clean();

    /**
     * @return Display a list of the first {@param count} comics
     */
    Observable<List<? extends Comic>> comics(@IntRange(from = 0) int count);

}
