package com.charlesdrews.domain.usecases;

import com.charlesdrews.domain.entities.ContactInfo;
import com.charlesdrews.domain.interfaces.ContactInfoSource;
import com.charlesdrews.domain.interfaces.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Implements the use case of retrieving contact information from a repository that satisfies
 * the ContactInfoSource contract
 * <p>
 * Created by charlie on 11/28/17.
 */

public class GetContactInfo implements UseCase<Void, ContactInfo> {

    private final ContactInfoSource contactInfoSource;

    @Inject
    public GetContactInfo(ContactInfoSource contactInfoSource) {
        this.contactInfoSource = contactInfoSource;
    }

    @Override
    public Observable<ContactInfo> execute(Void aVoid) {
        return contactInfoSource.getContactInfo();
    }
}
