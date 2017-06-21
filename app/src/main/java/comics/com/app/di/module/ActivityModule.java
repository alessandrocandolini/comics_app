package comics.com.app.di.module;

import android.app.Activity;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */
@Module
public class ActivityModule {

    @NonNull
    private final Activity activity;

    public ActivityModule(@NonNull Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity providesActivity() {
        return  activity;
    }
}
