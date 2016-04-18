package com.jino.healthLife.hl.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.jino.healthLife.hl.view.ExpandableMenu;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.fragment_food)
public class FoodFragment extends BaseFragment {

    @ViewInject(R.id.id_top_menu)
    ExpandableMenu topMenu;
    @ViewInject(R.id.id_food_list_rv)
    public RecyclerView foodLists;
    @ViewInject(R.id.id_detial_refreshLayout)
    MaterialRefreshLayout refreshLayout;


//    List<Category> tabs;
//    List<List<ItemInfo>> newsInfoList;
//    private FragmentManager fm;


    private List<FoodListFragment> fragments;
    private HashMap<String, List<Category>> tabDatas;
    private FoodListAdapter foodListAdapter;
    private List<FoodInfo> foodInfos;

    private int showPage = 1;
    private int showItems = 20;
    private String currentId;
    private boolean isLast;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        tabs = new ArrayList<>();
//        fm = getFragmentManager();
        initData(Constant.FOOD_CATEGORY_URL);
//        LogUtils.d("FFF");
//        initView();
    }

    @Override
    protected void initData(String url) {
//        super.initData(url);
        Request.getInstance().get(url, new RequestCallBack<List<Category>>() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public void onSuccess(List<Category> categories) {
                tabs = categories;
//                LogUtils.d(tabs.toString());
                getSecondCategory(tabs);
//                getPagerData(tabs);
//                initView();
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

    protected void initView() {

        foodInfos = new ArrayList<>();

//        initFragments();
//        adapter = new ListsPagerAdapter(fm, tabs, fragments);
//        foodLists.setAdapter(adapter);
//        topTab.setupWithViewPager(foodLists);

        topMenu.setRightItemClickListener(new ExpandableMenu.OnRightItemClickListener() {
            @Override
            public void onClick(String id, int position) {
//                setListData(id)
                LogUtils.d(id);
                currentId = id;
                if (showPage != 1)
                    showPage = 1;

                if (isLast)
                    isLast = !isLast;
                setListData(id, showPage);
            }
        });
        topMenu.setDatas(tabDatas);

        foodListAdapter = new FoodListAdapter(getContext(), foodInfos);
        foodListAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = setDetialData(position, Constant.FOOD_DETIALS_URL);
                getActivity().startActivity(intent);
            }
        });
        foodLists.setAdapter(foodListAdapter);
        foodLists.setLayoutManager(new LinearLayoutManager(getContext()));
        foodLists.setItemAnimator(new DefaultItemAnimator());


        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                if (showPage != 1) showPage = 1;
                setListData(currentId, showPage);

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);

                LogUtils.d("FFF");

                if (isLast) {

                    Toast.makeText(getContext(), R.string.isBottom, Toast.LENGTH_SHORT).show();
                    refreshLayout.finishRefreshLoadMore();
                    return;
                }

                setListData(currentId, ++showPage);
            }
        });
    }


    private Intent setDetialData(int position, String url) {
        FoodInfo foodInfo = foodInfos.get(position);
        int id_Detail = foodInfo.getId();
        String imgUrl = foodInfo.getImg();
        Intent intent = new Intent();
        intent.setClass(getContext(), FoodDetialActivity.class);
        Bundle b = new Bundle();
        b.putInt(Constant.KEY_ITEM_DETAIL_ID, id_Detail);
        b.putCharSequence(Constant.KEY_ITEM_DETAIL_IMG, imgUrl);
        intent.putExtras(b);
        return intent;
    }

    private void setListData(String id, final int showPagerNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", showPagerNum);
        params.put("limit", showItems);
        params.put("id", id);

        Request.getInstance().post(Constant.FOOD_LIST_URL, params, new RequestCallBack<List<FoodInfo>>() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public void onSuccess(List<FoodInfo> foodInfos) {
//                FoodFragment.this.foodInfos=foodInfos;
                LogUtils.d(foodInfos.toString());
                if (showPagerNum > 1) {
                    if (foodInfos.size() <= 0 || foodInfos == null) {
                        isLast = true;
                        Toast.makeText(getContext(), R.string.isBottom, Toast.LENGTH_SHORT).show();
                    } else
                        foodListAdapter.loadMoreData(foodInfos);
                    refreshLayout.finishRefreshLoadMore();
                } else {
                    if (foodInfos.size() <= 0 || foodInfos == null) {

                        Toast.makeText(getContext(), R.string.nullData, Toast.LENGTH_SHORT).show();
                    }
                    foodListAdapter.refreshData(foodInfos);
                    refreshLayout.finishRefresh();
                }
            }


            @Override
            public void onError(Throwable ex) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void getSecondCategory(final List<Category> tabs) {
//        LogUtils.d("FFF");

        tabDatas = new HashMap<String, List<Category>>();

        Map<String, Object> map = null;
        for (int i = 0; i < tabs.size(); i++) {
            map = new HashMap<>();

            final Category c = tabs.get(i);
            final int finalI = i;
            map.put("id", c.getId());
            Request.getInstance().post(Constant.FOOD_CATEGORY_URL, map, new RequestCallBack<List<Category>>() {
                @Override
                public void onPreLoad() {

                }

                @Override
                public void onSuccess(List<Category> categories) {
//                    LogUtils.d("FFF");
//                    LogUtils.d(categories.toString());
                    tabDatas.put(c.getName(), categories);

                    if (finalI == (tabs.size() - 1)) {
                        LogUtils.d(tabDatas.toString());
                        initView();
                    }
                }


                @Override
                public void onError(Throwable ex) {

                    LogUtils.d(ex.toString());
                }

                @Override
                public void onFinish() {

//                    LogUtils.d("OnFinish");
                }
            });
        }


    }

    protected void initFragments() {
//        LogUtils.d(tabs.toString());

        fragments = new ArrayList<>();
        FoodListFragment fragment = null;
        Bundle b = null;
        for (Category c : tabs) {
            fragment = new FoodListFragment();
            b = new Bundle();
            b.putSerializable(String.valueOf(fragment.hashCode()), c);
            fragment.setArguments(b);
            fragments.add(fragment);
//            fragment=null;
        }
    }


}
