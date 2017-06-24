package comics.com.app.presentation.list;

import android.support.annotation.NonNull;

import comics.com.app.domain.entities.Comic;
import comics.com.app.presentation.base.LoadingView;
import io.reactivex.Observable;

/**
 * Represents the filter widget. It's a stand alone view because maybe in the future we want to
 * implement it somehwere else
 * (in a popup, or in another screen). In our case it would probably be implemented on the same
 * screen
 * Created by alessandro.candolini on 24/06/2017.
 */

public interface FilterView extends LoadingView {

    void showFilteredList(@NonNull Comic comic); // in principle this can forward to a new page

    void showTotalAmount(@NonNull String total);

    void hideTotalAmount();

    void showNumberOfPages(@NonNull String pages);

    void hideNumberOfPages();

    Observable<String> threshold();

}
