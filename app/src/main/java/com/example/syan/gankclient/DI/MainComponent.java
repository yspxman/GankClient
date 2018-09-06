package com.example.syan.gankclient.DI;

import com.example.syan.gankclient.HomeActivity;
import com.example.syan.gankclient.MainActivity;

import dagger.Component;
import dagger.Module;



//https://www.jianshu.com/p/1d84ba23f4d2



@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(HomeActivity homeActivity);

}
