package com.example.syan.gankclient.DI;

import com.example.syan.gankclient.Helper.Utility;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    @Named("blue")
    public Cloth getBlueCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("蓝色");
        return cloth;
    }


    @Provides
    @Named("red")
    public Cloth getRedCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @Provides
    public Cloth getGeneralCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("black");
        return cloth;
    }

    @Provides
    @Named("blue")
    public Clothes getClothes(@Named("blue") Cloth cloth){
        return new Clothes(cloth);
    }

    @Provides
    public Clothes getGeneralClothes(Cloth cloth){
        return new Clothes(cloth);
    }
}

