package me.hienngo.bbcsport.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.hienngo.bbcsport.BuildConfig;
import me.hienngo.bbcsport.domain.repo.NewsApiRepo;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author hienngo
 * @since 9/29/17
 */
@Module
public class NetworkModule {
    private static final String END_POINT = "https://newsapi.org/v2/";

    @Singleton @Provides
    public OkHttpClient provideHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Singleton @Provides
    public NewsApiRepo provideNewsApiRepo(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(END_POINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(NewsApiRepo.class);
    }

}
