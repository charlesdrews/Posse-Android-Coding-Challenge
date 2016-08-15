package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlesdrews.charlesdrewsdemoapp.R;
import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.OnPersonClickedListener;

import java.util.List;

/**
 * Binds data from Person instances to views.
 *
 * Created by charlie on 8/13/16.
 */
public class PeopleRecyclerViewAdapter extends RecyclerView.Adapter<PersonViewHolder> {
    private static final String TAG = "PeopleRvAdapter";
    
    private List<Person> mPeople;
    private OnPersonClickedListener mOnPersonClickedListener;

    public PeopleRecyclerViewAdapter(@NonNull List<Person> people,
                                     @NonNull OnPersonClickedListener listener) {
        mPeople = people;
        mOnPersonClickedListener =  listener;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.people_list_item, parent, false);

        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, int position) {
        holder.bindData(mPeople.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: position clicked: " + holder.getAdapterPosition());
                Person person = mPeople.get(holder.getAdapterPosition());
                Log.d(TAG, "onClick: person name: " + person.getFirstName());
                Log.d(TAG, "onClick: person id: " + person.getId());
                Log.d(TAG, "onClick: ViewHolder is telling fragment it got clicked & it's person's id is " + mPeople.get(holder.getAdapterPosition()).getId());
                mOnPersonClickedListener.onPersonClicked(mPeople.get(holder.getAdapterPosition()).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

}
