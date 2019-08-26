package me.hienngo.bbcsport;

import android.app.Application;
import android.content.Context;

import me.hienngo.bbcsport.di.component.AppComponent;
import me.hienngo.bbcsport.di.component.DaggerAppComponent;
import me.hienngo.bbcsport.di.module.AppModule;

/**
 * @author hienngo
 * @since 2/1/18
 */

public class NewsApp extends Application {
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .build();
    }

    public static AppComponent appComponent(Context context) {
        return ((NewsApp)context.getApplicationContext()).appComponent;
    }
}
