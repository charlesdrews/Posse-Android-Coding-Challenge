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
    private WeakReference<PersonDetailContract.View> mPersonViewRef;
    private PeopleRepository mPeopleRepository;
    private Person mLoadedPerson;

    public PersonDetailPresenter(PeopleRepository peopleRepository) {
        mPeopleRepository = peopleRepository;
    }

    @Override
    public void bindView(@NonNull PersonDetailContract.View view) {
        mPersonViewRef = new WeakReference<>(view);

        if (mLoadedPerson != null) {
            showLoadedPerson();
        } else {
            mPersonViewRef.get().showSelectAPersonMessage();
        }
    }

    @Override
    public void unbindView() {
        mPersonViewRef.clear();
    }

    @Override
    public void loadPerson(long personId) {

        if (viewIsActive()) {
            mPersonViewRef.get().showLoadingIndicator(true);
        }

        if (mLoadedPerson != null && mLoadedPerson.getId() == personId && viewIsActive()) {
            showLoadedPerson();
        } else {
            new LoadPersonAsyncTask().execute(personId);
        }

    }

    private void showLoadedPerson() {
        mPersonViewRef.get().showLoadingIndicator(false);

        mPersonViewRef.get().showFavColor(mLoadedPerson.getFavoriteColor());
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
            mLoadedPerson = null;
            mPeopleRepository.getPerson(longs[0], new PeopleDataSource.GetPersonDetailCallback() {
                @Override
                public void onPersonLoaded(Person person) {
                    mLoadedPerson = person;
                }

                @Override
                public void onDataNotAvailable() {
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (viewIsActive()) {
                if (mLoadedPerson != null) {
                    showLoadedPerson();
                } else {
                    mPersonViewRef.get().showDataNotAvailableMessage();
                }
            }

        }
    }
}
