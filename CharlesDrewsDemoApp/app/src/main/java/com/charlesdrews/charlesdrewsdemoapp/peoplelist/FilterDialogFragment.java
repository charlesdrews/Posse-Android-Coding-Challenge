package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.charlesdrews.charlesdrewsdemoapp.R;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.OnFiltersSelectedListener;

import java.util.ArrayList;

/**
 * Gives the user options to filter by platform (Android, iOS, etc.) and/or location.
 * Shows two dropdown spinners in a dialog.
 *
 * Created by charlie on 8/16/16.
 */
public class FilterDialogFragment extends DialogFragment {
    public static final String PLATFORM_LIST_KEY = "platform_list_key";
    public static final String LOCATION_LIST_KEY = "location_list_key";

    private ArrayList<String> mPlatforms, mLocations;
    private OnFiltersSelectedListener mListener;

    public FilterDialogFragment() {}

    public static FilterDialogFragment newInstance(@NonNull ArrayList<String> platforms,
                                                   @NonNull ArrayList<String> locations) {
        FilterDialogFragment fragment = new FilterDialogFragment();

        Bundle args = new Bundle();
        args.putStringArrayList(PLATFORM_LIST_KEY, platforms);
        args.putStringArrayList(LOCATION_LIST_KEY, locations);
        fragment.setArguments(args);

        return fragment;
    }

    public void setOnFiltersSelectedListener(OnFiltersSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlatforms = getArguments().getStringArrayList(PLATFORM_LIST_KEY);
        mLocations = getArguments().getStringArrayList(LOCATION_LIST_KEY);

        mPlatforms.add(0, getString(R.string.platform_filter_initial_value));
        mLocations.add(0, getString(R.string.location_filter_initial_value));
    }

    @SuppressLint("InflateParams") // Must pass null root when inflating view for AlertDialog
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Inflate spinner view group
        View spinnerContainer = LayoutInflater.from(getContext()).inflate(R.layout.filter_dialog_body, null, false);
        final Spinner platformSpinner = (Spinner) spinnerContainer.findViewById(R.id.platform_spinner);
        final Spinner locationSpinner = (Spinner) spinnerContainer.findViewById(R.id.location_spinner);

        // Set up platform Spinner
        ArrayAdapter<String> platformAapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, mPlatforms);
        platformAapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        platformSpinner.setAdapter(platformAapter);

        // Set up location Spinner
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, mLocations);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        // Set up AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.filter_dialog_title))
                .setIcon(R.drawable.ic_filter_list_black_24dp)
                .setView(spinnerContainer)
                .setNegativeButton(R.string.filter_dialog_negative_button, null)
                .setPositiveButton(R.string.filter_dialog_positive_button,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String selectedPlatform =
                                        platformSpinner.getSelectedItem().equals(mPlatforms.get(0))
                                        ? null : platformSpinner.getSelectedItem().toString();

                                String selectedLocation =
                                        locationSpinner.getSelectedItem().equals(mLocations.get(0))
                                        ? null : locationSpinner.getSelectedItem().toString();

                                mListener.onFiltersSelected(selectedPlatform, selectedLocation);
                            }
                        });

        return builder.create();
    }

}
