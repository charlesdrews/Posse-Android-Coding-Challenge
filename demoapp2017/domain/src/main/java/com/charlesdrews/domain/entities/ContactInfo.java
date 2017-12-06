package com.charlesdrews.domain.entities;

/**
 * Models my contact information
 * <p>
 * Created by charlie on 11/18/17.
 */

public class ContactInfo {
    private final String name, location, email, phone, url;

    public ContactInfo(String name, String location, String email, String phone, String url) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUrl() {
        return url;
    }
}
