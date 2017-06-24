package comics.com.app.di.component;

import comics.com.app.di.module.ActivityModule;
import comics.com.app.di.module.PresenterModule;
import comics.com.app.di.scope.ActivityScope;
import dagger.Subcomponent;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */

@ActivityScope
@Subcomponent(
        modules = {
                ActivityModule.class,
                PresenterModule.class
        }
)
public interface ActivityComponent {
        String DAGGER_ACTIVITY_COMPONENT = "ActivityComponent";
}
