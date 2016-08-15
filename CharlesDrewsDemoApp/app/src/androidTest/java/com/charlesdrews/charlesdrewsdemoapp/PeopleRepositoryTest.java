package com.charlesdrews.charlesdrewsdemoapp;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleDataSource;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleRepository;

import java.util.List;

/**
 * Created by charlie on 8/14/16.
 */
public class PeopleRepositoryTest extends ApplicationTest {
    private static final String TAG = "PeopleRepositoryTest";

    private List<Person> mPeople;

    @VisibleForTesting
    public void TestGetPeople() {
        PeopleRepository repository = Injection.getPeopleRepository(getContext().getApplicationContext());

        mPeople.clear();

        repository.getPeople(new PeopleDataSource.GetPeopleCallback() {
            @Override
            public void onPeopleLoaded(List<Person> people) {
                for (Person person : people) {
                    mPeople.add(person);
                    Log.d(TAG, "onPeopleLoaded: " + person.getFirstName());
                }
            }

            @Override
            public void onDataNotAvailable() {
                Log.d(TAG, "onDataNotAvailable: no people");
            }
        });

        assertTrue(mPeople.size() < 0);
    }
}
