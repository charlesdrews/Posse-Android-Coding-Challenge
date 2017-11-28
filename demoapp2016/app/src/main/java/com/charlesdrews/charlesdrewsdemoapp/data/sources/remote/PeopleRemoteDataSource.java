package com.charlesdrews.charlesdrewsdemoapp.data.sources.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.charlesdrews.charlesdrewsdemoapp.R;
import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleDataSource;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Location;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Programmer;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Root;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Service;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * For this app, the data is being loaded from a local file in res/raw, but I wanted to set it up
 * as though it were external data coming from an HTTP request. That seems like a more realistic
 * scenario, and even the sample data file includes an HTTP status code :)
 *
 * Created by charlie on 8/14/16.
 */
public class PeopleRemoteDataSource implements PeopleDataSource {
    private static final String TAG = "PeopleRemoteDataSource";

    private static PeopleRemoteDataSource sInstance;

    // If this really were getting remote data via HTTP I wouldn't need this context,
    // but to read a local file from res/raw I do need it for now
    private WeakReference<Context> mContextWeakReference;

    private PeopleRemoteDataSource(@NonNull Context context) {
        mContextWeakReference = new WeakReference<>(context);
    }

    public static PeopleRemoteDataSource getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new PeopleRemoteDataSource(context);
        }
        return sInstance;
    }

    /**
     * Reads json from local file, converts to objects, returns Person objects
     * @param searchQuery is currently ignored, since this is not making an API call
     * @param callback to be implemented by the caller
     */
    @Override
    public void getPeople(@Nullable String searchQuery, @NonNull GetPeopleCallback callback) {
        InputStream raw = mContextWeakReference.get().getResources()
                .openRawResource(R.raw.android_model_challenge);

        Reader reader = new BufferedReader(new InputStreamReader(raw));

        Gson gson = new Gson();

        Root dataRoot;

        try {
            dataRoot = gson.fromJson(reader, Root.class);
        } catch (Exception e) {
            Log.e(TAG, "getPeople: error converting json file via gson", e);
            callback.onDataNotAvailable();
            return;
        }

        List<Person> people = new ArrayList<>();

        for (Location location : dataRoot.getResponse().getLocations()) {
            for (Service service : location.getServices()) {
                for (Programmer programmer : service.getProgrammers()) {

                    Person person = new Person.Builder()
                            .setLocation(location)
                            .setService(service)
                            .setProgrammer(programmer)
                            .build();

                    people.add(person);
                }
            }
        }

        if (people.size() == 0) {
            callback.onDataNotAvailable();
        }

        callback.onPeopleLoaded(people);
    }

    /**
     * This is just a stub - if the JSON were coming from an API instead of a local file, then
     * this method would be implemented.
     * @param personId long
     * @param callback to be implemented by the caller
     */
    @Override
    public void getPerson(long personId, @NonNull GetPersonDetailCallback callback) {
        callback.onDataNotAvailable();
    }

    /**
     * This is just a stub - if the JSON were coming from an API instead of a local file, then
     * this method would be implemented.
     * @param person Person instance
     * @param callback to be implemented by the caller
     */
    @Override
    public void savePerson(@NonNull Person person, @NonNull SavePersonCallback callback) {
        callback.onPersonNotSaved();
    }

    /**
     * This is just a stub - if the JSON were coming from an API instead of a local file, then
     * this method would be implemented.
     * @param callback to be implemented by the caller
     */
    @Override
    public void getPlatformAndLocationValues(@NonNull GetPlatformAndLocationValuesCallback callback) {
        callback.onDataNotAvailable();
    }
}
