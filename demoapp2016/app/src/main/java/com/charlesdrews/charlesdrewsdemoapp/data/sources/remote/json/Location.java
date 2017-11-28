package com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.json;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("public_id")
    @Expose
    private String publicId;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("services")
    @Expose
    private List<Service> services = new ArrayList<Service>();

    /**
     *
     * @return
     * The publicId
     */
    public String getPublicId() {
        return publicId;
    }

    /**
     *
     * @param publicId
     * The public_id
     */
    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    /**
     *
     * @return
     * The locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     *
     * @param locality
     * The locality
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     *
     * @return
     * The region
     */
    public String getRegion() {
        return region;
    }

    /**
     *
     * @param region
     * The region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     *
     * @return
     * The postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     * The postal_code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The services
     */
    public List<Service> getServices() {
        return services;
    }

    /**
     *
     * @param services
     * The services
     */
    public void setServices(List<Service> services) {
        this.services = services;
    }

}