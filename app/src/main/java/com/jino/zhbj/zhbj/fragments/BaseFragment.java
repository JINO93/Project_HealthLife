package com.jino.zhbj.zhbj.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jino.zhbj.zhbj.Constane.Constant;
import com.jino.zhbj.zhbj.adapters.ListsPagerAdapter;
import com.jino.zhbj.zhbj.models.Category;
import com.jino.zhbj.zhbj.net.Request;
import com.jino.zhbj.zhbj.net.RequestCallBack;
import com.jino.zhbj.zhbj.utils.LogUtils;

import org.xutils.x;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseFragment extends Fragment {

    protected List<Category> tabs;
    protected FragmentManager fm;
    protected ListsPagerAdapter adapter;


    private boolean injected = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }

        tabs = new ArrayList<>();
        fm = getFragmentManager();
    }


    protected void initData(String url) {
        Request.getInstance().get(url, new RequestCallBack<List<Category>>() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public void onSuccess(List<Category> categories) {
                tabs = categories;
                LogUtils.d(tabs.toString());
//                getPagerData(tabs);
                initView();
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

    protected abstract void initView() ;
    protected abstract void initFragments() ;
}
