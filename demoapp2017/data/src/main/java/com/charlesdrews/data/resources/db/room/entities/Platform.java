package com.charlesdrews.data.resources.db.room.entities;

import android.arch.persistence.room.PrimaryKey;

/**
 * Created by charlie on 11/18/17.
 */

public class Platform {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String platform;
}
