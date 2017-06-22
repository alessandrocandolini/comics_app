package comics.com.app.presentation.list;


import android.support.annotation.NonNull;

import java.util.List;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;

/**
 * Contract between view and presenter in MVP through interfaces.
 * We use strings (instread of numbers) because we don't want to perform the conversation in the view!
 * The view is as passive as possible
 * Created by alessandro.candolini on 22/06/2017.
 */

class Contract {

    /**
     * Represents the list (without the filter widget)
     */
    interface ListView extends comics.com.app.presentation.base.LoadingPage {

        void showEmptyList();
        void showList(@NonNull List<Comic> comics);
        void goToDetails(@NonNull Comic comic);
        void showTotal(@NonNull String total);
        void showPages(@NonNull String pages);
        Observable<String> amount();

    }

    /**
     * Represents the filter widget
     */
    interface FilterView extends comics.com.app.presentation.base.LoadingPage {

        void showFilteredList(@NonNull Comic comic); // in principle this can forward to a new page
        void showTotalAmount(@NonNull String total);
        void showNumberOfPages(@NonNull String pages);
        Observable<String> threshold();

    }

    public interface ListPresenter extends comics.com.app.presentation.base.Presenter<ListView> {
        void refresh();
    }

}
