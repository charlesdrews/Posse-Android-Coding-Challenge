package com.charlesdrews.possedemoapp.resources.db.room;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by charlie on 11/18/17.
 */

public class Location {
    @ColumnInfo(name = "public_id")
    public int publicId;

    public String locality;

    public String region;

    @ColumnInfo(name = "postal_code")
    public String postalCode;

    public String country;
}
