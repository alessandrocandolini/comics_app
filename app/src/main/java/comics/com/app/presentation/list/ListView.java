package comics.com.app.presentation.list;

import android.support.annotation.NonNull;

import com.jakewharton.rxbinding2.InitialValueObservable;

import java.util.List;

import comics.com.app.presentation.base.LoadingView;
import io.reactivex.Observable;

/**
 * View showing list of comics
 * Created by alessandro.candolini on 24/06/2017.
 */

public interface ListView extends LoadingView {

    void showNoComics();

    void hideNoComics();

    void showComics(@NonNull List<ListComic> comics);

    void hideComics();

    void showNumberOfComics(@NonNull String numberOfComics);

    void hideNumberOfComics();

    void showTotalAmount(@NonNull String total);

    void hideTotalAmount();

    void showNumberOfPages(@NonNull String pages);

    void hideNumberOfPages();

    void showTotalAmountError();

    void hideTotalAmountError();

    void goToDetails(@NonNull String comicId);
}
