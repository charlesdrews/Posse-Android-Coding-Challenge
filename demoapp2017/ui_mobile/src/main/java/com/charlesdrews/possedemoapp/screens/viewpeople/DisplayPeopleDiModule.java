package com.charlesdrews.possedemoapp.screens.viewpeople;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by charlie on 11/18/17.
 */

@Module
public abstract class DisplayPeopleDiModule {

    @ContributesAndroidInjector
    abstract DisplayPeopleActivity contributeDisplayPeopleActivity();
}
