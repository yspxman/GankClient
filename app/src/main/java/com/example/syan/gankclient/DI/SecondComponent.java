package com.example.syan.gankclient.DI;

import com.example.syan.gankclient.HomeActivity;
import com.example.syan.gankclient.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


//https://www.jianshu.com/p/1d84ba23f4d2

@Component(modules = SecondModule.class, dependencies = BaseComponent.class)
public interface SecondComponent {

    void inject(MainActivity mainActivity);

}
