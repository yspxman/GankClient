package com.example.syan.gankclient.Services;

import com.example.syan.gankclient.Common.MyAppConfig;
import com.example.syan.gankclient.Helper.Utility;
import com.example.syan.gankclient.Models.QuickBet;
import com.example.syan.gankclient.Models.SisterModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BettingService {

    @GET("events/getQuickBets")
    Call<QuickBet> getQuickBets();
}
