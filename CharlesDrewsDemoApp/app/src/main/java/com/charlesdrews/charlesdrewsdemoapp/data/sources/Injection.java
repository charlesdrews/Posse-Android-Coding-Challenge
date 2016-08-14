package com.charlesdrews.charlesdrewsdemoapp.data.sources;

import android.content.Context;
import android.support.annotation.NonNull;

import com.charlesdrews.charlesdrewsdemoapp.data.sources.local.PeopleLocalDataSource;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.PeopleRemoteDataSource;

/**
 * I haven't build a mock repository for testing, but with this setup it would be easy to use one
 *
 * Created by charlie on 8/14/16.
 */
public class Injection {

    public static PeopleRepository getTaskRepository(@NonNull Context context) {
        return PeopleRepository.getInstance(
                PeopleLocalDataSource.getInstance(context),
                PeopleRemoteDataSource.getInstance(context));
    }
}
