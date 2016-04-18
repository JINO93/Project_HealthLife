package com.jino.healthLife.hl.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.jino.healthLife.R;
import com.jino.healthLife.hl.Constane.Constant;
import com.jino.healthLife.hl.adapters.BaseAdapter;
import com.jino.healthLife.hl.adapters.ItemListAdapter;
import com.jino.healthLife.hl.models.Category;
import com.jino.healthLife.hl.models.ItemInfo;
import com.jino.healthLife.hl.net.Request;
import com.jino.healthLife.hl.net.RequestCallBack;
import com.jino.healthLife.hl.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.fragment_item_list)
public class NewsListFragment extends BaseListFragment {


//    public static final String NEWSFRAGMENT_NEWSINFO = "newInfo";

    @ViewInject(R.id.id_item_list_rv)
    RecyclerView newsList;
    @ViewInject(R.id.id_item_refresh)
    private MaterialRefreshLayout refreshLayout;

//    private Category tab;

    private ItemListAdapter adapter;
//    private List<ItemInfo> newsInfos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category= (Category) getArguments().getSerializable(Constant.KEY_NEWS_TAB_DATA);
        LogUtils.d(category.toString());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initData(Constant.NEWS_LIST_URL);
        return super.onCreateView(inflater, container, savedInstanceState);
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

        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                if (showPage != 1)
                    showPage = 1;
                Request.getInstance().post(Constant.NEWS_LIST_URL, buildMap(category.getId()), new RequestCallBack<List<ItemInfo>>() {
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
                Request.getInstance().post(Constant.NEWS_LIST_URL, buildMap(category.getId()), new RequestCallBack<List<ItemInfo>>() {
                    @Override
                    public void onPreLoad() {

                    }

                    @Override
                    public void onSuccess(List<ItemInfo> itemInfos) {

                        if (itemInfos != null && itemInfos.size() > 0) {
                            adapter.loadMoreData(itemInfos);
                        } else {
                            Toast.makeText(getContext(), R.string.isBottom, Toast.LENGTH_SHORT).show();
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









}
