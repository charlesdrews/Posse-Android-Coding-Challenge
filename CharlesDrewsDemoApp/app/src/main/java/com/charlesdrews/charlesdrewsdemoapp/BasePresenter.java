package com.charlesdrews.charlesdrewsdemoapp;

import android.support.annotation.NonNull;

/**
 * Base presenter to be used in the presenter repository and extended by the feature presenters
 *
 * Created by charlie on 8/14/16.
 */
public interface BasePresenter<V> {

    void bindView(@NonNull V view);

    void unbindView();
}
