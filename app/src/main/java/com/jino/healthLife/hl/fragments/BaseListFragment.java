package com.jino.healthLife.hl.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jino.healthLife.hl.Constane.Constant;
import com.jino.healthLife.hl.activies.ItemDetialActivity;
import com.jino.healthLife.hl.activies.MainActivity;
import com.jino.healthLife.hl.models.Bean;
import com.jino.healthLife.hl.models.Category;
import com.jino.healthLife.hl.models.ItemInfo;
import com.jino.healthLife.hl.net.Request;
import com.jino.healthLife.hl.net.RequestCallBack;
import com.jino.healthLife.hl.utils.LogUtils;
import com.mingle.widget.ShapeLoadingDialog;

import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */
public abstract class BaseListFragment extends Fragment {

    public static final String LIST_DATA = "datas";

    protected ShapeLoadingDialog loadingDialog;
    protected Category category;
    protected List<ItemInfo> infos;
    protected boolean isVisible=false;

//    protected List<T> datas;
//    protected D tab;

    public int showPage = 1;
    public int showItems = 20;


    private boolean injected = false;


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(getUserVisibleHint()==true){
//
//        }
//
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new ShapeLoadingDialog(getActivity());
        loadingDialog.setLoadingText("拼命加载中....");
        loadingDialog.setCanceledOnTouchOutside(false);
        showLoading();
//        category = (Category) getArguments().getSerializable(LIST_DATA);

//        LogUtils.d(tab.toString());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    protected void showLoading() {
        loadingDialog.show();
    }

    protected void dismissLoading() {
        loadingDialog.dismiss();
    }


    protected void goDetialView(int position, String url) {
        //TODO
        Intent intent = setDetialData(position, url);
        getActivity().startActivity(intent);
    }


    protected Bundle setBundle(Category c) {

        Bundle b = new Bundle();
        b.putSerializable(LIST_DATA, c);
        return b;
    }


    protected Map<String, Object> buildMap(long id) {
//        LogUtils.d(bean.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("page", showPage);
        map.put("limit", showItems);
        map.put("id", String.valueOf(id));
        return map;
    }


//    protected void loadMoreData(String url){
//        Request.getInstance().post(url, buildMap(category.getId()), new RequestCallBack<List<ItemInfo>>() {
//            @Override
//            public void onPreLoad() {
//
//            }
//
//            @Override
//            public void onSuccess(List<ItemInfo> itemInfos) {
//
//            }
//
//
//            @Override
//            public void onError(Throwable ex) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
//    }


    protected void initData(String url) {
        Request.getInstance().post(url, buildMap(category.getId()), new RequestCallBack<List<ItemInfo>>() {
            @Override
            public void onPreLoad() {
//                LogUtils.d("FFF");
            }

            @Override
            public void onSuccess(List<ItemInfo> newsInfos) {
                infos = newsInfos;
//                isLoading = false;
                LogUtils.d(newsInfos.toString());
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


    protected Intent setDetialData(int position, String url) {
        ItemInfo newsInfo = infos.get(position);
        String imgUri = newsInfo.getImg();
        int newsID = newsInfo.getId();
//        LogUtils.d("FFF");
//        LogUtils.d(newsID + "");
        Intent intent = new Intent();
        intent.setClass(getContext(), ItemDetialActivity.class);
        Bundle itemDetial = new Bundle();
        itemDetial.putCharSequence(Constant.KEY_ITEM_DETAIL_URL, url);
        itemDetial.putInt(Constant.KEY_ITEM_DETAIL_ID, newsID);
        itemDetial.putCharSequence(Constant.KEY_ITEM_DETAIL_IMG, imgUri);
        intent.putExtras(itemDetial);
        return intent;
    }

    protected abstract void initView();



    public void checkChangeData(int tag,Category category){
        MainActivity activity = (MainActivity) getActivity();
        activity.checkFragmentListData(tag,category);
    }

//    protected abstract void initData();

//    abstract Intent setDetialData(int position);
}
