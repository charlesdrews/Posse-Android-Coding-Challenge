package com.charlesdrews.charlesdrewsdemoapp.data.sources;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.charlesdrews.charlesdrewsdemoapp.data.Person;

import java.util.List;

/**
 * Created by charlie on 8/14/16.
 */
public interface PeopleDataSource {

    interface GetPeopleCallback {
        void onPeopleLoaded(List<Person> people);
        void onDataNotAvailable();
    }

    interface GetPersonDetailCallback {
        void onPersonLoaded(Person person);
        void onDataNotAvailable();
    }

    interface SavePersonCallback {
        void onPersonSaved(long savedPersonId);
        void onPersonNotSaved();
    }

    interface GetPlatformAndLocationValuesCallback {
        void onPlatformAndLocationValuesLoaded(List<String> platforms, List<String> locations);
        void onDataNotAvailable();
    }

    void getPeople(@Nullable String searchQuery, @NonNull GetPeopleCallback callback);

    void getPerson(@NonNull long personId, @NonNull GetPersonDetailCallback callback);

    void savePerson(@NonNull Person person, @NonNull SavePersonCallback callback);

    void getPlatformAndLocationValues(@NonNull GetPlatformAndLocationValuesCallback callback);
}
