package com.jino.zhbj.zhbj.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<ImageView> pics;

    public MyPagerAdapter(List<ImageView> pics) {
        this.pics = pics;
    }

    @Override
    public int getCount() {
        return pics.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pics.get(position));
        return pics.get(position);
    }
}
