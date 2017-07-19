package comics.com.app.di.component;

import comics.com.app.di.module.PresenterModule;
import comics.com.app.di.scope.ActivityScope;
import comics.com.app.presentation.list.ListActivity;
import dagger.Subcomponent;

/**
 * Created by alessandro.candolini on 21/06/2017.
 */

@ActivityScope
@Subcomponent(
        modules = {
                PresenterModule.class
        }
)
public interface ActivityComponent {
        String DAGGER_ACTIVITY_COMPONENT = "ActivityComponent";

        void inject(ListActivity activity);
}
