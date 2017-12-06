package com.charlesdrews.domain.usecases;

import com.charlesdrews.domain.entities.Person;
import com.charlesdrews.domain.interfaces.PeopleProvider;
import com.charlesdrews.domain.interfaces.UseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Implements the use case of retrieving people from a repository that satisfies the PeopleProvider contract
 * <p>
 * Created by charlie on 11/28/17.
 */

public class GetPeopleInteractor implements UseCase<GetPeopleInteractor.Request, List<Person>> {

    private final PeopleProvider peopleProvider;

    @Inject
    public GetPeopleInteractor(PeopleProvider peopleProvider) {
        this.peopleProvider = peopleProvider;
    }

    @Override
    public Observable<List<Person>> execute(Request request) {
        return peopleProvider.getPeople(request.getFilterLocationId(), request.getFilterPlatformId());
    }


    public static class Request {
        private final long filterLocationId, filterPlatformId;

        public Request(long filterLocationId, long filterPlatformId) {
            this.filterLocationId = filterLocationId;
            this.filterPlatformId = filterPlatformId;
        }

        long getFilterLocationId() {
            return filterLocationId;
        }

        long getFilterPlatformId() {
            return filterPlatformId;
        }
    }
}
