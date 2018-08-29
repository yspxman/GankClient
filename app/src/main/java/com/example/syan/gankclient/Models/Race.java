package com.example.syan.gankclient.Models;

import com.google.gson.annotations.SerializedName;

public class Race {

    @SerializedName("EventName")
    public String eventName;

    @SerializedName("TimeToRace")
    public String TimeToRace;

    @SerializedName("RaceNo")
    public String RaceNo;

    @Override
    public String toString() {
        return super.toString();
    }
}
