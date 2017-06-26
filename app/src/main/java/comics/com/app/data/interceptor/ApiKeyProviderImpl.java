package comics.com.app.data.interceptor;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import comics.com.app.BuildConfig;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ApiKeyProviderImpl implements  ApiKeyProvider{

    @NonNull
    private final Md5HashGenerator md5HashGenerator;

    @Inject
    public ApiKeyProviderImpl(@NonNull Md5HashGenerator md5HashGenerator) {
        this.md5HashGenerator = md5HashGenerator;
    }

    @Override
    @NonNull
    public String apiKey() {
        return BuildConfig.PUBLIC_API_KEY;
    }

    @Override
    @NonNull
    public String hash(@NonNull String requestId) {
        String message = requestId + BuildConfig.PRIVATE_API_KEY + BuildConfig.PUBLIC_API_KEY;
        return md5HashGenerator.hash(message);
    }

    @Override
    @NonNull
    public String requestId() {
        return String.valueOf(System.currentTimeMillis());
    }

}
