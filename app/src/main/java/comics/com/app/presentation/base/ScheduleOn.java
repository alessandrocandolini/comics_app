package comics.com.app.presentation.base;

import android.support.annotation.NonNull;

import org.reactivestreams.Publisher;

import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;

/**
 * Container for Rx {@link Scheduler}'s
 * Created by alessandro.candolini on 22/06/2017.
 */

public class ScheduleOn {

    /**
     * {@link Scheduler} for disk/network operations
     */
    @NonNull
    private final Scheduler io;

    /**
     * {@link Scheduler} for posting on the ui layer
     */
    @NonNull
    private final Scheduler ui;

    /**
     * {@link Scheduler} for doing computations in background thread
     */
    @NonNull
    private final Scheduler computation;

    public ScheduleOn(@NonNull Scheduler io,
                      @NonNull Scheduler ui,
                      @NonNull Scheduler computation) {
        this.io = io;
        this.ui = ui;
        this.computation = computation;
    }

    /**
     * Applies schedules such that the subscription is performed on the io thread, but the response
     * is observed on the Android main thread.
     */
    public <T> ObservableTransformer<T, T> applyObservableTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@io.reactivex.annotations.NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(io)
                        .observeOn(ui);
            }
        };
    }

    @NonNull
    public Scheduler io() {
        return io;
    }

    @NonNull
    public Scheduler ui() {
        return ui;
    }

    @NonNull
    public Scheduler computation() {
        return computation;
    }
}
