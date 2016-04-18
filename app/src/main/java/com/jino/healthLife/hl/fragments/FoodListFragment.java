package com.jino.healthLife.hl.fragments;

import android.content.Intent;
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
import com.jino.healthLife.hl.activies.FoodDetialActivity;
import com.jino.healthLife.hl.adapters.BaseAdapter;
import com.jino.healthLife.hl.adapters.FoodListAdapter;
import com.jino.healthLife.hl.models.Category;
import com.jino.healthLife.hl.models.FoodInfo;
import com.jino.healthLife.hl.net.Request;
import com.jino.healthLife.hl.net.RequestCallBack;
import com.jino.healthLife.hl.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.fragment_item_list)
public class FoodListFragment extends BaseListFragment {

    @ViewInject(R.id.id_item_refresh)
    private MaterialRefreshLayout refreshLayout;
    @ViewInject(R.id.id_item_list_rv)
    RecyclerView newsList;

    private FoodListAdapter adapter;
    private List<FoodInfo> foodInfos;

    public boolean isLoaded=false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = (Category) getArguments().getSerializable(String.valueOf(this.hashCode()));
//        LogUtils.d(getActivity().getClass().getName());
        LogUtils.d(category.toString());

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        LogUtils.d("onCreateView");
        initData(Constant.FOOD_LIST_URL);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
        LogUtils.d("FFF");

//        if(foodInfos==null||foodInfos.size()<=0){
//            checkChangeData(3,category);
//            this.onDestroy();
//            return;
//        }

        LogUtils.d(foodInfos.toString());
        adapter = new FoodListAdapter(getContext(), foodInfos);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                goDetialView(position, Constant.FOOD_DETIALS_URL);
//                LogUtils.d("V:" + v + "..." + position);
                LogUtils.d(foodInfos.get(position).toString());
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
                Request.getInstance().post(Constant.FOOD_LIST_URL, buildMap(category.getId()), new RequestCallBack<List<FoodInfo>>() {
                    @Override
                    public void onPreLoad() {

                    }

                    @Override
                    public void onSuccess(List<FoodInfo> foodInfos) {
                        adapter.refreshData(foodInfos);
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
                Request.getInstance().post(Constant.FOOD_LIST_URL, buildMap(category.getId()), new RequestCallBack<List<FoodInfo>>() {
                    @Override
                    public void onPreLoad() {

                    }

                    @Override
                    public void onSuccess(List<FoodInfo> foodInfos) {
                        if (foodInfos != null && foodInfos.size() > 0) {
                            adapter.loadMoreData(foodInfos);
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


    @Override
    protected void initData(String url) {
        Request.getInstance().post(url, buildMap(category.getId()), new RequestCallBack<List<FoodInfo>>() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public void onSuccess(List<FoodInfo> foodInfos) {
               FoodListFragment.this.foodInfos=foodInfos;
                isLoaded=true;
                initView();
                dismissLoading();
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
    protected Intent setDetialData(int position, String url) {
        FoodInfo foodInfo = foodInfos.get(position);
        int id_Detail = foodInfo.getId();
        String imgUrl=foodInfo.getImg();
        Intent intent =new Intent();
        intent.setClass(getContext(), FoodDetialActivity.class);
        Bundle b=new Bundle();
        b.putInt(Constant.KEY_ITEM_DETAIL_ID,id_Detail);
        b.putCharSequence(Constant.KEY_ITEM_DETAIL_IMG,imgUrl);
        intent.putExtras(b);
        return intent;
    }
}
