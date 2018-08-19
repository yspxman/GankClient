package com.example.syan.gankclient.CommonPager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.syan.gankclient.R;

import java.util.ArrayList;

public class CommonViewPagerAdatper extends PagerAdapter {
    private Context mContext;
    private ArrayList<View> mViews;
    private ArrayList<String> mImages;
    private ImageView imageView;

    public CommonViewPagerAdatper(Context context, ArrayList<String> images)
    {
        mContext = context;
        mImages = images;
        mViews = new ArrayList<View>();

        for(int i =0; i< images.size(); i++){
            mViews.add(View.inflate(mContext, R.layout.slider_item, null));
        }
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mViews.get(position);
        ImageView iv= view.findViewById(R.id.slider_imageView);

        Glide.with( mContext )
                .load( mImages.get(position) )
                .into( iv );

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViews.get(position));
    }
}
