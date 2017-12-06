package com.charlesdrews.possedemoapp.screens.viewpeople;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.charlesdrews.possedemoapp.R;

import dagger.android.AndroidInjection;

public class DisplayPeopleActivity extends AppCompatActivity {

    private static final String TAG = "DisplayPeopleActivity";

    private DisplayPeopleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(DisplayPeopleViewModel.class);

        Log.d(TAG, "onCreate: " + viewModel.hello);
    }
}
