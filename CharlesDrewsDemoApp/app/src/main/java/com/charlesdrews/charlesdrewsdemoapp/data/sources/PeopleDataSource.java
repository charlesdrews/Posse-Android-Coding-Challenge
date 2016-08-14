package com.charlesdrews.charlesdrewsdemoapp.data.sources;

import android.support.annotation.NonNull;

import com.charlesdrews.charlesdrewsdemoapp.data.models.PersonFullDetail;
import com.charlesdrews.charlesdrewsdemoapp.data.models.PersonMinDetail;

import java.util.List;

/**
 * Created by charlie on 8/14/16.
 */
public interface PeopleDataSource {

    interface GetPeopleMinDetailCallback {

        void onPeopleLoaded(List<PersonMinDetail> people);

        void onDataNotAvailable();
    }

    interface GetPersonFullDetailCallback {

        void onPersonLoaded(PersonFullDetail person);

        void onDataNotAvailable();
    }

    interface GetLocationAndServiceValuesCallback {

        void onLocationAndServiceValuesLoaded(List<String> locations, List<String> services);

        void onDataNotAvailable();
    }

    void getPeople(@NonNull GetPeopleMinDetailCallback callback);

    void searchPeople(@NonNull String query, @NonNull GetPeopleMinDetailCallback callback);

    void getPerson(@NonNull long personId, @NonNull GetPersonFullDetailCallback callback);

    boolean savePerson(@NonNull PersonFullDetail person);

    void getLocationAndServiceValues(@NonNull GetLocationAndServiceValuesCallback callback);
}
