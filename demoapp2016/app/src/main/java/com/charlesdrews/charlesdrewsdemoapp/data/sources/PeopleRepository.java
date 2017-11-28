package com.charlesdrews.charlesdrewsdemoapp.data.sources;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.local.PeopleLocalDataSource;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.PeopleRemoteDataSource;

import java.util.List;

/**
 * Provide a people repository by utilizing both local and remote data sources
 *
 * Created by charlie on 8/14/16.
 */
public class PeopleRepository implements PeopleDataSource {
    private static final String TAG = "PeopleRepository";
    
    private static PeopleRepository sInstance;

    private PeopleDataSource mLocalDataSource;
    private PeopleDataSource mRemoteDataSource;

    private PeopleRepository(@NonNull PeopleLocalDataSource localDataSource,
                             @NonNull PeopleRemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static PeopleRepository getInstance(@NonNull PeopleLocalDataSource localDataSource,
                                               @NonNull PeopleRemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new PeopleRepository(localDataSource, remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void getPeople(@Nullable final String searchQuery, @NonNull final GetPeopleCallback callback) {

        // Try to get people from local source first
        mLocalDataSource.getPeople(searchQuery, new GetPeopleCallback() {
            @Override
            public void onPeopleLoaded(List<Person> people) {
                callback.onPeopleLoaded(people);
            }

            // If people are not available from local source, use remote source
            @Override
            public void onDataNotAvailable() {

                // Careful not to re-load from remote in the case of a query w/ no results
                if (searchQuery == null) {

                    mRemoteDataSource.getPeople(null, new GetPeopleCallback() {
                        @Override
                        public void onPeopleLoaded(final List<Person> people) {

                            // Save people to local source
                            for (final Person person : people) {
                                mLocalDataSource.savePerson(person, new SavePersonCallback() {
                                    @Override
                                    public void onPersonSaved(long savedPersonId) {
                                        Log.i(TAG, "onPersonSaved: " + person.getFirstName() +
                                                " saved to local data source w/ id "
                                                + savedPersonId);
                                    }

                                    @Override
                                    public void onPersonNotSaved() {
                                        Log.w(TAG, "onPersonNotSaved: error saving" +
                                                person.getFirstName() + " to local data source");
                                    }
                                });
                            }

                            // This list of people doesn't include id values, since they came
                            // straight from the remote source, not from the database.
                            // Query the local database before returning, to be sure we have ids.
                            mLocalDataSource.getPeople(null, callback);
                        }

                        @Override
                        public void onDataNotAvailable() {
                            callback.onDataNotAvailable();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void getPerson(long personId, @NonNull final GetPersonDetailCallback callback) {
        mLocalDataSource.getPerson(personId, new GetPersonDetailCallback() {
            @Override
            public void onPersonLoaded(Person person) {
                callback.onPersonLoaded(person);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void savePerson(@NonNull Person person, @NonNull final SavePersonCallback callback) {
        mLocalDataSource.savePerson(person, new SavePersonCallback() {
            @Override
            public void onPersonSaved(long savedPersonId) {
                callback.onPersonSaved(savedPersonId);
            }

            @Override
            public void onPersonNotSaved() {
                callback.onPersonNotSaved();
            }
        });
    }

    @Override
    public void getPlatformAndLocationValues(@NonNull final GetPlatformAndLocationValuesCallback callback) {
        mLocalDataSource.getPlatformAndLocationValues(new GetPlatformAndLocationValuesCallback() {
            @Override
            public void onPlatformAndLocationValuesLoaded(List<String> platforms, List<String> locations) {
                callback.onPlatformAndLocationValuesLoaded(platforms, locations);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
