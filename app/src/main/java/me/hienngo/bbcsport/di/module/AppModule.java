package me.hienngo.bbcsport.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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

}
