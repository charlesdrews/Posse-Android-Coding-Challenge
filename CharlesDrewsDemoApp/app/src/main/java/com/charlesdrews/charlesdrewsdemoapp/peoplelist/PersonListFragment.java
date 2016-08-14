package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlesdrews.charlesdrewsdemoapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PersonListFragment extends Fragment {

    public PersonListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_list, container, false);
    }
}
