package me.hienngo.bbcsport.di.component;

/**
 * @author hienngo
 * @since 9/29/17
 */

import javax.inject.Singleton;

import dagger.Component;
import me.hienngo.bbcsport.di.module.AppModule;
import me.hienngo.bbcsport.di.module.DataModule;
import me.hienngo.bbcsport.ui.main.MainActivity;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
