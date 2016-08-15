package com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces;

/**
 * This will allow the views managed by the RecyclerView Adapter to communicate back to the
 * fragment, which will handle the event via the method declared below.
 *
 * Created by charlie on 8/15/16.
 */
public interface OnPersonClickedListener {
    void onPersonClicked(long personId);
}
