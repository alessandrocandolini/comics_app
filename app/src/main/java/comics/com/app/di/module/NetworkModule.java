package comics.com.app.di.module;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moczul.ok2curl.CurlInterceptor;

import javax.inject.Singleton;

import comics.com.app.BuildConfig;
import comics.com.app.data.ApiService;
import comics.com.app.data.interceptor.ApiKeyInterceptor;
import comics.com.app.data.interceptor.ApiKeyProvider;
import comics.com.app.data.interceptor.ApiKeyProviderImpl;
import comics.com.app.data.interceptor.Md5HashGenerator;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides data layer implementations of the repositories defined in the business layer
 * Created by alessandro.candolini on 22/06/2017.
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    ApiKeyProvider providesApiKeyProvider(@NonNull Md5HashGenerator md5HashGenerator) {
        return new ApiKeyProviderImpl(md5HashGenerator);
    }

    @Nullable
    @Provides
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor; // ensure this is null in prod
    }

    @Nullable
    @Provides
    CurlInterceptor providesCurlInterceptor() {
        return new CurlInterceptor();
    }

    @Singleton
    // we want to reuse it for ALL network request, it's expensive (Jake Worthon Droidcon 2016 London)
    @Provides
    OkHttpClient providesRemoteOkHttp(@NonNull ApiKeyInterceptor apiKeyInterceptor,
                                      @Nullable HttpLoggingInterceptor httpLoggingInterceptor,
                                      @Nullable CurlInterceptor curlInterceptor
                                      ) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor(apiKeyInterceptor);

        if (httpLoggingInterceptor != null) {
            builder.addInterceptor(httpLoggingInterceptor);
        }

        if ( curlInterceptor != null ) {
            builder.addNetworkInterceptor(curlInterceptor);
        }

        return builder.build();

    }

    @Singleton
    @Provides
    Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // add here custom gson if you need
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    GsonConverterFactory providesGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    RxJava2CallAdapterFactory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit(OkHttpClient okHttpClient,
                              GsonConverterFactory gsonConverterFactory,
                              RxJava2CallAdapterFactory rxJavaCallAdapterFactory) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .client(okHttpClient);

        return builder.build();
    }

    @Provides
    ApiService providesApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


}
