package com.charlesdrews.possedemoapp.screens.viewpeople;

import com.charlesdrews.domain.usecases.GetPersonInteractor;
import com.charlesdrews.domain.usecases.GetPeopleInteractor;

import javax.inject.Inject;

/**
 * This obj
 * Created by charlie on 11/28/17.
 */

public class DisplayPeoplePresenter {

    private GetPeopleInteractor getPeopleInteractorInteractor;
    private GetFilteredPeople applyLocationFilterInteractor;
    private GetPersonInteractor applyPlatformFilterInteractor;

    @Inject
    public DisplayPeoplePresenter(GetPeopleInteractor getPeopleInteractorInteractor,
                                  GetFilteredPeople applyLocationFilterInteractor,
                                  GetPersonInteractor applyPlatformFilterInteractor) {
        this.getPeopleInteractorInteractor = getPeopleInteractorInteractor;
        this.applyLocationFilterInteractor = applyLocationFilterInteractor;
        this.applyPlatformFilterInteractor = applyPlatformFilterInteractor;
    }
}
