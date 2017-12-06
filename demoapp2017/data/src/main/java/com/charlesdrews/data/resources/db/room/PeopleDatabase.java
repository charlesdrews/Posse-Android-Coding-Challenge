package com.charlesdrews.data.resources.db.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.charlesdrews.data.resources.db.room.entities.Location;
import com.charlesdrews.data.resources.db.room.entities.Person;
import com.charlesdrews.data.resources.db.room.entities.Platform;

/**
 * Created by charlie on 11/18/17.
 */

@Database(entities = {Person.class, Location.class, Platform.class}, version = 1)
public abstract class PeopleDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
