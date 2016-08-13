package com.charlesdrews.charlesdrewsdemoapp.personlist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.DataManager;
import com.charlesdrews.charlesdrewsdemoapp.persondetail.interfaces.PersonFullDetail;
import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.PersonMinDetail;
import com.charlesdrews.charlesdrewsdemoapp.persondetail.models.PersonFullDetailImpl;
import com.charlesdrews.charlesdrewsdemoapp.personlist.models.PersonMinDetailImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Persist JSON data to database so that it does not need to be read in each time.
 * It's coming from a local file now, but could easily be a network call instead.
 * In that case it would need to cache last dataset for offline use.
 *
 * Created by charlie on 8/13/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements DataManager {
    private static DatabaseHelper sInstance;

    private DatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    public DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
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

    @Override
    public List<PersonMinDetail> getAllPeopleForListFeature() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(DatabaseContract.PersonTable.TABLE_NAME,
                null, null, null, null, null,
                DatabaseContract.PersonTable.COL_NAME_FIRST_NAME);

        List<PersonMinDetail> people = constructPeopleFromCursor(cursor);

        cursor.close();
        db.close();
        return people;
    }

    @Override
    public List<PersonMinDetail> searchPeopleForListFeature(String query) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = DatabaseContract.PersonTable.COL_NAME_FIRST_NAME + " = ? OR " +
                DatabaseContract.PersonTable.COL_NAME_LOCALITY + " = ? OR " +
                DatabaseContract.PersonTable.COL_NAME_PLATFORM + " = ? OR " +
                DatabaseContract.PersonTable.COL_NAME_FAV_COLOR + " = ?";

        String[] selectionArgs = {query, query, query, query};

        Cursor cursor = db.query(DatabaseContract.PersonTable.TABLE_NAME,
                null, selection, selectionArgs, null, null,
                DatabaseContract.PersonTable.COL_NAME_FIRST_NAME);

        List<PersonMinDetail> people = constructPeopleFromCursor(cursor);

        cursor.close();
        db.close();
        return people;
    }

    @Override
    public PersonFullDetail getFullPersonDetailById(long personId) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = DatabaseContract.PersonTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(personId)};

        Cursor cursor = db.query(DatabaseContract.PersonTable.TABLE_NAME,
                null, selection, selectionArgs, null, null, null);

        PersonFullDetail person = null;

        if (cursor.moveToFirst()) {

            person = new PersonFullDetailImpl.Builder()
                    .setFirstName(cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_FIRST_NAME)))
                    .setFavColor(cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_FAV_COLOR)))
                    .setAge(cursor.getInt(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_AGE)))
                    .setWeight(cursor.getDouble(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_WEIGHT)))
                    .setPhoneNum(cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_PHONE_NUM)))
                    .setIsArtist(cursor.getInt(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_IS_ARTIST)) == 1)
                    .setPlatform(cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_PLATFORM)))
                    .setLocationPublicId(cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_LOCATION_PUBLIC_ID)))
                    .setLocality(cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_LOCALITY)))
                    .setRegion(cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_REGION)))
                    .setPostalCode(cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_POSTAL_CODE)))
                    .setCountry(cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.PersonTable.COL_NAME_COUNTRY)))
                    .build();
        }

        cursor.close();
        db.close();
        return person;
    }

    /**
     * Saves a PersonFullDetail to the database
     * @param person
     * @return the id value for the PersonFullDetail if added successfully, else -1
     */
    @Override
    public long addPerson(PersonFullDetail person) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.PersonTable.COL_NAME_AGE, person.getAge());
        values.put(DatabaseContract.PersonTable.COL_NAME_COUNTRY, person.getCountry());
        values.put(DatabaseContract.PersonTable.COL_NAME_FAV_COLOR, person.getFavoriteColor());
        values.put(DatabaseContract.PersonTable.COL_NAME_FIRST_NAME, person.getFirstName());
        values.put(DatabaseContract.PersonTable.COL_NAME_IS_ARTIST, person.isArtist());
        values.put(DatabaseContract.PersonTable.COL_NAME_LOCALITY, person.getLocality());
        values.put(DatabaseContract.PersonTable.COL_NAME_LOCATION_PUBLIC_ID,
                person.getLocationPublicId());
        values.put(DatabaseContract.PersonTable.COL_NAME_PHONE_NUM, person.getPhoneNumber());
        values.put(DatabaseContract.PersonTable.COL_NAME_PLATFORM, person.getPlatform());
        values.put(DatabaseContract.PersonTable.COL_NAME_POSTAL_CODE, person.getPostalCode());
        values.put(DatabaseContract.PersonTable.COL_NAME_REGION, person.getRegion());
        values.put(DatabaseContract.PersonTable.COL_NAME_WEIGHT, person.getWeight());

        long personId = db.insert(DatabaseContract.PersonTable.TABLE_NAME, null, values);

        db.close();
        return personId;
    }

    @Override
    public List<String> getLocationNames() {
        SQLiteDatabase db = getReadableDatabase();

        // Group by locality to get a distinct list of locations
        Cursor cursor = db.query(DatabaseContract.PersonTable.TABLE_NAME,
                new String[]{DatabaseContract.PersonTable.COL_NAME_LOCALITY},
                null, null, DatabaseContract.PersonTable.COL_NAME_LOCALITY, null,
                DatabaseContract.PersonTable.COL_NAME_LOCALITY);

        List<String> locationNames = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_LOCALITY);

            while (!cursor.isAfterLast()) {
                locationNames.add(cursor.getString(columnIndex));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return locationNames;
    }

    @Override
    public List<String> getPlatformNames() {
        SQLiteDatabase db = getReadableDatabase();

        // Group by platform to get a distinct list of platforms
        Cursor cursor = db.query(DatabaseContract.PersonTable.TABLE_NAME,
                new String[]{DatabaseContract.PersonTable.COL_NAME_PLATFORM},
                null, null, DatabaseContract.PersonTable.COL_NAME_PLATFORM, null,
                DatabaseContract.PersonTable.COL_NAME_PLATFORM);

        List<String> platformNames = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_PLATFORM);

            while (!cursor.isAfterLast()) {
                platformNames.add(cursor.getString(columnIndex));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return platformNames;
    }

    private List<PersonMinDetail> constructPeopleFromCursor(Cursor cursor) {
        List<PersonMinDetail> people = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            int firstNameIndex = cursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_FIRST_NAME);
            int favColorIndex = cursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_FAV_COLOR);
            int platformIndex = cursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_PLATFORM);
            int localityIndex = cursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_LOCALITY);

            while (!cursor.isAfterLast()) {
                PersonMinDetail person = new PersonMinDetailImpl.Builder()
                        .setFirstName(cursor.getString(firstNameIndex))
                        .setFavColor(cursor.getString(favColorIndex))
                        .setPlatform(cursor.getString(platformIndex))
                        .setPlatform(cursor.getString(localityIndex))
                        .build();

                people.add(person);

                cursor.moveToNext();
            }
        }

        return people;
    }
}
