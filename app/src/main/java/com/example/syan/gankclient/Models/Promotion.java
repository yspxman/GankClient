package com.example.syan.gankclient.Models;

import com.google.gson.annotations.SerializedName;

public class Promotion {

    @SerializedName("ImageUrl")
    private String imageUrl;


    @SerializedName("TargetUrl")
    private String targetUrl;

    @SerializedName("Name")
    private String name;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public String getTargetUrl() {
        return targetUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
