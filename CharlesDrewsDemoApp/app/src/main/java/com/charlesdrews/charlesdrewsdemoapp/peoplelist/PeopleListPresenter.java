package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleDataSource;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleRepository;

import java.util.List;

/**
 * Created by charlie on 8/14/16.
 */
public class PeopleListPresenter implements PeopleListContract.Presenter {

    private PeopleListContract.View mPeopleView;
    private PeopleRepository mPeopleRepository;

    public PeopleListPresenter(@NonNull PeopleListContract.View peopleView,
                               @NonNull PeopleRepository peopleRepository) {
        mPeopleView = peopleView;
        mPeopleRepository = peopleRepository;
    }

    @Override
    public void loadPeople(@Nullable String searchQuery) {
        mPeopleRepository.getPeople(searchQuery, new PeopleDataSource.GetPeopleCallback() {
            @Override
            public void onPeopleLoaded(List<Person> people) {
                mPeopleView.showPeople(people);
            }

            @Override
            public void onDataNotAvailable() {
                mPeopleView.showDataNotAvailableIndicator();
            }
        });
    }

    @Override
    public void handlePersonClicked(long personId) {

    }
}
