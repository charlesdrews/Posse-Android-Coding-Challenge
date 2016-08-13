package com.charlesdrews.charlesdrewsdemoapp.personlist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.DataManager;
import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.DevService;
import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.Location;
import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.Person;

import java.util.List;

/**
 * Created by charlie on 8/13/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements DataManager {

    public DatabaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public List<Person> getAllProgrammers() {
        return null;
    }

    @Override
    public List<Person> getProgrammersByLocationId(int locationId) {
        return null;
    }

    @Override
    public List<Person> getProgrammersByDevServiceId(int devServiceId) {
        return null;
    }

    @Override
    public Person getProgrammerById(int programmerId) {
        return null;
    }

    @Override
    public List<Location> getAllLocations() {
        return null;
    }

    @Override
    public List<Location> getLocationsByDevServiceId(int devServiceId) {
        return null;
    }

    @Override
    public Location getLocationById(int locationId) {
        return null;
    }

    @Override
    public List<DevService> getAllDevServices() {
        return null;
    }

    @Override
    public List<DevService> getDevServicesByLocationId(int locationId) {
        return null;
    }

    @Override
    public DevService getDevServiceById(int devServiceId) {
        return null;
    }

    @Override
    public int addProgrammer(Person person) {
        return 0;
    }

    @Override
    public int addLocation(Location location) {
        return 0;
    }

    @Override
    public int addDevService(DevService devService) {
        return 0;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
