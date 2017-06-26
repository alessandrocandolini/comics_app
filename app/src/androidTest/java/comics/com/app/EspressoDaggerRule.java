package comics.com.app;

import android.support.test.InstrumentationRegistry;

import comics.com.app.di.component.ApplicationComponent;
import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Created by alessandro.candolini on 25/06/2017.
 */

public class EspressoDaggerRule extends DaggerMockRule<ApplicationComponent> {

    public EspressoDaggerRule() {
        super(ApplicationComponent.class);
        set(new DaggerMockRule.ComponentSetter<ApplicationComponent>() {
            @Override public void setComponent(ApplicationComponent component) {
                getApp().setApplicationComponentForTests(component);
            }
        });
    }

    private static BaseApplication getApp() {
        return (BaseApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }
}
