package comics.com.app.presentation.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Extends {@link BasePresenter} with couple of utilities for handling Rx observables inside presenters
 * Created by alessandro.candolini on 22/06/2017.
 */

public class RxBasePresenter<V extends View> extends BasePresenter<V> {

    /**
     * Used for automatic unsubscription of Rx subscriptions/disposables
     */
    @NonNull
    private final CompositeDisposable disposables;

    @NonNull
    protected final ScheduleOn scheduleOn;

    public RxBasePresenter(@NonNull ScheduleOn scheduleOn) {
        super();
        disposables = new CompositeDisposable();
        this.scheduleOn = scheduleOn;
    }

    /**
     * Add a subscription to be tracked such that when this presenter is destroyed the subscription
     * will be unsubscribed from. Developer must remember to register the disposable
     */
    @CallSuper
    protected void addToAutoUnsubscribe(@NonNull Disposable disposable) {
        disposables.add(disposable);
    }

    @CallSuper
    @Override
    public void abort() {
        if (!disposables.isDisposed()) {
            disposables.clear();
            disposables.dispose();
        }
        super.abort();
    }

}
