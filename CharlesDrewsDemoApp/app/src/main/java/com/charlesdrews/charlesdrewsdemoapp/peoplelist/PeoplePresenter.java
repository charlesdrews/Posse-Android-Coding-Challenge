package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleDataSource;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleRepository;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.PeopleContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles business logic for People List and coordinates with the people repository
 *
 * Created by charlie on 8/14/16.
 */
public class PeoplePresenter implements PeopleContract.Presenter {
    private WeakReference<PeopleContract.View> mPeopleViewRef;
    private PeopleRepository mPeopleRepository;
    private List<Person> mLoadedPeople;

    private ArrayList<String> mPlatforms, mLocations;
    private String mSelectedPlatform, mSelectedLocation, mSearchQuery;

    public PeoplePresenter(@NonNull PeopleRepository peopleRepository) {
        mPeopleRepository = peopleRepository;
        mLoadedPeople = new ArrayList<>();
    }

    @Override
    public void bindView(@NonNull PeopleContract.View view) {
        mPeopleViewRef = new WeakReference<>(view);

        loadPeople(null);
    }

    @Override
    public void unbindView() {
        mPeopleViewRef.clear();
    }

    @Override
    public void loadPeople(@Nullable String searchQuery) {
        if (viewIsActive()) {
            mPeopleViewRef.get().showLoadingIndicator(true);
        }

        if (mLoadedPeople != null && mLoadedPeople.size() > 0
                && searchQuery == null && viewIsActive()) {

            mPeopleViewRef.get().showLoadingIndicator(false);
            mPeopleViewRef.get().showPeople(getFilteredPeople());

        } else {
            new LoadPeopleAsyncTask().execute(searchQuery);
        }
    }

    @Override
    public void startFilterProcess() {
        if (mPlatforms != null && mLocations != null && viewIsActive()) {
            mPeopleViewRef.get().showFilterDialog(mPlatforms, mLocations);
        } else {
            new LoadPlatformsAndLocationsAsyncTask().execute();
        }
    }

    @Override
    public void setFilters(@Nullable String selectedPlatform, @Nullable String selectedLocation) {

        mSelectedPlatform = (selectedPlatform == null || selectedPlatform.isEmpty())
                ? null : selectedPlatform;

        mSelectedLocation = (selectedLocation == null || selectedLocation.isEmpty())
                ? null : selectedLocation;

        loadPeople(mSearchQuery);
    }

    @Override
    public void handlePersonClicked(long personId) {
        if (viewIsActive()) {
            mPeopleViewRef.get().launchPersonDetailUi(personId);
        }
    }

    private boolean viewIsActive() {
        return mPeopleViewRef != null && mPeopleViewRef.get() != null;
    }

    private List<Person> getFilteredPeople() {
        // Start with all loaded people
        List<Person> filteredPeople = new ArrayList<>(mLoadedPeople);

        // Remove anyone whose platform doesn't match the filter
        if (mSelectedPlatform != null) {
            for (Person person : mLoadedPeople) {
                if (!person.getPlatform().equals(mSelectedPlatform)) {
                    filteredPeople.remove(person);
                }
            }
        }

        // Remove anyone whose location doesn't match the filter
        if (mSelectedLocation != null) {
            for (Person person : mLoadedPeople) {
                if (!person.getLocality().equals(mSelectedLocation)) {
                    filteredPeople.remove(person);
                }
            }
        }

        if (viewIsActive()) {
            if (mSelectedPlatform != null) {
                mPeopleViewRef.get().showPlatFormFilter(mSelectedPlatform);
            } else {
                mPeopleViewRef.get().hidePlatformFilter();
            }

            if (mSelectedLocation != null) {
                mPeopleViewRef.get().showLocationFilter(mSelectedLocation);
            } else {
                mPeopleViewRef.get().hideLocationFilter();
            }
        }

        return filteredPeople;
    }

    private class LoadPeopleAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            mLoadedPeople.clear();

            mPeopleRepository.getPeople(strings[0], new PeopleDataSource.GetPeopleCallback() {
                @Override
                public void onPeopleLoaded(List<Person> people) {
                    mLoadedPeople.addAll(people);
                }

                @Override
                public void onDataNotAvailable() {}
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (viewIsActive()) {
                mPeopleViewRef.get().showLoadingIndicator(false);

                if (mLoadedPeople.size() == 0) {
                    mPeopleViewRef.get().showDataNotAvailableIndicator();
                } else {
                    mPeopleViewRef.get().showPeople(getFilteredPeople());
                }
            }
        }
    }

    private class LoadPlatformsAndLocationsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            mPeopleRepository.getPlatformAndLocationValues(
                    new PeopleDataSource.GetPlatformAndLocationValuesCallback() {
                        @Override
                        public void onPlatformAndLocationValuesLoaded(List<String> platforms,
                                                                      List<String> locations) {
                            mPlatforms = new ArrayList<>(platforms);
                            mLocations = new ArrayList<>(locations);

                        }

                        @Override
                        public void onDataNotAvailable() {}
                    }
            );
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (mPlatforms != null && mLocations != null && viewIsActive()) {
                mPeopleViewRef.get().showFilterDialog(mPlatforms, mLocations);
            }
            if (viewIsActive()) {
                mPeopleViewRef.get().showUnableToFilterMessage();
            }
        }
    }
}
