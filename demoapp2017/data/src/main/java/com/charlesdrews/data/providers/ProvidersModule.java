package com.charlesdrews.data.providers;

import com.charlesdrews.domain.interfaces.PeopleSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by charlie on 12/6/17.
 */

@Module
public class ProvidersModule {
    @Provides
    static PeopleSource providePeopleSource(PeopleRepository peopleRepository) {
        return peopleRepository;
    }
}
