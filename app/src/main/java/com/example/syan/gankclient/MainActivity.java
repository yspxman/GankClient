package com.example.syan.gankclient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.syan.gankclient.Common.MyAppConfig;
import com.example.syan.gankclient.CommonPager.CommonViewPager;
import com.example.syan.gankclient.Models.Banner;
import com.example.syan.gankclient.Models.Promotion;
import com.example.syan.gankclient.Models.QuickBet;
import com.example.syan.gankclient.Models.SisterModel;
import com.example.syan.gankclient.Services.BettingService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/////  https://www.jianshu.com/p/adb21180862a
public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button showBtn;
    private Button refreshBtn;
    private ArrayList<String> urls;
    private int curPos = 0;
    private PictureLoader loader;
    private SisterAPI sisterAPI;
    private SisterTask sisterTask;
    private int page = 1;
    private ArrayList<SisterModel> data;
    private ProgressDialog dialog;
    private AppCompatActivity activity;
    private ProgressBar progressBar;
    private CommonViewPager commonViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_main);

        sisterAPI = new SisterAPI();
        loader = new PictureLoader();


        initData();
        initUI();
    }

    private void initData() {

        data = new ArrayList<>();
        urls = new ArrayList<>();
        urls.add("http://ww4.sinaimg.cn/large/610dc034jw1f6ipaai7wgj20dw0kugp4.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f6gcxc1t7vj20hs0hsgo1.jpg");
        urls.add("http://ww4.sinaimg.cn/large/610dc034jw1f6f5ktcyk0j20u011hacg.jpg");
        urls.add("http://ww1.sinaimg.cn/large/610dc034jw1f6e1f1qmg3j20u00u0djp.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f6aipo68yvj20qo0qoaee.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f69c9e22xjj20u011hjuu.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f689lmaf7qj20u00u00v7.jpg");
        urls.add("http://ww3.sinaimg.cn/large/c85e4a5cjw1f671i8gt1rj20vy0vydsz.jpg");
        urls.add("http://ww2.sinaimg.cn/large/610dc034jw1f65f0oqodoj20qo0hntc9.jpg");
        urls.add("http://ww2.sinaimg.cn/large/c85e4a5cgw1f62hzfvzwwj20hs0qogpo.jpg");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyAppConfig.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BettingService service = retrofit.create(BettingService.class);


        Call<QuickBet> quickBetCall = service.getQuickBets();

        quickBetCall.enqueue(new Callback<QuickBet>() {
            @Override
            public void onResponse(Call<QuickBet> call, Response<QuickBet> response) {

                QuickBet bet = response.body();

                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT ).show();

                // set silder

                if (bet != null){

                    Banner banner = bet.Banner;

                    if (banner != null){

                        ArrayList<Promotion> promos = banner.Promos;


                        ArrayList<String> images = new ArrayList<>();

                        for (int i=0; i< promos.size(); i++)     {
                            images.add(promos.get(i).imageUrl);
                        }

                        commonViewPager.setImages(images);
                    }
                }


            }

            @Override
            public void onFailure(Call<QuickBet> call, Throwable t) {

            }
        });
    }

    ////https://www.jianshu.com/p/adb21180862a
    private void initUI()
    {
        showBtn = (Button)findViewById(R.id.next_btn);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        commonViewPager = (CommonViewPager) findViewById(R.id.my_viewpager);

        showBtn.setOnClickListener(this);
        refreshBtn = findViewById(R.id.refresh_btn);
        refreshBtn.setOnClickListener(this);

        Button quickbetBtn = (Button) findViewById(R.id.quickbet_btn);

        quickbetBtn.setOnClickListener(this);

        Refresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_btn:

                if (data != null && !data.isEmpty()) {
                    curPos++;
                    if (curPos > 9) {
                        curPos = 0;
                    }
                    //loader.load(showImg, data.get(curPos).getUrl());
                    commonViewPager.next();
                }
                break;
            case R.id.refresh_btn:
                Refresh();
                break;

            case R.id.quickbet_btn:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void Refresh()
    {
        new SisterTask(page).execute();
    }

    private class SisterTask extends AsyncTask<Void, Void, ArrayList<SisterModel>>{
        private int _page;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        public SisterTask(int page){
            this._page = page;
        }

        @Override
        protected ArrayList<SisterModel> doInBackground(Void... voids) {
            return sisterAPI.fetchSister(10, _page);
        }

        @Override
        protected void onPostExecute(ArrayList<SisterModel> sisterModels) {
            super.onPostExecute(sisterModels);

            if (sisterModels == null){
                return;
            }

            data.clear();
            data.addAll(sisterModels);
            page++;
            curPos = 0;
            //loader.load(showImg, data.get(curPos).getUrl());
            progressBar.setVisibility(View.INVISIBLE);

            // set silder
            ArrayList<String> images = new ArrayList<>();

           for (int i=0; i< sisterModels.size(); i++)     {
               images.add(sisterModels.get(i).getUrl());
           }

            //commonViewPager.setImages(images);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            sisterTask = null;
        }

        private void dismissDlg()
        {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sisterTask.cancel(true);
    }
}
