package comics.com.app.domain.repositories;

import java.util.List;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */

public interface ComicsRepository {

    /**
     * @return Display a list of the first {@param count} comics
     */
    Observable<List<? extends Comic>> comics(int count);

}
