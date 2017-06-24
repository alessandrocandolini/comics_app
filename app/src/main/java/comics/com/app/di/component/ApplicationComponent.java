package comics.com.app.di.component;

import javax.inject.Singleton;

import comics.com.app.di.module.ActivityModule;
import comics.com.app.di.module.ApplicationModule;
import comics.com.app.di.module.RepositoryModule;
import comics.com.app.di.module.ThreadModule;
import comics.com.app.di.module.UsecaseModule;
import comics.com.app.di.module.UtilityModule;
import dagger.Component;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        RepositoryModule.class,
        UsecaseModule.class,
        ThreadModule.class,
        UtilityModule.class
})
public interface ApplicationComponent {

    String DAGGER_APPLICATION_COMPONENT = "ApplicationComponent";

    ActivityComponent plus(ActivityModule activityModule);

}
