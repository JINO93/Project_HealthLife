package com.jino.healthLife.hl.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jino.healthLife.R;
import com.jino.healthLife.hl.Constane.Constant;
import com.jino.healthLife.hl.adapters.ListsPagerAdapter;
import com.jino.healthLife.hl.models.Category;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_news)
public class NewsFragment extends BaseFragment {

    @ViewInject(R.id.id_news_topTaps)
    TabLayout topTab;
    @ViewInject(R.id.id_news_vp)
   public ViewPager newLists;


//    List<Category> tabs;
//    List<List<ItemInfo>> newsInfoList;
//    private FragmentManager fm;


    private List<NewsListFragment> fragments;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        tabs = new ArrayList<>();
//        fm = getFragmentManager();
        initData(Constant.NEWS_CATEGORY_URL);
//        initView();
    }



    protected void initView() {

        initFragments();
        adapter = new ListsPagerAdapter(fm, tabs,fragments);
        newLists.setAdapter(adapter);
        topTab.setupWithViewPager(newLists);
    }

    protected void initFragments() {

        fragments = new ArrayList<>();
        NewsListFragment fragment=null;
        Bundle b=null;
        for(Category c: tabs){
            fragment= new NewsListFragment();
            b=new Bundle();
            b.putSerializable(Constant.KEY_NEWS_TAB_DATA,c);
            fragment.setArguments(b);
            fragments.add(fragment);
//            fragment=null;
        }
    }


//    void initData() {
//        getTabDatas();
//    }
//
//
//
//
//    private void getTabDatas() {
//        Request.getInstance().get(Constant.NEWS_CATEGORY_URL, new RequestCallBack<List<Category>>() {
//            @Override
//            public void onPreLoad() {
//
//            }
//
//            @Override
//            public void onSuccess(List<Category> categories) {
//                tabs = categories;
//                LogUtils.d(tabs.toString());
////                getPagerData(tabs);
//                initView();
//            }
//
//            @Override
//            public void onError(Throwable ex) {
//                LogUtils.d(ex.toString());
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//
//        });
//
//    }





}
