package comics.com.app;

import android.app.Application;
import android.support.annotation.Nullable;

import comics.com.app.di.component.ApplicationComponent;
import comics.com.app.di.component.DaggerApplicationComponent;
import comics.com.app.di.module.ApplicationModule;

/**
 * Base application class. Used to store the dagger application component
 * Created by alessandro.candolini on 21/06/2017.
 */

public class BaseApplication extends Application {

    /** Kepp track of the application component */
    @Nullable
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @Nullable
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
