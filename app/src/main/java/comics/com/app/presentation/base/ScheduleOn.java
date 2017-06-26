package comics.com.app.presentation.base;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

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

    public ScheduleOn(@NonNull Scheduler ui,
                      @NonNull Scheduler io,
                      @NonNull Scheduler computation) {
        this.ui = ui;
        this.io = io;
        this.computation = computation;
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
