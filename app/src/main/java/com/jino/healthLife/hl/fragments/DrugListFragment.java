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
import com.jino.healthLife.hl.activies.DrugDetialActivity;
import com.jino.healthLife.hl.activies.ItemDetialActivity;
import com.jino.healthLife.hl.adapters.BaseAdapter;
import com.jino.healthLife.hl.adapters.DrugListAdapter;
import com.jino.healthLife.hl.adapters.ItemListAdapter;
import com.jino.healthLife.hl.models.Category;
import com.jino.healthLife.hl.models.DrugInfo;
import com.jino.healthLife.hl.models.ItemInfo;
import com.jino.healthLife.hl.net.Request;
import com.jino.healthLife.hl.net.RequestCallBack;
import com.jino.healthLife.hl.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.fragment_item_list)
public class DrugListFragment extends BaseListFragment {

    @ViewInject(R.id.id_item_refresh)
    private MaterialRefreshLayout refreshLayout;
    @ViewInject(R.id.id_item_list_rv)
    RecyclerView drugList;

    private DrugListAdapter adapter;
    private List<DrugInfo> drugInfos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = (Category) getArguments().getSerializable(String.valueOf(this.hashCode()));
//        LogUtils.d("onCreate");

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        LogUtils.d("onCreateView");
        initData(Constant.DRUG_LIST_URL);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected void initData(String url) {
        Request.getInstance().post(url, buildMap(category.getId()), new RequestCallBack<List<DrugInfo>>() {
            @Override
            public void onPreLoad() {
//                LogUtils.d("FFF");
            }

            @Override
            public void onSuccess(List<DrugInfo> drugInfos) {
                DrugListFragment.this.drugInfos = drugInfos;
//                isLoading = false;
                LogUtils.d(drugInfos.toString());
//                adapter.notifyDataSetChanged();
                initView();
                dismissLoading();
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

    @Override
    protected void initView() {

        LogUtils.d(drugInfos.toString());
        adapter = new DrugListAdapter(getContext(),  drugInfos);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                goDetialView(position, Constant.DRUG_DETIALS_URL);
//                LogUtils.d("V:" + v + "..." + position);
//                LogUtils.d(infos.get(position).toString());
            }
        });
        drugList.setAdapter(adapter);
        drugList.setLayoutManager(new LinearLayoutManager(getContext()));
        drugList.setItemAnimator(new DefaultItemAnimator());


        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                if (showPage != 1)
                    showPage = 1;
                Request.getInstance().post(Constant.DRUG_LIST_URL, buildMap(category.getId()), new RequestCallBack<List<DrugInfo>>() {
                    @Override
                    public void onPreLoad() {

                    }

                    @Override
                    public void onSuccess(List<DrugInfo> itemInfos) {
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
                Request.getInstance().post(Constant.DRUG_LIST_URL, buildMap(category.getId()), new RequestCallBack<List<DrugInfo>>() {
                    @Override
                    public void onPreLoad() {

                    }

                    @Override
                    public void onSuccess(List<DrugInfo> drugInfos) {
                        if (drugInfos != null && drugInfos.size() > 0) {
                            adapter.loadMoreData(drugInfos);
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
    protected Intent setDetialData(int position, String url) {
        DrugInfo drugInfo = drugInfos.get(position);
        String imgUri = drugInfo.getImage();
        int newsID = drugInfo.getId();
//        LogUtils.d("FFF");
//        LogUtils.d(newsID + "");
        Intent intent = new Intent();
        intent.setClass(getContext(), DrugDetialActivity.class);
        Bundle itemDetial = new Bundle();
        itemDetial.putCharSequence(Constant.KEY_ITEM_DETAIL_URL, url);
        itemDetial.putInt(Constant.KEY_ITEM_DETAIL_ID, newsID);
        itemDetial.putCharSequence(Constant.KEY_ITEM_DETAIL_IMG, imgUri);
        intent.putExtras(itemDetial);
        return intent;
    }
}
