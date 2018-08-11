package com.example.syan.gankclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.example.syan.gankclient.Helper.Utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PictureLoader {
    private ImageView loadImg;
    private String imgUrl;
    private byte[] picBytes;


    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123){
                if (picBytes != null){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(picBytes, 0, picBytes.length);
                    loadImg.setImageBitmap(bitmap);
                }
            }
        }
    };

    public void load(ImageView imageView, String url)
    {
        loadImg = imageView;
        imgUrl = url;
        Drawable drawable = loadImg.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable){
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null && !bitmap.isRecycled()){
                bitmap.recycle();
            }
        }
        new Thread(runnable).start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                picBytes = Utility.GetBytesFromUrl(imgUrl);
                handler.sendEmptyMessage(0x123);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}
