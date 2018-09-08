package com.example.syan.gankclient.Services;

import com.example.syan.gankclient.Helper.Utility;
import com.example.syan.gankclient.Models.GanDataModel;
import com.example.syan.gankclient.Models.SisterModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SisterAPI {
    private static final String TAG = "Network";
    private static final String BASE_URL = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/";

    public ArrayList<SisterModel> fetchSister(int count, int page){

        String fetchUrl = BASE_URL + count + "/" + page;
        ArrayList<SisterModel> sisters = new ArrayList<>();

        try{
            String result = new Utility().GetStringFromUrl(fetchUrl);
            if (result != null) {
                sisters = parseSister(result);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return sisters;

    }

    public ArrayList<SisterModel> parseSister(String content) throws Exception{

        Gson gson = new Gson();
        //BaseModel s =   gson.fromJson(content, new TypeToken<ArrayList<SisterModel>>(){}.getType());
        GanDataModel model =   gson.fromJson(content, GanDataModel.class);
        return model.getResults();
    }
}
