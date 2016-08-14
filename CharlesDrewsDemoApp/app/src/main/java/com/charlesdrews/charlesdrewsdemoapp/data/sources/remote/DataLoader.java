package com.charlesdrews.charlesdrewsdemoapp.data.sources.remote;

import com.charlesdrews.charlesdrewsdemoapp.data.sources.DataManager;

/**
 * Data loader utility which gathers data (from local file, network call, etc.)
 * and persists it via DataManager
 *
 * Created by charlie on 8/13/16.
 */
public interface DataLoader {

    /**
     * Load data (from local file, network call, etc.) and persist via DataManager
     * @param dataManager
     * @return true if data loaded and persisted successfully, else false
     */
    boolean loadAndPersistData(DataManager dataManager);
}
