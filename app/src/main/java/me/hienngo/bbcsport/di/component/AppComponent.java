package me.hienngo.bbcsport.di.component;

/**
 * @author hienngo
 * @since 9/29/17
 */

import javax.inject.Singleton;

import dagger.Component;
import me.hienngo.bbcsport.di.module.AppModule;
import me.hienngo.bbcsport.di.module.NetworkModule;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
}
