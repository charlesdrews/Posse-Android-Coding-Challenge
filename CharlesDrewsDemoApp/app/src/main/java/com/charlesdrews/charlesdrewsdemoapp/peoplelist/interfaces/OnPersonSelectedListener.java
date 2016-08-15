package com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces;

/**
 * This allows the fragment to communicate the selected person back to the Activity, which
 * will handle the event via the method declared below.
 *
 * Created by charlie on 8/14/16.
 */
public interface OnPersonSelectedListener {

    void onPersonSelected(long personId);

}