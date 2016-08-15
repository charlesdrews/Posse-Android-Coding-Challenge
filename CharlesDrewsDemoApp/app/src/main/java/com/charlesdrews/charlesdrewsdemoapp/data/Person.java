package com.charlesdrews.charlesdrewsdemoapp.data;

import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Location;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Programmer;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json.Service;

/**
 * Models a person (programmer info + service/platform info + location info)
 *
 * Created by charlie on 8/13/16.
 */
public class Person {
    private final long mId;
    private final int mAge;
    private final String mFirstName, mFavColor, mPhoneNum, mPlatform, mLocationPublicId;
    private final String mLocality, mRegion, mPostalCode, mCountry;
    private final double mWeight;
    private final boolean mIsArtist;

    public Person(
            final long id, final int age, final String locationPublicId, final String firstName,
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

    public long getId() {
        return mId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getFavoriteColor() {
        return mFavColor;
    }

    public int getAge() {
        return mAge;
    }

    public double getWeight() {
        return mWeight;
    }

    public String getPhoneNumber() {
        return mPhoneNum;
    }

    public boolean isArtist() {
        return mIsArtist;
    }

    public String getPlatform() {
        return mPlatform;
    }

    public String getLocationPublicId() {
        return mLocationPublicId;
    }

    public String getLocality() {
        return mLocality;
    }

    public String getRegion() {
        return mRegion;
    }

    public String getPostalCode() {
        return mPostalCode;
    }

    public String getCountry() {
        return mCountry;
    }

    public static class Builder {
        private long mId;
        private int mAge;
        private String mFirstName, mFavColor, mPhoneNum, mPlatform, mLocationPublicId;
        private String mLocality, mRegion, mPostalCode, mCountry;
        private double mWeight;
        private boolean mIsArtist;

        /**
         * Use a GSON-generated Location object to build a Person
         * @param location
         * @return
         */
        public Builder setLocation(Location location) {
            mLocationPublicId = location.getPublicId();
            mLocality = location.getLocality();
            mRegion = location.getRegion();
            mPostalCode = location.getPostalCode();
            mCountry = location.getCountry();
            return this;
        }

        /**
         * Use a GSON-generated Service object to build a Person
         * @param service
         * @return
         */
        public Builder setService(Service service) {
            mPlatform = service.getPlatform();
            return this;
        }

        /**
         * Use a GSON-generated Programmer object to build a Person
         * @param programmer
         * @return
         */
        public Builder setProgrammer(Programmer programmer) {
            mFirstName = programmer.getName();
            mFavColor = programmer.getFavoriteColor();
            mAge = programmer.getAge();
            mWeight = programmer.getWeight();
            mPhoneNum = programmer.getPhone();
            mIsArtist = programmer.isIsArtist();
            return this;
        }

        public Builder setId(long id) {
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

        public Person build() {
            return new Person(mId, mAge, mLocationPublicId, mFirstName, mFavColor, mPhoneNum,
                    mPlatform, mLocality, mRegion, mPostalCode, mCountry, mWeight, mIsArtist);
        }
    }
}
