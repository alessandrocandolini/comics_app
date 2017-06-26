package comics.com.app.data.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Custom okhttp interceptor to add api-key authentication
 * Created by alessandro.candolini on 26/06/2017.
 */

public class ApiKeyInterceptor implements Interceptor {

    private static final String API_KEY_HEADER = "apikey";
    private static final String REQUEST_ID_HEADER = "ts";
    private static final String HASH_HEADER = "hash";

    @NonNull
    private final ApiKeyProvider apiKeyProvider;

    @Inject
    public ApiKeyInterceptor(@NonNull ApiKeyProvider apiKeyProvider) {
        this.apiKeyProvider = apiKeyProvider;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder builder = request.url().newBuilder();

        builder.addQueryParameter(API_KEY_HEADER, apiKeyProvider.apiKey());
        String requestId = apiKeyProvider.requestId();
        builder.addQueryParameter(REQUEST_ID_HEADER, requestId);
        builder.addQueryParameter(HASH_HEADER, apiKeyProvider.hash(requestId));

        HttpUrl url = builder.build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
