package com.charlesdrews.charlesdrewsdemoapp.personlist.models;

import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.Person;

/**
 * Created by charlie on 8/13/16.
 */
public class PersonImpl implements Person {
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getFavoriteColor() {
        return null;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public boolean isArtist() {
        return false;
    }

    @Override
    public int getLocationId() {
        return 0;
    }

    @Override
    public int getServiceId() {
        return 0;
    }
}
