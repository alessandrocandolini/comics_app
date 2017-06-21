package comics.com.app.presentation.base;

import android.support.annotation.NonNull;

/**
 * Represents the presenter in MVP.
 * <p>
 * We allow presenter to be attached and detached from views.
 * Although this is not as per original MVP pattern, it might be helpful when dealing with
 * android lifecycle problems.
 * Created by alessandro.candolini on 21/06/2017.
 */

public interface Presenter<V extends View> {

    /** Attach view to presenter */
    void bind(@NonNull V view);

    /** Detatch view */
    void unbind();

    /** Abort long-lasting operations */
    void abort();
}
