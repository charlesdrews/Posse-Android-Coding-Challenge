package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

/**
 * Created by charlie on 8/14/16.
 */
public interface PeopleListContract {
    interface View {
        void reportPersonClicked();
    }

    interface Presenter {
        void handlePersonClicked(long personId);
    }
}
