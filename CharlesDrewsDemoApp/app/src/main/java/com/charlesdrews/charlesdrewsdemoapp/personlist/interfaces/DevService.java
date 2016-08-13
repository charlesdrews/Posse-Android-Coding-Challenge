package com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces;

import java.util.List;

/**
 * Created by charlie on 8/13/16.
 */
public interface DevService {
    int getId();
    String getPlatform();
    List<Location> getLocations();
    List<Person> getPeople();
}
