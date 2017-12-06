package com.charlesdrews.domain.entities;

/**
 * Models a platform
 * <p>
 * Created by charlie on 11/28/17.
 */

public class Platform {
    private final long id;
    private final String platform;

    public Platform(long id, String platform) {
        this.id = id;
        this.platform = platform;
    }

    public long getId() {
        return id;
    }

    public String getPlatform() {
        return platform;
    }
}
