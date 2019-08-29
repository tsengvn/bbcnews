package me.hienngo.bbcsport.di.module;

import androidx.room.Room;
import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.hienngo.bbcsport.BuildConfig;
import me.hienngo.bbcsport.db.AppDatabase;
import me.hienngo.bbcsport.domain.interactor.GetNews;
import me.hienngo.bbcsport.domain.interactor.GetNewsDetail;
import me.hienngo.bbcsport.domain.repo.MovideApiRepo;
import me.hienngo.bbcsport.domain.repo.NewsApiRepo;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author hienngo
 */
@Module
public class DataModule {
//    private static final String END_POINT = "https://newsapi.org/v2/";
    private static final String END_POINT = "https://api.themoviedb.org/3/";

    @Singleton @Provides
    public OkHttpClient provideHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Singleton @Provides
    public MovideApiRepo provideApiRepo(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(END_POINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MovideApiRepo.class);
    }

    @Singleton @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Singleton @Provides
    public AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "bbc-sport").build();
    }

    @Singleton @Provides
    public GetNews provideGetNews(NewsApiRepo newsApiRepo, AppDatabase appDatabase) {
        return new GetNews(newsApiRepo, appDatabase);
    }

    @Singleton @Provides
    public GetNewsDetail provideGetNewsDetail(AppDatabase appDatabase) {
        return new GetNewsDetail(appDatabase);
    }
}
