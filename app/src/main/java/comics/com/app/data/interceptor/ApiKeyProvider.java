package comics.com.app.data.interceptor;

import android.support.annotation.NonNull;

/**
 * Utility class to provide info for the {@link ApiKeyInterceptor}
 * Created by alessandro.candolini on 26/06/2017.
 */

public interface ApiKeyProvider {

    @NonNull
    String apiKey();

    @NonNull
    String hash(@NonNull String requestId);

    @NonNull
    String requestId();

}
