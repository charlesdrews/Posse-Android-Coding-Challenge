package com.charlesdrews.charlesdrewsdemoapp;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Keep presenters in memory on configuration change
 *
 * Created by charlie on 8/14/16.
 */
public class PresenterCache {
    private static final String TAG = "PresenterCache";
    
    private static PresenterCache sInstance;

    private Map<String, BasePresenter> mPresenters;

    public static PresenterCache getInstance() {
        if (sInstance == null) {
            sInstance = new PresenterCache();
        }
        return sInstance;
    }

    private PresenterCache() {
        mPresenters = new HashMap<>();
    }

    @SuppressWarnings("unchecked") // cast of BasePresenter to P is handled via try/catch
    public <P extends BasePresenter> P getPresenter(String tag,
                                                    PresenterFactory<P> presenterFactory) {
        P presenter = null;
        try {
            presenter = (P) mPresenters.get(tag);
        } catch (ClassCastException e) {
            Log.w(TAG, "getPresenter: duplicate presenter tag: " + tag, e);
        }

        if (presenter == null) {
            presenter = presenterFactory.createPresenter();
            mPresenters.put(tag, presenter);
        }

        return presenter;
    }

    public void removePresenter(String tag) {
        mPresenters.remove(tag);
    }

    public interface PresenterFactory<P extends BasePresenter> {
        P createPresenter();
    }
}
