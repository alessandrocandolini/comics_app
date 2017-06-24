package comics.com.app.presentation.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Base implementation of {@link Presenter}
 * Created by alessandro.candolini on 22/06/2017.
 */

public class BasePresenter<V extends View> implements Presenter<V> {

    @Nullable
    private V view;

    @CallSuper
    @Override
    public void attach(@NonNull V view) {
        this.view = view;
    }

    @CallSuper
    @Override
    public void detach() {
        this.view = null;
    }

    @CallSuper
    @Override
    public void abort() {
    }

    boolean isBound() {
        return view != null;
    }

    /**
     * Wraps the if-view-attached check and protects the view from being exposed in the subclasses.
     * It also allows to add logic for what to do when view is not attached.
     */
    protected void doOnViewAttached(@NonNull OnViewAttachedAction<V> action) {
        if (view != null) {
            action.execute(view);
        }
    }


    public interface OnViewAttachedAction<View> {
        void execute(@NonNull View view);
    }
}
