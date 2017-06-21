package comics.com.app.domain.usecases.comics;

import java.util.List;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */

public class GetComics {

    public Observable<List<Comic>> execute() {
        return Observable.empty();
    }

}
