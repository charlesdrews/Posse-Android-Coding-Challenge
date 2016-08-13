package com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces;

import java.util.List;

/**
 * Data manager to store and retrieve Location, DevService, and Person objects
 *
 * Created by charlie on 8/13/16.
 */
public interface DataManager {
    List<Person> getAllPeople();
    List<Person> getPeopleByLocationId(int locationId);
    List<Person> getPeopleByDevServiceId(int devServiceId);
    Person getpersonById(int personId);

    List<Location> getAllLocations();
    List<Location> getLocationsByDevServiceId(int devServiceId);
    Location getLocationById(int locationId);

    List<DevService> getAllDevServices();
    List<DevService> getDevServicesByLocationId(int locationId);
    DevService getDevServiceById(int devServiceId);

    /**
     * Add a Person to data storage
     * @param person
     * @return int id of Person if added successfully, else -1
     */
    int addperson(Person person);

    /**
     * Add a Location to data storage
     * @param location
     * @return int id of Location if added successfully, else -1
     */
    int addLocation(Location location);

    /**
     * Add a DevService to data storage
     * @param devService
     * @return int id of DevService if added successfully, else -1
     */
    int addDevService(DevService devService);
}
