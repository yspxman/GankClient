package com.example.syan.gankclient.Models;

import com.google.gson.annotations.SerializedName;

public class Promotion {

    @SerializedName("ImageUrl")
    public String imageUrl;


    @SerializedName("TargetUrl")
    public String targetUrl;

    @SerializedName("Name")
    public String name;


    @Override
    public String toString() {
        return super.toString();
    }
}
