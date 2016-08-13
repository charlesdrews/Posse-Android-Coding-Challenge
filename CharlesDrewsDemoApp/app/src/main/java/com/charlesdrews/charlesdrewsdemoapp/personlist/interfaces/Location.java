package com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces;

import java.util.List;

/**
 * Created by charlie on 8/13/16.
 */
public interface Location {
    int getId();
    String getName();
    String getRegion();
    String getPostalCode();
    String getCountry();
    List<DevService> getDevServices();
    List<Person> getPeople();
}
