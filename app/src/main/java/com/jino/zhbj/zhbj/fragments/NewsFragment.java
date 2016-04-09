package com.jino.zhbj.zhbj.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jino.zhbj.R;
import com.jino.zhbj.zhbj.Constane.Constant;
import com.jino.zhbj.zhbj.adapters.NewsPagerAdapter;
import com.jino.zhbj.zhbj.models.Category;
import com.jino.zhbj.zhbj.models.NewsInfo;
import com.jino.zhbj.zhbj.net.Request;
import com.jino.zhbj.zhbj.net.RequestCallBack;
import com.jino.zhbj.zhbj.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.fragment_news)
public class NewsFragment extends BaseFragment {

    @ViewInject(R.id.id_news_topTaps)
    TabLayout topTab;
    @ViewInject(R.id.id_news_vp)
    ViewPager newLists;


    List<Category> tabs;
    List<List<NewsInfo>> newsInfoList;
    private NewsPagerAdapter adapter;


    private FragmentManager fm;
    private List<NewsListFragment> fragments;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
//        initView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabs = new ArrayList<>();
        fm = getFragmentManager();
//        newsInfoList = new ArrayList<>();
//        initView();
//        initData();
    }

    private void initView() {

        initFragments();
        adapter = new NewsPagerAdapter(fm, tabs,fragments);
        newLists.setAdapter(adapter);
        topTab.setupWithViewPager(newLists);
    }

    private void initFragments() {

        fragments = new ArrayList<>();
        NewsListFragment fragment=null;
        for(Category c: tabs){
            fragment=NewsListFragment.newInstance(c);
            fragments.add(fragment);
//            fragment=null;
        }
    }


    void initData() {
        getTabDatas();
    }




    private void getTabDatas() {
        Request.getInstance().get(Constant.NEWS_CATEGORY_URL, new RequestCallBack<List<Category>>() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public void onSuccess(List<Category> categories) {
                tabs = categories;
                LogUtils.d(tabs.toString());
//                getPagerData(tabs);
                initView();
            }

            @Override
            public void onError(Throwable ex) {
                LogUtils.d(ex.toString());
            }

            @Override
            public void onFinish() {

            }

        });

    }

    private void getPagerData(final List<Category> tabs) {
        newsInfoList = new ArrayList<>();
//        for (Category c : tabs) {
//            Request.getInstance().post(Constant.NEWS_LIST_URL, buildMap(c), new RequestCallBack<List<NewsInfo>>() {
//
//                @Override
//                public void onPreLoad() {
//
//                }
//
//                @Override
//                public void onSuccess(List<NewsInfo> newsInfos) {
//                    LogUtils.d(newsInfos.toString());
//                    newsInfoList.add(newsInfos);
//                    adapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onError(Throwable ex) {
//
//                    LogUtils.d(ex.toString());
//                }
//
//                @Override
//                public void onFinish() {
//                }
//            });
//        }

//        for(int i=0;i<tabs.size();i++){
//            final int finalI = i;
//            Request.getInstance().post(Constant.NEWS_LIST_URL, buildMap(tabs.get(i)), new RequestCallBack<List<NewsInfo>>() {
////
//                @Override
//                public void onPreLoad() {
//
//                }
//
//                @Override
//                public void onSuccess(List<NewsInfo> newsInfos) {
//                    LogUtils.d(newsInfos.toString());
//                    newsInfoList.add(newsInfos);
////                    adapter.notifyDataSetChanged();
//                    if (finalI ==tabs.size()-1){
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//
//                @Override
//                public void onError(Throwable ex) {
//
//                    LogUtils.d(ex.toString());
//                }
//
//                @Override
//                public void onFinish() {
//                }
//            });
//        }
    }




}
