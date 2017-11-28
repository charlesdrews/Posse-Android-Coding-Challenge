package com.charlesdrews.charlesdrewsdemoapp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.charlesdrews.charlesdrewsdemoapp.data.sources.PeopleRepository;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.local.PeopleLocalDataSource;
import com.charlesdrews.charlesdrewsdemoapp.data.sources.remote.PeopleRemoteDataSource;

/**
 * I haven't built mock repositories for testing, but with this setup it should be relatively
 * easy to do so.
 *
 * Created by charlie on 8/14/16.
 */
public class Injection {

    public static PeopleRepository getPeopleRepository(@NonNull Context context) {
        return PeopleRepository.getInstance(
                PeopleLocalDataSource.getInstance(context),
                PeopleRemoteDataSource.getInstance(context));
    }
}
