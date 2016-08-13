package com.charlesdrews.charlesdrewsdemoapp.personlist.models;

import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.DevService;
import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.Location;
import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.Programmer;

import java.util.List;

/**
 * Created by charlie on 8/13/16.
 */
public class LocationImpl implements Location {
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getRegion() {
        return null;
    }

    @Override
    public String getPostalCode() {
        return null;
    }

    @Override
    public String getCountry() {
        return null;
    }

    @Override
    public List<DevService> getDevServices() {
        return null;
    }

    @Override
    public List<Programmer> getProgrammers() {
        return null;
    }
}
