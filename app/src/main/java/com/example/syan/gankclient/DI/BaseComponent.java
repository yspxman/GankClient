package com.example.syan.gankclient.DI;

import com.example.syan.gankclient.Helper.Utility;
import com.example.syan.gankclient.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;


//https://www.jianshu.com/p/1d84ba23f4d2


@Component(modules = BaseModule.class)
public interface BaseComponent {

     Utility getUtility();
}
