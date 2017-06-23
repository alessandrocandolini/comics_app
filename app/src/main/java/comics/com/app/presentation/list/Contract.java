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
     * Represents the view with list of comics (without the filter widget)
     */
    interface ListView extends comics.com.app.presentation.base.LoadingPage {

        void showNoComics();
        void hideNoComics();
        void showComics(@NonNull List<Comic> comics);
        void hideComics();
        void showNumberOfComics(@NonNull String numberOfComics);
        void hideNumberOfComics();
        void goToDetails(@NonNull String comicId);

    }

    interface ListPresenter extends comics.com.app.presentation.base.Presenter<ListView> {
        void loadComics();
        void refresh();
        void onComicClick(@NonNull Comic comic);
    }

    /**
     * Represents the filter widget. It's a stand alone view because maybe in the future we want to implement it somehwere else
     * (in a popup, or in another screen). In our case it would probably be implemented on the same screen
     */
    interface FilterView extends comics.com.app.presentation.base.LoadingPage {

        void showFilteredList(@NonNull Comic comic); // in principle this can forward to a new page
        void showTotalAmount(@NonNull String total);
        void hideTotalAmount();
        void showNumberOfPages(@NonNull String pages);
        void hideNumberOfPages();

        Observable<String> threshold();

    }

    interface FilterPresenter extends comics.com.app.presentation.base.Presenter<FilterView> {
    }



}
