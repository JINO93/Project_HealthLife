package com.jino.zhbj.zhbj.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jino.zhbj.R;
import com.jino.zhbj.zhbj.Constane.Constant;
import com.jino.zhbj.zhbj.adapters.BaseAdapter;
import com.jino.zhbj.zhbj.adapters.ItemListAdapter;
import com.jino.zhbj.zhbj.models.Bean;
import com.jino.zhbj.zhbj.models.Category;
import com.jino.zhbj.zhbj.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.fragment_news_list)
public class KnowledgeListFragment extends BaseListFragment {


    @ViewInject(R.id.id_news_list_rv)
    RecyclerView newsList;
    private ItemListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category= (Category) getArguments().getSerializable(String.valueOf(this.hashCode()));
        LogUtils.d(category.toString());
        initData(Constant.KNOWLEDGE_LIST_URL);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {

        LogUtils.d(infos.toString());
        adapter = new ItemListAdapter(getContext(), infos);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                goDetialView(position,Constant.KNOWLEDGE_DETIALS_URL);
                LogUtils.d("V:" + v + "..." + position);
                LogUtils.d(infos.get(position).toString());
            }
        });
        newsList.setAdapter(adapter);
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsList.setItemAnimator(new DefaultItemAnimator());
    }



//    @Override
//    Intent setDetialData(int position) {
//        return null;
//    }
}
