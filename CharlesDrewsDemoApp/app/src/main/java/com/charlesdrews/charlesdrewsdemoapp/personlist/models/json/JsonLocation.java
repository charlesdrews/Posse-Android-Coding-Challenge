package com.charlesdrews.charlesdrewsdemoapp.personlist.models.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charlie on 8/13/16.
 */
//@Generated("org.jsonschema2pojo")
public class JsonLocation {

//    @SerializedName("public_id")
//    @Expose
    private String publicId;

//    @SerializedName("locality")
//    @Expose
    private String locality;

//    @SerializedName("region")
//    @Expose
    private String region;

//    @SerializedName("postal_code")
//    @Expose
    private String postalCode;

//    @SerializedName("country")
//    @Expose
    private String country;

//    @SerializedName("services")
//    @Expose
    private List<JsonService> services = new ArrayList<JsonService>();


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
    public List<JsonService> getServices() {
        return services;
    }

    /**
     *
     * @param services
     * The services
     */
    public void setServices(List<JsonService> services) {
        this.services = services;
    }
}