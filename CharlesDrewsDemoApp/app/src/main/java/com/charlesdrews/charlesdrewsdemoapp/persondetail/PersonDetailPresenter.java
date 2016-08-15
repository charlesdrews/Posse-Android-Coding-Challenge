package com.charlesdrews.charlesdrewsdemoapp.persondetail;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleDataSource;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleRepository;

import java.lang.ref.WeakReference;

/**
 * Handles business logic for Person Detail and coordinates with the people repository
 *
 * Created by charlie on 8/15/16.
 */
public class PersonDetailPresenter implements PersonDetailContract.Presenter {
    private static final String TAG = "PersonDetailPresenter";

    private WeakReference<PersonDetailContract.View> mPersonViewRef;
    private PeopleRepository mPeopleRepository;
    private Person mLoadedPerson;

    public PersonDetailPresenter(PeopleRepository peopleRepository) {
        mPeopleRepository = peopleRepository;
    }

    @Override
    public void bindView(@NonNull PersonDetailContract.View view) {
        Log.d(TAG, "bindView: binding...");

        mPersonViewRef = new WeakReference<>(view);

        if (mLoadedPerson != null) {
            Log.d(TAG, "bindView: person already loaded");
            showLoadedPerson();
        } else {
            Log.d(TAG, "bindView: no person loaded yet; please select a person");
            mPersonViewRef.get().showSelectAPersonMessage();
        }
    }

    @Override
    public void unbindView() {
        Log.d(TAG, "unbindView: unbinding...");
        mPersonViewRef.clear();
    }

    @Override
    public void loadPerson(long personId) {
        Log.d(TAG, "loadPerson: starting to load person");
        
        if (viewIsActive()) {
            Log.d(TAG, "loadPerson: show progress bar");
            mPersonViewRef.get().showLoadingIndicator(true);
        }

        if (mLoadedPerson != null && mLoadedPerson.getId() == personId && viewIsActive()) {
            Log.d(TAG, "loadPerson: person all set to go");
            showLoadedPerson();
        } else {
            Log.d(TAG, "loadPerson: launching async task to load person");
            new LoadPersonAsyncTask().execute(personId);
        }

    }

    private void showLoadedPerson() {
        Log.d(TAG, "showLoadedPerson: ");
        mPersonViewRef.get().showLoadingIndicator(false);

        mPersonViewRef.get().showName(mLoadedPerson.getFirstName());
        mPersonViewRef.get().showPlatform(mLoadedPerson.getPlatform());
        mPersonViewRef.get().showLocation(mLoadedPerson.getLocality());

        StringBuilder builder = new StringBuilder();
        builder.append(mLoadedPerson.getRegion()).append("\n")
                .append(mLoadedPerson.getPostalCode()).append("\n")
                .append(mLoadedPerson.getCountry());
        mPersonViewRef.get().showLocationDetails(builder.toString());

        builder = new StringBuilder();
        builder.append("Phone number: ").append(mLoadedPerson.getPhoneNumber()).append("\n")
                .append("Favorite color: ").append(mLoadedPerson.getFavoriteColor()).append("\n")
                .append("Age: ").append(mLoadedPerson.getAge()).append("\n")
                .append("Weight: ").append(mLoadedPerson.getWeight());
        mPersonViewRef.get().showPersonalDetails(builder.toString());
    }

    private boolean viewIsActive() {
        return mPersonViewRef != null && mPersonViewRef.get() != null;
    }

    private class LoadPersonAsyncTask extends AsyncTask<Long, Void, Void> {

        @Override
        protected Void doInBackground(Long... longs) {
            Log.d(TAG, "doInBackground: loading person from repository in background, with id " + longs[0]);
            mLoadedPerson = null;
            mPeopleRepository.getPerson(longs[0], new PeopleDataSource.GetPersonDetailCallback() {
                @Override
                public void onPersonLoaded(Person person) {
                    Log.d(TAG, "onPersonLoaded: repository returned person: " + person.getFirstName());
                    mLoadedPerson = person;
                }

                @Override
                public void onDataNotAvailable() {
                    Log.d(TAG, "onDataNotAvailable: repository unable to return person");
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.d(TAG, "onPostExecute: done loading person");
            if (viewIsActive()) {
                if (mLoadedPerson != null) {
                    Log.d(TAG, "onPostExecute: person set, show person");
                    showLoadedPerson();
                } else {
                    Log.d(TAG, "onPostExecute: failed to load person");
                    mPersonViewRef.get().showDataNotAvailableMessage();
                }
            }

        }
    }
}
