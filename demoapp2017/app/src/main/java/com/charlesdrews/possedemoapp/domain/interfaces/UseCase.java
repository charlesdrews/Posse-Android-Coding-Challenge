package com.charlesdrews.possedemoapp.domain.interfaces;

import io.reactivex.Observable;

/**
 * Created by charlie on 11/28/17.
 */

public interface UseCase<REQUEST, RESPONSE> {
    Observable<RESPONSE> execute(REQUEST request);
}
