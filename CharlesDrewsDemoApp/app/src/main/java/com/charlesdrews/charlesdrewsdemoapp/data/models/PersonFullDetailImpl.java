package com.charlesdrews.charlesdrewsdemoapp.data.models;

import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Location;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Programmer;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Service;

/**
 * Models a person with full detail needed for the detail feature.
 * Uses the builder pattern since there are so many parameters.
 *
 * Created by charlie on 8/13/16.
 */
public class PersonFullDetailImpl implements PersonFullDetail {

    private final int mId, mAge;
    private final String mFirstName, mFavColor, mPhoneNum, mPlatform, mLocationPublicId;
    private final String mLocality, mRegion, mPostalCode, mCountry;
    private final double mWeight;
    private final boolean mIsArtist;

    public PersonFullDetailImpl(
            final int id, final int age, final String locationPublicId, final String firstName,
            final String favColor, final String phoneNum, final String platform,
            final String locality, final String region, final String postalCode,
            final String country, final double weight, final boolean isArtist) {
        mId = id;
        mAge = age;
        mLocationPublicId = locationPublicId;
        mFirstName = firstName;
        mFavColor = favColor;
        mPhoneNum = phoneNum;
        mPlatform = platform;
        mLocality = locality;
        mRegion = region;
        mPostalCode = postalCode;
        mCountry = country;
        mWeight = weight;
        mIsArtist = isArtist;
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
    public int getAge() {
        return mAge;
    }

    @Override
    public double getWeight() {
        return mWeight;
    }

    @Override
    public String getPhoneNumber() {
        return mPhoneNum;
    }

    @Override
    public boolean isArtist() {
        return mIsArtist;
    }

    @Override
    public String getPlatform() {
        return mPlatform;
    }

    @Override
    public String getLocationPublicId() {
        return mLocationPublicId;
    }

    @Override
    public String getLocality() {
        return mLocality;
    }

    @Override
    public String getRegion() {
        return mRegion;
    }

    @Override
    public String getPostalCode() {
        return mPostalCode;
    }

    @Override
    public String getCountry() {
        return mCountry;
    }

    public static class Builder {
        private int mId, mAge;
        private String mFirstName, mFavColor, mPhoneNum, mPlatform, mLocationPublicId;
        private String mLocality, mRegion, mPostalCode, mCountry;
        private double mWeight;
        private boolean mIsArtist;

        public Builder setLocation(Location location) {
            mLocationPublicId = location.getPublicId();
            mLocality = location.getLocality();
            mRegion = location.getRegion();
            mPostalCode = location.getPostalCode();
            mCountry = location.getCountry();
            return this;
        }

        public Builder setService(Service service) {
            mPlatform = service.getPlatform();
            return this;
        }

        public Builder setProgrammer(Programmer programmer) {
            mFirstName = programmer.getName();
            mFavColor = programmer.getFavoriteColor();
            mAge = programmer.getAge();
            mWeight = programmer.getWeight();
            mPhoneNum = programmer.getPhone();
            mIsArtist = programmer.isIsArtist();
            return this;
        }

        public Builder setId(int id) {
            mId = id;
            return this;
        }

        public Builder setAge(int age) {
            mAge = age;
            return this;
        }

        public Builder setLocationPublicId(String locationPublicId) {
            mLocationPublicId = locationPublicId;
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

        public Builder setPhoneNum(String phoneNum) {
            mPhoneNum = phoneNum;
            return this;
        }

        public Builder setPlatform(String platform) {
            mPlatform = platform;
            return this;
        }

        public Builder setLocality(String locality) {
            mLocality = locality;
            return this;
        }

        public Builder setRegion(String region) {
            mRegion = region;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            mPostalCode = postalCode;
            return this;
        }

        public Builder setCountry(String country) {
            mCountry = country;
            return this;
        }

        public Builder setWeight(double weight) {
            mWeight = weight;
            return this;
        }

        public Builder setIsArtist(boolean isArtist) {
            mIsArtist = isArtist;
            return this;
        }

        public PersonFullDetailImpl build() {
            return new PersonFullDetailImpl(mId, mAge, mLocationPublicId, mFirstName, mFavColor, mPhoneNum,
                    mPlatform, mLocality, mRegion, mPostalCode, mCountry, mWeight, mIsArtist);
        }
    }
}
