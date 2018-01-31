package me.hienngo.bbcsport.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.hienngo.bbcsport.db.AppDatabase;

/**
 * @author hienngo
 * @since 9/29/17
 */
@Module
public class AppModule {
    private final Application context;

    public AppModule(Application context) {
        this.context = context;
    }

    @Singleton @Provides
    public Context provideContext() {
        return this.context;
    }

    @Singleton @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Singleton @Provides
    public AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "bbc-sport").build();
    }

}
