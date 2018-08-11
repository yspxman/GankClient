package com.example.syan.gankclient;

import com.example.syan.gankclient.Helper.Utility;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class SisterAPI {
    private static final String TAG = "Network";
    private static final String BASE_URL = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/";

    public ArrayList<SisterModel> fetchSister(int count, int page){

        String fetchUrl = BASE_URL + count + "/" + page;
        ArrayList<SisterModel> sisters = new ArrayList<>();

        try{
            String result = Utility.GetStringFromUrl(fetchUrl);
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

        ArrayList<SisterModel> sisters = new ArrayList<>();

        JSONArray array = new JSONObject(content).getJSONArray("results");
        for (int i=0; i< array.length(); i++){

            JSONObject object = (JSONObject) array.get(i);

            SisterModel s = new SisterModel();
            s._id = object.getString("_id");
            s.createAt = object.getString("createdAt");
            s.desc = object.getString("desc");
            s.publishedAt = (object.getString("publishedAt"));
            s.source = (object.getString("source"));
            s.type = (object.getString("type"));
            s.url = (object.getString("url"));
            s.used = (object.getBoolean("used"));
            s.who = (object.getString("who"));
            sisters.add(s);
        }
        return sisters;
    }

}
