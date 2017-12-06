package com.charlesdrews.domain.usecases;

import com.charlesdrews.domain.entities.Location;
import com.charlesdrews.domain.entities.Platform;
import com.charlesdrews.domain.interfaces.PeopleSource;
import com.charlesdrews.domain.interfaces.UseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Implements the use case of retrieving a list of existing locations and platforms
 * from a repository that satisfies the PeopleSource contract
 * <p>
 * Created by charlie on 12/6/17.
 */

public class GetLocationsAndPlatformsInteractor implements UseCase<Void, GetLocationsAndPlatformsInteractor.Response> {

    private final PeopleSource peopleSource;

    @Inject
    public GetLocationsAndPlatformsInteractor(PeopleSource peopleSource) {
        this.peopleSource = peopleSource;
    }

    @Override
    public Observable<Response> execute(Void aVoid) {
        Observable<List<Location>> locations = peopleSource.getLocations();
        Observable<List<Platform>> platforms = peopleSource.getPlatforms();
        return Observable.zip(locations, platforms, Response::new);
    }

    public static class Response {
        private final List<Location> locations;
        private final List<Platform> platforms;

        public Response(List<Location> locations, List<Platform> platforms) {
            this.locations = locations;
            this.platforms = platforms;
        }

        public List<Location> getLocations() {
            return locations;
        }

        public List<Platform> getPlatforms() {
            return platforms;
        }
    }
}
