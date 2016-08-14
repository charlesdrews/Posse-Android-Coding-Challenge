package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.charlesdrews.charlesdrewsdemoapp.data.Person;

import java.util.List;

/**
 * Created by charlie on 8/13/16.
 */
public class PeopleRecyclerViewAdapter extends RecyclerView.Adapter<PersonViewHolder> {
    private List<Person> mPeople;

    public PeopleRecyclerViewAdapter(@NonNull List<Person> people) {
        mPeople = people;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
