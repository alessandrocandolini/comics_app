package comics.com.app.presentation.list;

import android.support.annotation.NonNull;

import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.presentation.base.LoadingView;

/**
 * View showing list of comics
 * Created by alessandro.candolini on 24/06/2017.
 */

public interface ListView extends LoadingView {

    void showNoComics();

    void hideNoComics();

    void showComics(@NonNull List<Comic> comics);

    void hideComics();

    void showNumberOfComics(@NonNull String numberOfComics);

    void hideNumberOfComics();

    void goToDetails(@NonNull String comicId);
}
