package com.example.syan.gankclient.DI;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class SecondModule {

    @Provides
    public Cloth getGeneralCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("black");
        return cloth;
    }
}

