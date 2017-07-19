package comics.com.app.presentation.details;

import android.support.annotation.NonNull;

import comics.com.app.presentation.list.ListView;
import comics.com.app.presentation.list.ViewComic;

/**
 * Created by alessandro.candolini on 27/06/2017.
 */

public interface DetailPresenter extends comics.com.app.presentation.base.Presenter<DetailView> {

    void init(@NonNull ViewComic comic);
}
