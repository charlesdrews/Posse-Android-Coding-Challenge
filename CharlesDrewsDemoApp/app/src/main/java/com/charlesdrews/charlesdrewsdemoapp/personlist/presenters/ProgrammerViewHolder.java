package com.charlesdrews.charlesdrewsdemoapp.personlist.presenters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.DataBinder;
import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.Programmer;

/**
 * Created by charlie on 8/13/16.
 */
public class ProgrammerViewHolder extends RecyclerView.ViewHolder
        implements DataBinder<Programmer> {

    public ProgrammerViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public boolean bindData(Programmer data) {
        return false;
    }
}