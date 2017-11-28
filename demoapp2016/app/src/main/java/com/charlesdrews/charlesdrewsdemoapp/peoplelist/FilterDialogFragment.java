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
    private static final String PLATFORM_LIST_KEY = "platform_list_key";
    private static final String LOCATION_LIST_KEY = "location_list_key";
    private static final String SELECTED_PLATFORM_KEY = "selected_platform_key";
    private static final String SELECTED_LOCATION_KEY = "selected_location_key";

    private ArrayList<String> mPlatforms, mLocations;
    private String mSelectedPlatform, mSelectedLocation;
    private OnFiltersSelectedListener mListener;

    public FilterDialogFragment() {}

    public static FilterDialogFragment newInstance(@NonNull ArrayList<String> platforms,
                                                   @NonNull ArrayList<String> locations,
                                                   @Nullable String selectedPlatform,
                                                   @Nullable String selectedLocation) {
        FilterDialogFragment fragment = new FilterDialogFragment();

        Bundle args = new Bundle();
        args.putStringArrayList(PLATFORM_LIST_KEY, platforms);
        args.putStringArrayList(LOCATION_LIST_KEY, locations);
        args.putString(SELECTED_PLATFORM_KEY, selectedPlatform);
        args.putString(SELECTED_LOCATION_KEY, selectedLocation);
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
        mSelectedPlatform = getArguments().getString(SELECTED_PLATFORM_KEY);
        mSelectedLocation = getArguments().getString(SELECTED_LOCATION_KEY);

        String allPlatforms = getString(R.string.platform_filter_initial_value);
        if (!mPlatforms.get(0).equals(allPlatforms)) {
            mPlatforms.add(0, allPlatforms);
        }

        String allLocations = getString(R.string.location_filter_initial_value);
        if (!mLocations.get(0).equals(allLocations)) {
            mLocations.add(0, allLocations);
        }
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

        if (mSelectedPlatform != null && mPlatforms.contains(mSelectedPlatform)) {
            platformSpinner.setSelection(mPlatforms.indexOf(mSelectedPlatform));
        }

        // Set up location Spinner
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, mLocations);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        if (mSelectedLocation != null && mLocations.contains(mSelectedLocation)) {
            locationSpinner.setSelection(mLocations.indexOf(mSelectedLocation));
        }

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
