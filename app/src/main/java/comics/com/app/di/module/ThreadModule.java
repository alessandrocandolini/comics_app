package comics.com.app.di.module;


import javax.inject.Singleton;

import comics.com.app.di.annotations.ComputationScheduler;
import comics.com.app.di.annotations.IoScheduler;
import comics.com.app.di.annotations.UiScheduler;
import comics.com.app.presentation.base.ScheduleOn;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Provides Rx {@link Scheduler}'s
 * Created by alessandro.candolini on 22/06/2017.
 */
@Module
public class ThreadModule {

    @Singleton
    @UiScheduler
    @Provides
    Scheduler providesUiScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @IoScheduler
    @Provides
    Scheduler providesIoScheduler() {
        return Schedulers.io();
    }

    @Singleton
    @ComputationScheduler
    @Provides
    Scheduler providescomputationScheduler() {
        return  Schedulers.computation();
    }

    @Singleton
    @Provides
    ScheduleOn providesScheduleOn(
            @UiScheduler Scheduler ui,
            @IoScheduler Scheduler io,
            @ComputationScheduler Scheduler computation
    ) {
        return new ScheduleOn(ui,io,computation);
    }
}
