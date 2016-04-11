package com.jino.zhbj.zhbj.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.jino.zhbj.R;
import com.jino.zhbj.zhbj.Constane.Constant;
import com.jino.zhbj.zhbj.adapters.BaseAdapter;
import com.jino.zhbj.zhbj.adapters.ItemListAdapter;
import com.jino.zhbj.zhbj.models.Bean;
import com.jino.zhbj.zhbj.models.Category;
import com.jino.zhbj.zhbj.models.ItemInfo;
import com.jino.zhbj.zhbj.net.Request;
import com.jino.zhbj.zhbj.net.RequestCallBack;
import com.jino.zhbj.zhbj.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.fragment_news_list)
public class KnowledgeListFragment extends BaseListFragment {

    @ViewInject(R.id.id_news_refresh)
    private MaterialRefreshLayout refreshLayout;
    @ViewInject(R.id.id_news_list_rv)
    RecyclerView newsList;

    private ItemListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = (Category) getArguments().getSerializable(String.valueOf(this.hashCode()));
//        LogUtils.d("onCreate");

    }

    @Override
    public void onResume() {
        super.onResume();
//        LogUtils.d(new String((adapter.equals(null))?"Yes":"no"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        LogUtils.d("onCreateView");
        initData(Constant.KNOWLEDGE_LIST_URL);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {

//        LogUtils.d(infos.toString());
        adapter = new ItemListAdapter(getContext(), infos);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                goDetialView(position, Constant.KNOWLEDGE_DETIALS_URL);
//                LogUtils.d("V:" + v + "..." + position);
//                LogUtils.d(infos.get(position).toString());
            }
        });
        newsList.setAdapter(adapter);
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsList.setItemAnimator(new DefaultItemAnimator());


        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                if (showPage != 1)
                    showPage = 1;
                Request.getInstance().post(Constant.KNOWLEDGE_LIST_URL, buildMap(category.getId()), new RequestCallBack<List<ItemInfo>>() {
                    @Override
                    public void onPreLoad() {

                    }

                    @Override
                    public void onSuccess(List<ItemInfo> itemInfos) {
                        adapter.refreshData(itemInfos);
                        refreshLayout.finishRefresh();
                    }


                    @Override
                    public void onError(Throwable ex) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                ++showPage;
                Request.getInstance().post(Constant.KNOWLEDGE_LIST_URL, buildMap(category.getId()), new RequestCallBack<List<ItemInfo>>() {
                    @Override
                    public void onPreLoad() {

                    }

                    @Override
                    public void onSuccess(List<ItemInfo> itemInfos) {

                        if(itemInfos!=null&&itemInfos.size()>0){
                            adapter.loadMoreData(itemInfos);
                        }else {
                            Toast.makeText(getContext(),R.string.nullData,Toast.LENGTH_SHORT).show();
                        }

                        refreshLayout.finishRefreshLoadMore();
                    }


                    @Override
                    public void onError(Throwable ex) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();

        LogUtils.d("onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d("onDestroy");
    }




    //    @Override
//    Intent setDetialData(int position) {
//        return null;
//    }
}
