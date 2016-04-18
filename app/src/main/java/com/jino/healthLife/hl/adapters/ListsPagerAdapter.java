package com.jino.healthLife.hl.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jino.healthLife.hl.fragments.BaseListFragment;
import com.jino.healthLife.hl.models.Category;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public class ListsPagerAdapter extends FragmentPagerAdapter {

    private final FragmentManager fm;
    private List<Category> tabs;
//    private List<List<ItemInfo>> newsInfoList;

    private List<? extends Fragment> fragments;


    public ListsPagerAdapter(FragmentManager fm, List<Category> tabs, List<? extends BaseListFragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
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


    public void removeItem(Category c) {
        int position = tabs.indexOf(c);
        if (position != -1) {

            tabs.remove(position);
            fragments.remove(position);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        if (tabs == null || tabs.size() <= 0) {
            return 0;
        }
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (tabs.get(position).getName() == null || tabs.get(position).getName() == "") {
            return tabs.get(position).getTitle();

        }
        return tabs.get(position).getName();
    }
}
