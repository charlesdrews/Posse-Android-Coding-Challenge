package com.charlesdrews.domain.interfaces;

import com.charlesdrews.domain.entities.ContactInfo;

import io.reactivex.Observable;

/**
 * Contract that must be fulfilled by any repository providing a ContactInfo object
 * <p>
 * Created by charlie on 11/28/17.
 */

public interface ContactInfoSource {
    Observable<ContactInfo> getContactInfo();
}
