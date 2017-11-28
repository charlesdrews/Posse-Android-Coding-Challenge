package com.charlesdrews.charlesdrewsdemoapp.persondetail;

import com.charlesdrews.charlesdrewsdemoapp.BasePresenter;

/**
 * Define the responsibilities of the View and Presenter for the Person Detail feature
 *
 * Created by charlie on 8/15/16.
 */
public interface PersonDetailContract {

    interface View {

        void showLoadingIndicator(boolean show);
        void showDataNotAvailableMessage();
        void showSelectAPersonMessage();

        void showFavColor(String favColor);
        void showName(String name);
        void showPlatform(String platform);
        void showLocation(String location);
        void showLocationDetails(String locationDetails);
        void showPersonalDetails(String personalDetails);
    }

    interface Presenter extends BasePresenter<View> {

        void loadPerson(long personId);
    }
}
