package com.charlesdrews.charlesdrewsdemoapp.persondetail.interfaces;

import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.PersonMinDetail;

/**
 * Full detail needed for person detail feature
 * Created by charlie on 8/13/16.
 */
public interface PersonFullDetail extends PersonMinDetail {
    // Attributes not included in PersonMinDetail
    int getAge();
    double getWeight();
    String getPhoneNumber();
    boolean isArtist();
    String getLocationPublicId();
    String getRegion();
    String getPostalCode();
    String getCountry();
}
