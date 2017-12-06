package com.charlesdrews.domain.entities;

/**
 * Models a location
 * <p>
 * Created by charlie on 11/28/17.
 */

public class Location {
    private final long id;
    private final String name, region, postalCode, country;

    public Location(long id, String locality, String region, String postalCode, String country) {
        this.id = id;
        this.name = locality;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }
}
