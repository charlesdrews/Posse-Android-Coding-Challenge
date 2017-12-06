package com.charlesdrews.possedemoapp.screens.viewpeople.master;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by charlie on 11/28/17.
 */

public class PeopleRvAdapter extends RecyclerView.Adapter<PeopleRvAdapter.PeopleViewHolder> {

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class PeopleViewHolder extends RecyclerView.ViewHolder {

        public PeopleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
