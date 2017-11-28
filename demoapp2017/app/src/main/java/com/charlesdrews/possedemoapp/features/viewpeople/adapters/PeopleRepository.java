package com.charlesdrews.possedemoapp.features.viewpeople.adapters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;

import com.charlesdrews.possedemoapp.domain.interfaces.PeopleProvider;
import com.charlesdrews.possedemoapp.resources.db.room.PersonDao;
import com.charlesdrews.possedemoapp.domain.entities.Person;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by charlie on 11/18/17.
 */

@Singleton
public class PeopleRepository implements PeopleProvider {
    private final PersonDao personDao;

    @Inject
    public PeopleRepository(PersonDao personDao) {
        this.personDao = personDao;
    }

    public LiveData<Person> getPeople() {
        return LiveDataReactiveStreams.fromPublisher(personDao.getPeople().map(personEntity -> new Person()));
    }
}
