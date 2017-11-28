package com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Programmer {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("favorite_color")
    @Expose
    private String favoriteColor;
    @SerializedName("age")
    @Expose
    private int age;
    @SerializedName("weight")
    @Expose
    private double weight;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("is_artist")
    @Expose
    private boolean isArtist;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The favoriteColor
     */
    public String getFavoriteColor() {
        return favoriteColor;
    }

    /**
     *
     * @param favoriteColor
     * The favorite_color
     */
    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    /**
     *
     * @return
     * The age
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @param age
     * The age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @return
     * The weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     * The weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The isArtist
     */
    public boolean isIsArtist() {
        return isArtist;
    }

    /**
     *
     * @param isArtist
     * The is_artist
     */
    public void setIsArtist(boolean isArtist) {
        this.isArtist = isArtist;
    }

}