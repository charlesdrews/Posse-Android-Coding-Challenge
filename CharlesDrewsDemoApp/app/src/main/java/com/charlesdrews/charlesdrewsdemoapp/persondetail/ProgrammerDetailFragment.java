package com.charlesdrews.charlesdrewsdemoapp.persondetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlesdrews.charlesdrewsdemoapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgrammerDetailFragment extends Fragment {


    public ProgrammerDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_programmer_detail, container, false);
    }

}
