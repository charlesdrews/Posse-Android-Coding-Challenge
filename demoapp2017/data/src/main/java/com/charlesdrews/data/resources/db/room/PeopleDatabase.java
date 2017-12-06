package com.charlesdrews.data.resources.db.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by charlie on 11/18/17.
 */

@Database(entities = {PersonEntity.class}, version = 1)
public abstract class PeopleDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
