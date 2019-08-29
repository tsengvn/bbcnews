package me.hienngo.bbcsport.di.component;

/**
 * @author hienngo
 */

import javax.inject.Singleton;

import dagger.Component;
import me.hienngo.bbcsport.di.module.AppModule;
import me.hienngo.bbcsport.di.module.DataModule;
import me.hienngo.bbcsport.ui.detail.DetailActivity;
import me.hienngo.bbcsport.ui.main.MainActivity;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
