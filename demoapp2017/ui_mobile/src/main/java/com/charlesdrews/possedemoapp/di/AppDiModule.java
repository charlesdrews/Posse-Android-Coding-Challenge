package com.charlesdrews.possedemoapp.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.charlesdrews.data.resources.db.room.PeopleDatabase;
import com.charlesdrews.data.resources.db.room.PersonDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by charlie on 11/18/17.
 */

@Module
class AppDiModule {

    @Provides
    @Singleton
    PeopleDatabase providePeopleDatabase(Application application) {
        return Room.databaseBuilder(application, PeopleDatabase.class, "people.db").build();
    }

    @Provides
    @Singleton
    PersonDao providePersonDao(PeopleDatabase database) {
        return database.personDao();
    }
}
