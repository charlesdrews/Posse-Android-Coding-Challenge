package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.charlesdrews.charlesdrewsdemoapp.R;
import com.charlesdrews.charlesdrewsdemoapp.data.Person;
import com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces.DataBinder;

/**
 * Binds data to the views in a Person list item
 * Created by charlie on 8/13/16.
 */
public class PersonViewHolder extends RecyclerView.ViewHolder
        implements DataBinder<Person> {

    private static final String TAG = "PersonViewHolder";

    private ImageView mImageView;
    private TextView mTextView;

    public PersonViewHolder(View itemView) {
        super(itemView);

        mImageView = (ImageView) itemView.findViewById(R.id.people_list_item_image);
        mTextView = (TextView) itemView.findViewById(R.id.people_list_item_name);
    }

    @Override
    public void bindData(Person data) {
        //TODO - look into other ways of parsing colors strings into colors
        //TODO - building an exception here might be too slow to keep the UI smooth
        try {
            mImageView.setBackgroundColor(Color.parseColor(data.getFavoriteColor()));
        } catch (IllegalArgumentException e) {
            Log.i(TAG, "bindData: unable to parse color string: " + data.getFavoriteColor());
            mImageView.setBackgroundColor(Color.BLACK);
        }

        mTextView.setText(data.getFirstName());
    }
}
