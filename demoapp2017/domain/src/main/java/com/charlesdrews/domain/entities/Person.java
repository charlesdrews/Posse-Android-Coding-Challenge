package com.charlesdrews.domain.entities;

/**
 * Models a person and provides a nested builder class
 * <p>
 * Created by charlie on 11/18/17.
 */

public class Person {
    private final long id;
    private final String firstName, favColor, phoneNum;
    private final int age;
    private final double weight;
    private final boolean isArtist;
    private final Location location;
    private final Platform platform;

    private Person(PersonBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.favColor = builder.favColor;
        this.phoneNum = builder.phoneNum;
        this.age = builder.age;
        this.weight = builder.weight;
        this.isArtist = builder.isArtist;
        this.location = builder.location;
        this.platform = builder.platform;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFavColor() {
        return favColor;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isArtist() {
        return isArtist;
    }

    public Location getLocation() {
        return location;
    }

    public Platform getPlatform() {
        return platform;
    }

    public static class PersonBuilder {
        private final long id;
        private final String firstName;

        // Default values
        private String favColor = null, phoneNum = null;
        private int age = -1;
        private double weight = -1;
        private boolean isArtist = false;
        private Location location = null;
        private Platform platform = null;

        public PersonBuilder(long id, String firstName) {
            this.id = id;
            this.firstName = firstName;
        }

        public PersonBuilder favColor(String favColor) {
            this.favColor = favColor;
            return this;
        }

        public PersonBuilder phoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
            return this;
        }

        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public PersonBuilder isArtist(boolean isArtist) {
            this.isArtist = isArtist;
            return this;
        }

        public PersonBuilder location(Location location) {
            this.location = location;
            return this;
        }

        public PersonBuilder platform(Platform platform) {
            this.platform = platform;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
