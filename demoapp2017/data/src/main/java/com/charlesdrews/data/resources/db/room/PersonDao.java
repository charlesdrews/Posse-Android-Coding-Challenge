package com.charlesdrews.data.resources.db.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.charlesdrews.data.resources.db.room.entities.Location;
import com.charlesdrews.data.resources.db.room.entities.Person;
import com.charlesdrews.data.resources.db.room.entities.Platform;

import io.reactivex.Observable;

/**
 * Defines a data access object for Person entities
 * <p>
 * Created by charlie on 11/18/17.
 */

@Dao
public interface PersonDao {
    @Insert
    void insertLocation(Location location);

    @Insert
    void insertPlatform(Platform platform);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPerson(Person person);

    @Query("SELECT * FROM PersonEntity")
    Observable<Person> getPeople();

    @Query("SELECT * FROM PersonEntity WHERE lo")
    Observable<Person> getPeopleByLocation(int locationId);

    @Query("SELECT * FROM PersonEntity")
    Observable<Person> getPeopleByPlatform(int platformId);

    @Query("SELECT * FROM PersonEntity")
    Observable<Person> getPeopleByLocationAndPlatform(int locationId, int platformId);

    @Query("SELECT * FROM PersonEntity WHERE first_name LIKE :query OR locality LIKE :query "
            + "OR platformDbEntity LIKE :query OR favorite_color LIKE :query")
    Observable<Person> searchPeople(String query);

    @Query("SELECT * FROM PersonEntity WHERE id = :personId LIMIT 1")
    Observable<Person> getPerson(int personId);

    @Query("SELECT DISTINCT platformDbEntity FROM PersonEntity ORDER BY platformDbEntity")
    Observable<String> getPlatforms();

    @Query("SELECT DISTINCT locality FROM PersonEntity ORDER BY locality")
    Observable<String> getLocations();
}
