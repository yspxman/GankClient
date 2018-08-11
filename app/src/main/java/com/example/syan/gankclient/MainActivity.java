package com.example.syan.gankclient;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button showBtn;
    private Button refreshBtn;
    private ImageView showImg;
    private ArrayList<String> urls;

    private int curPos = 0;
    private PictureLoader loader;
    private SisterAPI sisterAPI;
    private SisterTask sisterTask;
    private int page = 1;
    private ArrayList<SisterModel> data;
    private ProgressDialog dialog;
    private AppCompatActivity activity;

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
    }

    private void initUI()
    {
        showBtn = (Button)findViewById(R.id.next_btn);
        showImg = (ImageView) findViewById(R.id.imageView);
        showBtn.setOnClickListener(this);

        refreshBtn = findViewById(R.id.refresh_btn);
        refreshBtn.setOnClickListener(this);

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
                    loader.load(showImg, data.get(curPos).getUrl());
                }
                break;
            case R.id.refresh_btn:
                Refresh();
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
            dialog =  ProgressDialog.show(activity, "", "loading", true);
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
            data.clear();
            data.addAll(sisterModels);
            page++;
            curPos = 0;
            loader.load(showImg, data.get(curPos).getUrl());
            dismissDlg();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            sisterTask = null;
        }

        private void dismissDlg()
        {
            if (dialog != null)
            {
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
