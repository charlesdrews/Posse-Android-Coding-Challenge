package com.charlesdrews.possedemoapp.resources.db.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by charlie on 11/18/17.
 */

@Entity(tableName = "people")
public class PersonEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "favorite_color")
    public String favoriteColor;

    public int age;

    public double weight;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "is_artist")
    public boolean isArtist;

    @Embedded
    public Platform platform;

    @Embedded
    public Location location;
}
