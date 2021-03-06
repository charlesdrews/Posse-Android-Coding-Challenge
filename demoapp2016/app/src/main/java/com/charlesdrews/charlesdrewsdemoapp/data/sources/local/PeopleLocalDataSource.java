package com.charlesdrews.charlesdrewsdemoapp.data.sources.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Use the DatabaseHelper to persist people to the database
 *
 * Created by charlie on 8/14/16.
 */
public class PeopleLocalDataSource implements PeopleDataSource {
    private static PeopleLocalDataSource sInstance;

    private DatabaseHelper mDatabaseHelper;

    public PeopleLocalDataSource(@NonNull Context context) {
        mDatabaseHelper = DatabaseHelper.getInstance(context);
    }

    public static PeopleLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new PeopleLocalDataSource(context);
        }
        return sInstance;
    }

    @Override
    public void getPeople(@Nullable String searchQuery, @NonNull GetPeopleCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        String selection = null;
        String[] selectionArgs = null;

        if (searchQuery != null) {
            selection = DatabaseContract.PersonTable.COL_NAME_FIRST_NAME + " LIKE ? OR " +
                    DatabaseContract.PersonTable.COL_NAME_LOCALITY + " LIKE ? OR " +
                    DatabaseContract.PersonTable.COL_NAME_PLATFORM + " LIKE ? OR " +
                    DatabaseContract.PersonTable.COL_NAME_FAV_COLOR + " LIKE ?";

            String queryWithWildcards = "%" + searchQuery + "%";
            selectionArgs = new String[]{queryWithWildcards, queryWithWildcards,
                    queryWithWildcards, queryWithWildcards};
        }

        Cursor cursor = db.query(DatabaseContract.PersonTable.TABLE_NAME,
                null, selection, selectionArgs, null, null,
                DatabaseContract.PersonTable.COL_NAME_FIRST_NAME);

        List<Person> people = constructPeopleFromCursor(cursor);

        cursor.close();
        db.close();

        if (people.size() == 0) {
            callback.onDataNotAvailable();
        } else {
            callback.onPeopleLoaded(people);
        }
    }

    @Override
    public void getPerson(long personId, @NonNull GetPersonDetailCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        String selection = DatabaseContract.PersonTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(personId)};

        Cursor cursor = db.query(DatabaseContract.PersonTable.TABLE_NAME,
                null, selection, selectionArgs, null, null, null);

        Person person = null;

        if (cursor.moveToFirst()) {

            person = new Person.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(DatabaseContract.PersonTable._ID)))
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

        if (person == null) {
            callback.onDataNotAvailable();
        } else {
            callback.onPersonLoaded(person);
        }
    }

    @Override
    public void savePerson(@NonNull Person person, @NonNull SavePersonCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

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

        if (personId == -1) {
            callback.onPersonNotSaved();
        } else {
            callback.onPersonSaved(personId);
        }
    }

    @Override
    public void getPlatformAndLocationValues(@NonNull GetPlatformAndLocationValuesCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        // Group by platform to get a distinct list of platforms
        Cursor platformCursor = db.query(DatabaseContract.PersonTable.TABLE_NAME,
                new String[]{DatabaseContract.PersonTable.COL_NAME_PLATFORM},
                null, null, DatabaseContract.PersonTable.COL_NAME_PLATFORM, null,
                DatabaseContract.PersonTable.COL_NAME_PLATFORM);

        List<String> platformNames = new ArrayList<>(platformCursor.getCount());

        if (platformCursor.moveToFirst()) {
            int columnIndex = platformCursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_PLATFORM);

            while (!platformCursor.isAfterLast()) {
                platformNames.add(platformCursor.getString(columnIndex));
                platformCursor.moveToNext();
            }
        }

        // Group by locality to get a distinct list of locations
        Cursor locationCursor = db.query(DatabaseContract.PersonTable.TABLE_NAME,
                new String[]{DatabaseContract.PersonTable.COL_NAME_LOCALITY},
                null, null, DatabaseContract.PersonTable.COL_NAME_LOCALITY, null,
                DatabaseContract.PersonTable.COL_NAME_LOCALITY);

        List<String> locationNames = new ArrayList<>(locationCursor.getCount());

        if (locationCursor.moveToFirst()) {
            int columnIndex = locationCursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_LOCALITY);

            while (!locationCursor.isAfterLast()) {
                locationNames.add(locationCursor.getString(columnIndex));
                locationCursor.moveToNext();
            }
        }


        platformCursor.close();
        locationCursor.close();
        db.close();

        if (platformNames.size() == 0 && locationNames.size() == 0) {
            callback.onDataNotAvailable();
        } else {
            callback.onPlatformAndLocationValuesLoaded(platformNames, locationNames);
        }
    }

    private List<Person> constructPeopleFromCursor(Cursor cursor) {
        List<Person> people = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseContract.PersonTable._ID);
            int firstNameIndex = cursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_FIRST_NAME);
            int favColorIndex = cursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_FAV_COLOR);
            int platformIndex = cursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_PLATFORM);
            int localityIndex = cursor.getColumnIndex(DatabaseContract.PersonTable.COL_NAME_LOCALITY);

            while (!cursor.isAfterLast()) {
                Person person = new Person.Builder()
                        .setId(cursor.getLong(idIndex))
                        .setFirstName(cursor.getString(firstNameIndex))
                        .setFavColor(cursor.getString(favColorIndex))
                        .setPlatform(cursor.getString(platformIndex))
                        .setLocality(cursor.getString(localityIndex))
                        .build();

                people.add(person);

                cursor.moveToNext();
            }
        }

        return people;
    }
}
