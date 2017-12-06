package com.charlesdrews.data.resources.db.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by charlie on 11/18/17.
 */

@Entity(tableName = "people",
        foreignKeys = {
                @ForeignKey(entity = Location.class,
                        parentColumns = "id", childColumns = "location_id"),
                @ForeignKey(entity = Platform.class,
                        parentColumns = "id", childColumns = "platform_id")})
public class Person {
    @PrimaryKey(autoGenerate = true)
    public long id;

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

    @ColumnInfo(name = "location_id")
    public int locationId;

    @ColumnInfo(name = "platform_id")
    public int platformId;
}
