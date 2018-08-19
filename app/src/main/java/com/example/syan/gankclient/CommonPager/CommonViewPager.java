package com.example.syan.gankclient.CommonPager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.syan.gankclient.CommonPager.CommonViewPagerAdatper;
import com.example.syan.gankclient.R;

import java.util.ArrayList;

public class CommonViewPager extends RelativeLayout {

    private ViewPager mViewPager;
    private CommonViewPagerAdatper mAdapter;
    private LinearLayout ll_indicator;


    // this ctor is essential when editing in xaml
    public CommonViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonViewPager(Context context) {
        super(context);
        init();
    }

    public void next() {
        int idx = mViewPager.getCurrentItem() + 1;
        if (idx >=  mAdapter.getCount()) {
            idx = 0;
        }
        mViewPager.setCurrentItem(idx);
    }

    public void setImages(ArrayList<String> images)
    {
        createDots(images.size());
        mAdapter = new CommonViewPagerAdatper(getContext(), images);
        mViewPager.setAdapter(mAdapter);
        setIndicatorPos(0);
    }

    private void init(){
        View v = LayoutInflater.from(getContext()).inflate(R.layout.common_viewpager,this,true);
        mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
        ll_indicator = (LinearLayout) v.findViewById(R.id.ll_indicator);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setIndicatorPos(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createDots(int count)    {

        ll_indicator.removeAllViews();

        for (int i = 0; i< count; i++){
            ImageView imageView = new ImageView(getContext());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.rightMargin = 5;
            params.leftMargin = 5;
            imageView.setLayoutParams(params);
            ll_indicator.addView(imageView);
        }
    }


    private void setIndicatorPos(int pos){
        for(int i=0; i< ll_indicator.getChildCount(); i++) {
            if (i == pos)
                ll_indicator.getChildAt(i).setBackgroundResource(R.drawable.dot_black);
            else
                ll_indicator.getChildAt(i).setBackgroundResource(R.drawable.dot_white);
        }
    }
}
