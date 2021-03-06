package comics.com.app.presentation.list;

import android.support.annotation.NonNull;

/**
 * Created by alessandro.candolini on 24/06/2017.
 */

public interface ListPresenter extends comics.com.app.presentation.base.Presenter<ListView> {

    void loadComics();

    void refresh(@NonNull CharSequence text);

    void onComicClick(@NonNull ViewComic comic);
}
