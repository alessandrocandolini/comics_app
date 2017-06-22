package comics.com.app.presentation.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

/**
 * Base implementation of {@link Presenter}
 * Created by alessandro.candolini on 22/06/2017.
 */

public class BasePresenter<V extends View> implements Presenter<V> {

    @Nullable
    private V view;

    @CallSuper
    @Override
    public void bind(@NonNull V view) {
        this.view = view;
    }

    @CallSuper
    @Override
    public void unbind() {
        this.view = null;
    }

    @CallSuper
    @Override
    public void abort() {
    }

    boolean isBound() {
        return view != null;
    }
}
