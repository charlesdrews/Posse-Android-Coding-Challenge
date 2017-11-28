package com.charlesdrews.possedemoapp.resources.db.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Flowable;

/**
 * Created by charlie on 11/18/17.
 */

@Dao
public interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPerson(PersonEntity person);

    @Query("SELECT * FROM PersonEntity")
    Flowable<PersonEntity> getPeople();

    @Query("SELECT * FROM PersonEntity WHERE first_name LIKE :query OR locality LIKE :query "
            + "OR platform LIKE :query OR favorite_color LIKE :query")
    Flowable<PersonEntity> searchPeople(String query);

    @Query("SELECT * FROM PersonEntity WHERE id = :id LIMIT 1")
    Flowable<PersonEntity> getPerson(int id);

    @Query("SELECT DISTINCT platform FROM PersonEntity ORDER BY platform")
    Flowable<String> getPlatforms();

    @Query("SELECT DISTINCT locality FROM PersonEntity ORDER BY locality")
    Flowable<String> getLocations();
}
