package com.example.syan.gankclient;

import android.app.Application;
import android.support.design.widget.AppBarLayout;

import com.example.syan.gankclient.DI.BaseComponent;
import com.example.syan.gankclient.DI.BaseModule;
import com.example.syan.gankclient.DI.DaggerBaseComponent;
import com.example.syan.gankclient.DI.DaggerMainComponent;

public class MyApplication extends Application {

    private BaseComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        baseComponent = DaggerBaseComponent.builder().baseModule(new BaseModule()).build();
    }

    public BaseComponent getBaseComponent(){
        return baseComponent;
    }
}
