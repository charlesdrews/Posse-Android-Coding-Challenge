package com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces;

import java.util.List;

/**
 * Data manager to store and retrieve Location, DevService, and Programmer objects
 *
 * Created by charlie on 8/13/16.
 */
public interface DataManager {
    List<Programmer> getAllProgrammers();
    List<Programmer> getProgrammersByLocationId(int locationId);
    List<Programmer> getProgrammersByDevServiceId(int devServiceId);
    Programmer getProgrammerById(int programmerId);

    List<Location> getAllLocations();
    List<Location> getLocationsByDevServiceId(int devServiceId);
    Location getLocationById(int locationId);

    List<DevService> getAllDevServices();
    List<DevService> getDevServicesByLocationId(int locationId);
    DevService getDevServiceById(int devServiceId);

    /**
     * Add a Programmer to data storage
     * @param programmer
     * @return int id of Programmer if added successfully, else -1
     */
    int addProgrammer(Programmer programmer);

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
