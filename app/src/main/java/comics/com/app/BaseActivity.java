package comics.com.app;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import comics.com.app.di.component.ActivityComponent;
import comics.com.app.di.component.ApplicationComponent;
import comics.com.app.di.module.ActivityModule;

/** Base class. Provides support for dagger to inject activities*/
public abstract class BaseActivity extends AppCompatActivity {

    /** Keep reference to the activity component */
    @Nullable
    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initActivityComponent();
        if (activityComponent != null) {
            injectActivity(activityComponent);  // inject before super.onCreate because fragments are recreated on super.onCreate
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        activityComponent = null;
        super.onDestroy();
    }

    /**
     * This method will be implemented in the derived classes to inject them
     * @param activityComponent activity component to be used for injections
     */
    protected abstract void injectActivity(@NonNull ActivityComponent activityComponent);

    private void initActivityComponent() {
        ApplicationComponent applicationComponent = ((BaseApplication) getApplication()).getApplicationComponent();
        if (applicationComponent != null) {
            activityComponent = applicationComponent.plus(new ActivityModule(this));
        }
    }

    /**
     * Override method to provide a trick to access application and activity component inside custom views.
     * <p>
     * Another option would be to cast the getContext() method, however
     * some views can use ContextThemeWrapper to inflate child views rather than the containing
     * Activity’s context. Refer to https://medium.com/@theMikhail/system-services-are-not-just-for-the-system-ce33aab4594a
     */
    @Override
    @CallSuper
    public Object getSystemService(@NonNull String name) {
        if (ActivityComponent.DAGGER_ACTIVITY_COMPONENT.equals(name)) {
            return activityComponent;
        }
        if (ApplicationComponent.DAGGER_APPLICATION_COMPONENT.equals(name)) {
            return ((BaseApplication) getApplication()).getApplicationComponent();
        }
        return super.getSystemService(name);
    }


}
