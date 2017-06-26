package comics.com.app.di.module;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import comics.com.app.domain.usecases.list.CalculateTotal;
import comics.com.app.domain.usecases.list.CountPages;
import comics.com.app.domain.usecases.list.GetComics;
import comics.com.app.domain.usecases.list.GetComicsFilteredByTotalAmount;
import comics.com.app.presentation.base.ScheduleOn;
import comics.com.app.presentation.list.ListComicViewMapper;
import comics.com.app.presentation.list.ListPresenter;
import comics.com.app.presentation.list.ListPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by alessandro.candolini on 24/06/2017.
 */
@Module
public class PresenterModule {

    //  The following methods must be declared public or protected in order DaggerMock to override it at runtime
    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    @Provides
    public ListPresenter providesLoginPresenter(@NonNull ScheduleOn scheduleOn,
                                                @NonNull GetComics getComics,
                                                @NonNull ListComicViewMapper listComicViewMapper,
                                                @NonNull CalculateTotal calculateTotal,
                                                @NonNull GetComicsFilteredByTotalAmount getComicsFilteredByTotalAmount,
                                                @NonNull CountPages countPages
    ) {
        return new ListPresenterImpl(scheduleOn, getComics,listComicViewMapper,calculateTotal,getComicsFilteredByTotalAmount,countPages);
    }


}
