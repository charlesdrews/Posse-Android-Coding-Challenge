package com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.charlesdrews.charlesdrewsdemoapp.BasePresenter;
import com.charlesdrews.charlesdrewsdemoapp.data.Person;

import java.util.List;

/**
 * Define the responsibilities of the View and Presenter for the People List feature
 *
 * Created by charlie on 8/14/16.
 */
public interface PeopleContract {

    interface View {

        // Show data
        void showLoadingIndicator(boolean show);
        void showPeople(@NonNull List<Person> people);
        void showDataNotAvailableIndicator();

        // Navigation
        void launchPersonDetailUi(long personId);
    }

    interface Presenter extends BasePresenter<View> {

        // Load data
        void loadPeople(@Nullable String searchQuery);

        // Navigation
        void handlePersonClicked(long personId);
    }
}
