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
            mPeopleViewRef.get().showPeople(mLoadedPeople);

        } else {
            new LoadPeopleAsyncTask().execute(searchQuery);
        }
    }

    @Override
    public void startFilterProcess() {
        if (mPlatforms != null && mLocations != null && viewIsActive()) {
            mPeopleViewRef.get().showFilterDialog(mPlatforms, mLocations);
        } else {
            mPeopleRepository.getPlatformAndLocationValues(
                    new PeopleDataSource.GetPlatformAndLocationValuesCallback() {
                        @Override
                        public void onPlatformAndLocationValuesLoaded(List<String> platforms,
                                                                      List<String> locations) {
                            if (viewIsActive()) {
                                mPeopleViewRef.get()
                                        .showFilterDialog(new ArrayList<>(platforms),
                                                new ArrayList<>(locations));
                            }
                        }

                        @Override
                        public void onDataNotAvailable() {
                            if (viewIsActive()) {
                                mPeopleViewRef.get().showUnableToFilterMessage();
                            }
                        }
                    }
            );
        }
    }

    @Override
    public void applyPlatformFilter(@NonNull String platformFilter) {

    }

    @Override
    public void removePlatformFilter() {

    }

    @Override
    public void applyLocationFilter(@NonNull String locationFilter) {

    }

    @Override
    public void removeLocationFilter() {

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
                    mPeopleViewRef.get().showPeople(mLoadedPeople);
                }
            }
        }
    }
}
