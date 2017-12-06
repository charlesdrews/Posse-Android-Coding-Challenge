package com.charlesdrews.domain.interfaces;

import com.charlesdrews.domain.entities.Location;
import com.charlesdrews.domain.entities.Person;
import com.charlesdrews.domain.entities.Platform;

import java.util.List;

import io.reactivex.Observable;

/**
 * Contract that must be fulfilled by any repository providing Person objects
 * <p>
 * Created by charlie on 11/28/17.
 */

public interface PeopleProvider {
    Observable<List<Person>> getPeople(long filterLocationId, long filterPlatformId);
    Observable<Person> getPerson(long personId);
    Observable<List<Location>> getLocations();
    Observable<List<Platform>> getPlatforms();
}
