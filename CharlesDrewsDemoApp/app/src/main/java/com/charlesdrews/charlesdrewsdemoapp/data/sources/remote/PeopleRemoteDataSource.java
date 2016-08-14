package com.charlesdrews.charlesdrewsdemoapp.data.sources.remote;

import android.support.annotation.NonNull;

import com.charlesdrews.charlesdrewsdemoapp.data.models.PersonFullDetail;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleDataSource;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Location;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Programmer;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Response;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Root;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Service;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * For this app, the data is being loaded from a local file in res/raw, but I wanted to set it up
 * as though it were external data coming from an HTTP request. That seems like a more realistic
 * scenario, and even the sample data file includes an HTTP status code :)
 *
 * Created by charlie on 8/14/16.
 */
public class PeopleRemoteDataSource implements PeopleDataSource {
    private static final String RAW_DATA_FILE ="raw/android_model_challenge.json";

    @Override
    public void getPeople(@NonNull GetPeopleMinDetailCallback callback) {
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(RAW_DATA_FILE);

        Reader reader = new InputStreamReader(inputStream);

        Gson gson = new Gson();

        Root dataRoot = gson.fromJson(reader, Root.class);

        for (Location location : dataRoot.getResponse().getLocations()) {

            // get location values
            String locationPublicId = location.getPublicId();
            String locality = location.getLocality();
            String region = location.getRegion();
            String postalCode = location.getPostalCode();
            

            for (Service service : location.getServices()) {
                // get service value

                for (Programmer programmer : service.getProgrammers()) {
                    // get programmer values

                    // save
                }
            }
        }
    }

    /**
     * This is just a stub - if the JSON were coming from an API instead of a local file, then
     * this method would be implemented.
     * @param query
     * @param callback
     */
    @Override
    public void searchPeople(@NonNull String query, @NonNull GetPeopleMinDetailCallback callback) {


    }

    /**
     * This is just a stub - if the JSON were coming from an API instead of a local file, then
     * this method would be implemented.
     * @param personId
     * @param callback
     */
    @Override
    public void getPerson(@NonNull long personId, @NonNull GetPersonFullDetailCallback callback) {

    }

    /**
     * This is just a stub - if the JSON were coming from an API instead of a local file, then
     * this method would be implemented.
     * @param person
     * @return
     */
    @Override
    public boolean savePerson(@NonNull PersonFullDetail person) {
        return false;
    }

    /**
     * This is just a stub - if the JSON were coming from an API instead of a local file, then
     * this method would be implemented.
     * @param callback
     */
    @Override
    public void getLocationAndServiceValues(@NonNull GetLocationAndServiceValuesCallback callback) {

    }
}
