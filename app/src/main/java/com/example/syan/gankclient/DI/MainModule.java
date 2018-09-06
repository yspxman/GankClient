package com.example.syan.gankclient.DI;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    public Cloth getCloth(){
        Cloth cloth = new Cloth();
        return cloth;
    }

    @Provides
    public Clothes getClothes(Cloth cloth){
        return new Clothes(cloth);
    }

}
