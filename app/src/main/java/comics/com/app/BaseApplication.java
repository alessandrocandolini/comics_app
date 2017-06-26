package comics.com.app;

import android.app.Application;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import comics.com.app.di.component.ApplicationComponent;
import comics.com.app.di.component.DaggerApplicationComponent;
import comics.com.app.di.module.ApplicationModule;
import comics.com.app.di.module.NetworkModule;
import comics.com.app.di.module.RepositoryModule;
import comics.com.app.di.module.ThreadModule;
import comics.com.app.di.module.UsecaseModule;
import comics.com.app.di.module.UtilityModule;

/**
 * Base application class. Used to store the dagger application component
 * Created by alessandro.candolini on 21/06/2017.
 */

public class BaseApplication extends Application {

    /** Keep track of the application component */
    @Nullable
    protected ApplicationComponent applicationComponent = null;

    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = createApplicationComponent();
    }

    @Nullable
    protected ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .repositoryModule(new RepositoryModule())
                .threadModule(new ThreadModule())
                .usecaseModule(new UsecaseModule())
                .utilityModule(new UtilityModule())
                .networkModule(new NetworkModule())
                .build();
    }

    @Nullable
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    void setApplicationComponentForTests(@Nullable ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

}
