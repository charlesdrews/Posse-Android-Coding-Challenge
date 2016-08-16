package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.charlesdrews.charlesdrewsdemoapp.ColorUtil;
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
    private TextView mNameView, mDescriptionView;

    public PersonViewHolder(View itemView) {
        super(itemView);

        mImageView = (ImageView) itemView.findViewById(R.id.people_list_item_image);
        mNameView = (TextView) itemView.findViewById(R.id.people_list_item_name);
        mDescriptionView = (TextView) itemView.findViewById(R.id.people_list_item_description);
    }

    @Override
    public void bindData(Person data) {
        int color = ColorUtil.ParseColorByName(data.getFavoriteColor());
        if (color == -1) {
            color = ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary);
        }

        mImageView.setBackgroundColor(color);
        mNameView.setText(data.getFirstName());
        mDescriptionView.setText(mDescriptionView.getContext().getString(
                R.string.person_list_item_description, data.getPlatform(), data.getLocality()));
    }
}
