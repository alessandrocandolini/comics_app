package comics.com.app.presentation.list;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import comics.com.app.domain.usecases.list.GetComics;
import comics.com.app.domain.usecases.list.MaxListOfComincsWithGivenAmount;
import comics.com.app.presentation.base.RxBasePresenter;
import comics.com.app.presentation.base.ScheduleOn;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */

public class Presenter extends RxBasePresenter<Contract.ListView> implements Contract.ListPresenter {

    @NonNull
    private final GetComics getComics;

    @NonNull
    private final MaxListOfComincsWithGivenAmount maxListOfComincsWithGivenAmount;


    // we choose to inject directly the presenter class, but it's easy to have a presenter module that
    // provides the presenter interface

    @Inject
    public Presenter(@NonNull ScheduleOn scheduleOn,
                     @NonNull GetComics getComics,
                     @NonNull MaxListOfComincsWithGivenAmount maxListOfComincsWithGivenAmount) {
        super(scheduleOn);
        this.getComics = getComics;
        this.maxListOfComincsWithGivenAmount = maxListOfComincsWithGivenAmount;
    }

    @Override
    public void refresh() {

    }
}
