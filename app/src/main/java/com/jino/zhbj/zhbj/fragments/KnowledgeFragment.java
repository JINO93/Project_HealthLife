package com.jino.zhbj.zhbj.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jino.zhbj.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_home)
public class KnowledgeFragment extends BaseFragment {

    @ViewInject(R.id.id_news_topTaps)
    TabLayout topTab;
    @ViewInject(R.id.id_news_vp)
    ViewPager newLists;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        topTab.setupWithViewPager(newLists);
    }


}
