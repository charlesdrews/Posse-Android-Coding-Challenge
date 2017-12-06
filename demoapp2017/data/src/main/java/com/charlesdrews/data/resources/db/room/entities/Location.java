package com.charlesdrews.data.resources.db.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by charlie on 11/18/17.
 */

public class Location {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "public_id")
    public int publicId;

    public String locality;

    public String region;

    @ColumnInfo(name = "postal_code")
    public String postalCode;

    public String country;
}
