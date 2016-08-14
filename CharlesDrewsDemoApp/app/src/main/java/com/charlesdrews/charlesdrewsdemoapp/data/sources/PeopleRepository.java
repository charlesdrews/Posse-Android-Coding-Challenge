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
                                    Log.d(TAG, "onPersonNotSaved: error saving" +
                                            person.getFirstName() + " to local data source");
                                }
                            });
                        }

                        // If need to apply query, use now-populated local data source
                        if (searchQuery == null) {
                            callback.onPeopleLoaded(people);
                        } else {
                            mLocalDataSource.getPeople(searchQuery, callback);
                        }
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });
            }
        });
    }

    @Override
    public void getPerson(@NonNull long personId, @NonNull final GetPersonDetailCallback callback) {
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
    public void getLocationAndServiceValues(@NonNull final GetLocationAndServiceValuesCallback callback) {
        mLocalDataSource.getLocationAndServiceValues(new GetLocationAndServiceValuesCallback() {
            @Override
            public void onLocationAndServiceValuesLoaded(List<String> locations, List<String> services) {
                callback.onLocationAndServiceValuesLoaded(locations, services);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
