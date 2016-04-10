package com.jino.zhbj.zhbj.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jino.zhbj.R;
import com.jino.zhbj.zhbj.Constane.Constant;
import com.jino.zhbj.zhbj.activies.ItemDetialActivity;
import com.jino.zhbj.zhbj.adapters.BaseAdapter;
import com.jino.zhbj.zhbj.adapters.ItemListAdapter;
import com.jino.zhbj.zhbj.models.Category;
import com.jino.zhbj.zhbj.models.ItemInfo;
import com.jino.zhbj.zhbj.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_news_list)
public class NewsListFragment extends BaseListFragment {


//    public static final String NEWSFRAGMENT_NEWSINFO = "newInfo";

    @ViewInject(R.id.id_news_list_rv)
    RecyclerView newsList;

//    private Category tab;

    private ItemListAdapter adapter;
//    private List<ItemInfo> newsInfos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category= (Category) getArguments().getSerializable(Constant.KEY_NEWS_TAB_DATA);
        initData(Constant.NEWS_LIST_URL);
        LogUtils.d(category.toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(category.toString());
    }


    protected void initView() {
        LogUtils.d(infos.toString());
//        newsInfos=datas;
        adapter = new ItemListAdapter(getContext(), infos);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                goDetialView(position, Constant.NEWS_DETIALS_URL);
//                LogUtils.d("V:" + v + "..." + position);
            }
        });
        newsList.setAdapter(adapter);
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsList.setItemAnimator(new DefaultItemAnimator());
    }









}
