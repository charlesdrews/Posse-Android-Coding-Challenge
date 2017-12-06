package com.charlesdrews.domain.usecases;

import com.charlesdrews.domain.entities.Person;
import com.charlesdrews.domain.interfaces.PeopleProvider;
import com.charlesdrews.domain.interfaces.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Implements the use case of retrieving one person from a repository that satisfies the PeopleProvider contract
 * <p>
 * Created by charlie on 11/28/17.
 */

public class GetPersonInteractor implements UseCase<GetPersonInteractor.Request, Person> {

    private final PeopleProvider peopleProvider;

    @Inject
    public GetPersonInteractor(PeopleProvider peopleProvider) {
        this.peopleProvider = peopleProvider;
    }

    @Override
    public Observable<Person> execute(Request request) {
        return peopleProvider.getPerson(request.getSelectedPersonId());
    }

    public static class Request {
        private final long selectedPersonId;

        public Request(long selectedPersonId) {
            this.selectedPersonId = selectedPersonId;
        }

        public long getSelectedPersonId() {
            return selectedPersonId;
        }
    }
}
