package com.charlesdrews.charlesdrewsdemoapp.personlist.models;

import com.charlesdrews.charlesdrewsdemoapp.personlist.interfaces.PersonMinDetail;

/**
 * Created by charlie on 8/13/16.
 */
public class PersonMinDetailImpl implements PersonMinDetail {
    private final int mId;
    private final String mFirstName, mFavColor, mLocality, mPlatform;

    private PersonMinDetailImpl(final int id, final String firstName, final String favColor,
                               final String locality, final String platform) {
        mId = id;
        mFirstName = firstName;
        mFavColor = favColor;
        mLocality = locality;
        mPlatform = platform;
    }

    @Override
    public int getId() {
        return mId;
    }

    @Override
    public String getFirstName() {
        return mFirstName;
    }

    @Override
    public String getFavoriteColor() {
        return mFavColor;
    }

    @Override
    public String getLocality() {
        return mLocality;
    }

    @Override
    public String getPlatform() {
        return mPlatform;
    }

    public static class Builder {
        private int mId;
        private String mFirstName;
        private String mFavColor;
        private String mLocality;
        private String mPlatform;

        public Builder setId(int id) {
            mId = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            mFirstName = firstName;
            return this;
        }

        public Builder setFavColor(String favColor) {
            mFavColor = favColor;
            return this;
        }

        public Builder setLocality(String locality) {
            mLocality = locality;
            return this;
        }

        public Builder setPlatform(String platform) {
            mPlatform = platform;
            return this;
        }

        public PersonMinDetailImpl build() {
            return new PersonMinDetailImpl(mId, mFirstName, mFavColor, mLocality, mPlatform);
        }
    }
}
