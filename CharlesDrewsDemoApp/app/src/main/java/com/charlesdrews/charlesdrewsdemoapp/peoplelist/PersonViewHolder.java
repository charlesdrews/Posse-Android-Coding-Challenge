package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.charlesdrews.charlesdrewsdemoapp.data.Person;

/**
 * Created by charlie on 8/13/16.
 */
public class PersonViewHolder extends RecyclerView.ViewHolder
        implements DataBinder<Person> {

    public PersonViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public boolean bindData(Person data) {
        return false;
    }
}
