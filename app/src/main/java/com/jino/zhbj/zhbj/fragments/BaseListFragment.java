package com.jino.zhbj.zhbj.fragments;

import android.content.Intent;
import android.os.Bundle;

import com.jino.zhbj.zhbj.Constane.Constant;
import com.jino.zhbj.zhbj.models.Bean;
import com.jino.zhbj.zhbj.net.Request;
import com.jino.zhbj.zhbj.net.RequestCallBack;
import com.jino.zhbj.zhbj.utils.LogUtils;
import com.mingle.widget.ShapeLoadingDialog;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */
public abstract class BaseListFragment<T extends Bean, D extends Bean> extends BaseFragment {

    protected ShapeLoadingDialog loadingDialog;

    protected List<T> datas;
    protected D tab;

    public int showPage = 2;
    public int showItems = 20;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new ShapeLoadingDialog(getActivity());
        loadingDialog.setLoadingText("拼命加载中....");
        loadingDialog.setCanceledOnTouchOutside(false);
        tab = (D) getArguments().get(Constant.KEY_TAB_INFO);

        LogUtils.d(tab.toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoading();
//        initData("");
    }

    protected void showLoading() {
        loadingDialog.show();
    }

    protected void dismissLoading() {
        loadingDialog.dismiss();
    }

    protected void goDetialView(int position) {
        //TODO
        Intent intent = setDetialData(position);
        getActivity().startActivity(intent);
    }





    protected abstract Map<String, Object> buildMap(D bean);

    protected abstract void initView();

    abstract Intent setDetialData(int position);
}
