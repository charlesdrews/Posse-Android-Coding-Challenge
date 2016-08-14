package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.charlesdrews.charlesdrewsdemoapp.data.Person;

import java.util.List;

/**
 * Created by charlie on 8/14/16.
 */
public interface PeopleListContract {

    interface View<Presenter> {
        void setPresenter(Presenter presenter);
        void showLoadingIndicator(boolean show);
        void showPeople(@NonNull List<Person> people);
        void showDataNotAvailableIndicator();
        void launchPersonDetailUi(long personId);
    }

    interface Presenter {
        void loadPeople(@Nullable String searchQuery);
        void handlePersonClicked(long personId);
    }
}
