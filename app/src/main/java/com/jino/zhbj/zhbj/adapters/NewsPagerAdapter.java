package com.jino.zhbj.zhbj.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jino.zhbj.zhbj.fragments.NewsListFragment;
import com.jino.zhbj.zhbj.models.Category;
import com.jino.zhbj.zhbj.models.NewsInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public class NewsPagerAdapter extends FragmentPagerAdapter {

    private final FragmentManager fm;
    private List<Category> tabs;
//    private List<List<NewsInfo>> newsInfoList;

    private List<NewsListFragment> fragments;


    public NewsPagerAdapter(FragmentManager fm, List<Category> tabs,List<NewsListFragment> fragments) {
        super(fm);
        this.fm=fm;
        this.fragments=fragments;
        this.tabs = tabs;
//        this.newsInfoList = newsInfoList;
    }

    @Override
    public Fragment getItem(int position) {
       return fragments.get(position);
    }


//    Fragment getFragment(int position){
//        String tag=tabs.get(position).getName();
//        NewsListFragment fragment= (NewsListFragment) fm.findFragmentByTag(tag);
//        if(fragment==null){
//            fragment=NewsListFragment.newInstance(tabs.get(position));
//        }
//
//        return null;
//
//    }

    @Override
    public int getCount() {
        if(tabs==null||tabs.size()<=0){
            return 0;
        }
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getName();
    }
}
