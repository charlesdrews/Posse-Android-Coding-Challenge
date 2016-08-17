package com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.charlesdrews.charlesdrewsdemoapp.BasePresenter;
import com.charlesdrews.charlesdrewsdemoapp.data.Person;

import java.util.ArrayList;
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

        // Filter and search
        void showFilterDialog(@NonNull ArrayList<String> platforms, @NonNull ArrayList<String> locations);
        void showUnableToFilterMessage();
        void showFilterBar(boolean show);
        void showPlatFormFilter(@NonNull String platformFilter);
        void hidePlatformFilter();
        void showLocationFilter(@NonNull String locationFilter);
        void hideLocationFilter();

        // Navigation
        void launchPersonDetailUi(long personId);
    }

    interface Presenter extends BasePresenter<View> {

        // Load data
        void loadPeople(@Nullable String searchQuery);

        // Filter and search
        void startFilterProcess();
        void applyPlatformFilter(@NonNull String platformFilter);
        void removePlatformFilter();
        void applyLocationFilter(@NonNull String locationFilter);
        void removeLocationFilter();

        // Navigation
        void handlePersonClicked(long personId);
    }
}
