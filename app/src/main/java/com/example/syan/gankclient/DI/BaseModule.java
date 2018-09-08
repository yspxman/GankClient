package com.example.syan.gankclient.DI;

import com.example.syan.gankclient.Helper.Utility;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseModule {

    @Provides
    public Utility getUtility(){
        return new Utility();
    }
}
