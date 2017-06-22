package comics.com.app.di.module;

import android.app.Application;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */
@Module
public class ApplicationModule {

    @NonNull
    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return application;
    }
}
