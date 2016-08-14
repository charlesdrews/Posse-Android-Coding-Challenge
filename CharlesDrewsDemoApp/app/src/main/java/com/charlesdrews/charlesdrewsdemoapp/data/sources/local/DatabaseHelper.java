package com.charlesdrews.charlesdrewsdemoapp.data.sources.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Persist JSON data to database so that it does not need to be read in each time.
 * It's coming from a local file now, but could easily be a network call instead.
 * In that case it would need to cache last dataset for offline use.
 *
 * Created by charlie on 8/13/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper sInstance;

    private DatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.PersonTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.PersonTable.DROP_TABLE);
        onCreate(db);
    }
}
