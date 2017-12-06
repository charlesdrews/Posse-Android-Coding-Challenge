package com.charlesdrews.domain.usecases;

import com.charlesdrews.domain.entities.ContactInfo;
import com.charlesdrews.domain.interfaces.ContactInfoProvider;
import com.charlesdrews.domain.interfaces.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Implements the use case of retrieving contact information from a repository that satisfies
 * the ContactInfoProvider contract
 * <p>
 * Created by charlie on 11/28/17.
 */

public class GetContactInfo implements UseCase<Void, ContactInfo> {

    private final ContactInfoProvider contactInfoProvider;

    @Inject
    public GetContactInfo(ContactInfoProvider contactInfoProvider) {
        this.contactInfoProvider = contactInfoProvider;
    }

    @Override
    public Observable<ContactInfo> execute(Void aVoid) {
        return contactInfoProvider.getContactInfo();
    }
}
