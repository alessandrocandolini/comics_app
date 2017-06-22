package comics.com.app.presentation.base;

import android.support.annotation.NonNull;

/**
 * View in MVP with standard base methods for showing/hiding loading spinner and error messages
 * Created by alessandro.candolini on 22/06/2017.
 */

public interface LoadingPage extends View {

    void showLoading();
    void hideLoading();
    void showGenericError();
    void hideGenericError();
    void showError(@NonNull String error);
    void hideError();

}
