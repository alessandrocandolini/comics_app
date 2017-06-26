package comics.com.app.presentation.base;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import comics.com.app.BaseActivity;

/**
 * Created by alessandro.candolini on 25/06/2017.
 */

public abstract class BasePresenterActivity extends BaseActivity {

    @NonNull
    private final List<PresenterViewContainer> presenterViewContainerList = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
        for (PresenterViewContainer container : presenterViewContainerList) {
            container.onStart();
        }
    }

    @Override
    protected void onStop() {
        for (PresenterViewContainer container : presenterViewContainerList) {
            container.onStop();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        final boolean isFinishing = isFinishing();
        for (PresenterViewContainer container : presenterViewContainerList) {
            container.onDestroy(isFinishing);
        }
        presenterViewContainerList.clear();
        super.onDestroy();
    }

    /**
     * This method must be called in the subactivities to register a new presenter-view pair
     * @param view
     * @param presenter
     * @param <V>
     * @param <P>
     */
    protected <V extends View, P extends Presenter<V>> void registerPresenterLifecycle(@NonNull V view, @NonNull P presenter) {
        PresenterViewContainer<V, P> presenterViewContainer = new PresenterViewContainer<>(view, presenter);
        presenterViewContainerList.add(presenterViewContainer);
    }

    private static class PresenterViewContainer<V extends View, P extends Presenter<V>> {

        @NonNull
        private final V view;

        @NonNull
        private final P presenter;

        private PresenterViewContainer(@NonNull V view, @NonNull P presenter) {
            this.view = view;
            this.presenter = presenter;
        }

        private void onStart() {
            presenter.attach(view);
        }

        private void onStop() {
            presenter.detach();
        }

        private void onDestroy(boolean isFinishing) {
            if (isFinishing) {
                presenter.abort();
            }
        }


    }

}
