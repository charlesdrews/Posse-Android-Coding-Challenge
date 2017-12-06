package com.charlesdrews.possedemoapp.di;

import android.app.Application;

import com.charlesdrews.possedemoapp.screens.viewpeople.DisplayPeopleDiModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by charlie on 11/18/17.
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppDiModule.class,
        DisplayPeopleDiModule.class})
public interface AppDiComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppDiComponent build();
    }

    void inject(PeopleApp app);
}
