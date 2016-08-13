package com.charlesdrews.charlesdrewsdemoapp.personlist.data;

import android.provider.BaseColumns;

/**
 * Created by charlie on 8/13/16.
 */
public final class DatabaseContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "posse_data.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String INT_NOT_NULL_TYPE = " INTEGER NOT NULL";
    private static final String PRIMARY_KEY_TYPE = " INTEGER NOT NULL PRIMARY KEY";
    private static final String DOUBLE_TYPE = " REAL";

    private static final String COMMA_SEPARATOR = ", ";
    private static final String CREATE_TABLE_COMMAND = "CREATE TABLE ";
    private static final String DROP_TABLE_COMMAND = "DROP TABLE IF EXISTS ";

    // Prevent instantiation
    private DatabaseContract() {}

    /**
     * A server-side database of employees would of course be normalized and use a relational
     * structure. This database, however, will just be simple data cache for the app, so I'm
     * willing to allow a bit of redundancy in order to avoid needing joins that might slow
     * down the UI.
     *
     * In this case, the data is coming from a local file, but it could just as easily come
     * from a network call, and in that case I would be sure to page through the response data
     * to be sure only a reasonable number of records were being persisted in the database at
     * any given time, again to avoid slowing down the UI, and to avoid needing too much memory.
     */
    public static abstract class PersonTable implements BaseColumns {
        public static final String TABLE_NAME = "people";
        public static final String COL_NAME_FIRST_NAME = "first_name";
        public static final String COL_NAME_FAV_COLOR = "favorite_color";
        public static final String COL_NAME_AGE = "age";
        public static final String COL_NAME_WEIGHT = "weight";
        public static final String COL_NAME_PHONE_NUM = "phone_number";
        public static final String COL_NAME_IS_ARTIST = "is_artist";

        public static final String COL_NAME_PLATFORM = "platform";

        public static final String COL_NAME_LOCATION_PUBLIC_ID = "public_id";
        public static final String COL_NAME_LOCALITY = "locality";
        public static final String COL_NAME_REGION = "region";
        public static final String COL_NAME_POSTAL_CODE = "postal_code";
        public static final String COL_NAME_COUNTRY = "country";

        public static final String CREATE_TABLE = CREATE_TABLE_COMMAND +
                TABLE_NAME + " (" +
                _ID + PRIMARY_KEY_TYPE + COMMA_SEPARATOR +
                COL_NAME_FIRST_NAME + TEXT_TYPE + COMMA_SEPARATOR +
                COL_NAME_FAV_COLOR + TEXT_TYPE + COMMA_SEPARATOR +
                COL_NAME_AGE + INT_TYPE + COMMA_SEPARATOR +
                COL_NAME_WEIGHT + DOUBLE_TYPE + COMMA_SEPARATOR +
                COL_NAME_PHONE_NUM + TEXT_TYPE + COMMA_SEPARATOR +
                COL_NAME_IS_ARTIST + INT_TYPE + COMMA_SEPARATOR +
                COL_NAME_PLATFORM + TEXT_TYPE + COMMA_SEPARATOR +
                COL_NAME_LOCATION_PUBLIC_ID + INT_NOT_NULL_TYPE + COMMA_SEPARATOR +
                COL_NAME_LOCALITY + TEXT_TYPE + COMMA_SEPARATOR +
                COL_NAME_REGION + TEXT_TYPE + COMMA_SEPARATOR +
                COL_NAME_POSTAL_CODE + TEXT_TYPE + COMMA_SEPARATOR +
                COL_NAME_COUNTRY + TEXT_TYPE + ")";

        public static final String DROP_TABLE = DROP_TABLE_COMMAND + TABLE_NAME;
    }
}