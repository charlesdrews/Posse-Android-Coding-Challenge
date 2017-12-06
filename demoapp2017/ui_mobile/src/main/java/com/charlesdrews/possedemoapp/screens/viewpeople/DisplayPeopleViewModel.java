package com.charlesdrews.possedemoapp.screens.viewpeople;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

/**
 * Created by charlie on 11/18/17.
 */

public class DisplayPeopleViewModel extends ViewModel {
    //TODO remove this
    public final String hello = "hello from the DisplayPeopleViewModel";

    private DisplayPeoplePresenter presenter;

    @Inject
    public DisplayPeopleViewModel(DisplayPeoplePresenter presenter) {
        this.presenter = presenter;
    }
}
