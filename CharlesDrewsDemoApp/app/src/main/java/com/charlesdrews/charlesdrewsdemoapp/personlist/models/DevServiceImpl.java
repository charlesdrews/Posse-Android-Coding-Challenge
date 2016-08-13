package com.charlesdrews.charlesdrewsdemoapp.personlist.models;

import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.DevService;
import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.Location;
import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.Person;

import java.util.List;

/**
 * Created by charlie on 8/13/16.
 */
public class DevServiceImpl implements DevService {
    @Override
    public int getId() {

        return 0;
    }

    @Override
    public String getPlatform() {
        return null;
    }

    @Override
    public List<Location> getLocations() {
        return null;
    }

    @Override
    public List<Person> getProgrammers() {
        return null;
    }
}
