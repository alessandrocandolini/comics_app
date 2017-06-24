package comics.com.app.di.module;

import comics.com.app.domain.usecases.list.GetComics;
import comics.com.app.presentation.base.ScheduleOn;
import comics.com.app.presentation.list.ListPresenter;
import comics.com.app.presentation.list.ListPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by alessandro.candolini on 24/06/2017.
 */
@Module
public class PresenterModule {

    @Provides
    ListPresenter providesLoginPresenter(ScheduleOn scheduleOn,
                                         GetComics getComics
    ) {
        return new ListPresenterImpl(scheduleOn, getComics);
    }


}
