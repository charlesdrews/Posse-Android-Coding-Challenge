package com.charlesdrews.data.resources.db;

import com.charlesdrews.domain.entities.Location;
import com.charlesdrews.domain.entities.Person;
import com.charlesdrews.domain.entities.Platform;

/**
 * Created by charlie on 11/28/17.
 */

public interface DbResource {
    void addLocation(Location location);
    void addPlatform(Platform platform);
    void addPerson(Person person);
}
