package com.charlesdrews.charlesdrewsdemoapp.data.sources;

import com.charlesdrews.charlesdrewsdemoapp.data.models.PersonFullDetail;
import com.charlesdrews.charlesdrewsdemoapp.data.models.PersonMinDetail;

import java.util.List;

/**
 * Data manager to store and retrieve PersonFullDetail objects
 *
 * Created by charlie on 8/13/16.
 */
public interface DataManager {

    /**
     * Get a list of all people in the database with the minimum detail needed for the list feature
     * @return a List of PersonMinDetail objects
     */
    List<PersonMinDetail> getAllPeopleForListFeature();

    /**
     * Get a list of people in the database matching the query, with the minimum detail needed
     * for the list feature
     * @param query
     * @return a List of PersonMinDetail objects matching the query
     */
    List<PersonMinDetail> searchPeopleForListFeature(String query);

    /**
     * Get a person by id with all detail needed for the detail feature
     * @param personId
     * @return a PersonFullDetail object if id found in database, else null
     */
    PersonFullDetail getFullPersonDetailById(long personId);

    /**
     * Add a person to the database
     * @param person
     * @return long id of person if added successfully, else -1
     */
    long addPerson(PersonFullDetail person);

    /**
     * Get a list of location names present in the database
     * @return a List of location name Strings
     */
    List<String> getLocationNames();

    /**
     * Get a list of platform names present in the database
     * @return a List of platform name Strings
     */
    List<String> getPlatformNames();
}
