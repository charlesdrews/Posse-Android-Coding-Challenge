package com.charlesdrews.possedemoapp.features.viewpeople.adapters;

import com.charlesdrews.possedemoapp.features.viewpeople.usecases.FilterByLocation;
import com.charlesdrews.possedemoapp.features.viewpeople.usecases.FilterByPlatform;
import com.charlesdrews.possedemoapp.features.viewpeople.usecases.GetPeople;

import javax.inject.Inject;

/**
 * This obj
 * Created by charlie on 11/28/17.
 */

public class DisplayPeoplePresenter {

    private GetPeople getPeopleInteractor;
    private FilterByLocation applyLocationFilterInteractor;
    private FilterByPlatform applyPlatformFilterInteractor;

    @Inject
    public DisplayPeoplePresenter(GetPeople getPeopleInteractor,
                                  FilterByLocation applyLocationFilterInteractor,
                                  FilterByPlatform applyPlatformFilterInteractor) {
        this.getPeopleInteractor = getPeopleInteractor;
        this.applyLocationFilterInteractor = applyLocationFilterInteractor;
        this.applyPlatformFilterInteractor = applyPlatformFilterInteractor;
    }
}
