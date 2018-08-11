package com.example.syan.gankclient.Helper;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public  class Utility {

    public static byte[] readFromStream(InputStream inputStream) throws Exception{

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int len =-1;
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, len);
        }

        byte[] results = outputStream.toByteArray();
        inputStream.close();
        outputStream.close();
        return results;
    }

    public static String readStringFromStream(InputStream inputStream) throws Exception{
        return new String(readFromStream(inputStream), "UTF-8");
    }


    public static Response GetResponse(String url)
    {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                return response;
            }
            else {
                Log.e("network", "failed: " + response.code());
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String GetStringFromUrl(String url) throws IOException {
        Response response = GetResponse(url);

        if (response != null){
            return response.body().string();
        }

        return null;
    }

    public static byte[] GetBytesFromUrl(String url) throws IOException {
        Response response = GetResponse(url);

        if (response != null){
            return response.body().bytes();
        }

        return null;
    }
}
