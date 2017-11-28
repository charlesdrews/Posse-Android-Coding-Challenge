package com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces;

import android.support.annotation.Nullable;

/**
 * This will allow the filter dialog fragment to communicate back to the people list fragment
 *
 * Created by charlie on 8/17/16.
 */
public interface OnFiltersSelectedListener {

    void onFiltersSelected(@Nullable String platformSelected, @Nullable String locationSelected);
}
