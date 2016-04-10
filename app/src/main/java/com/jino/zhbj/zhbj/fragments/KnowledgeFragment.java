package com.jino.zhbj.zhbj.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jino.zhbj.R;
import com.jino.zhbj.zhbj.Constane.Constant;
import com.jino.zhbj.zhbj.adapters.ListsPagerAdapter;
import com.jino.zhbj.zhbj.models.Category;
import com.jino.zhbj.zhbj.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_konwledge)
public class KnowledgeFragment extends BaseFragment {

    @ViewInject(R.id.id_knowledge_topTaps)
    TabLayout topTab;
    @ViewInject(R.id.id_knowledge_vp)
    ViewPager knowledgePager;
    private List<KnowledgeListFragment> fragments;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData(Constant.KNOWLEDGE_CATEGORY_URL);

    }

    @Override
    protected void initView() {
        initFragments();
        LogUtils.d(tabs.toString());
        adapter = new ListsPagerAdapter(fm, tabs, fragments);
        knowledgePager.setAdapter(adapter);
        topTab.setupWithViewPager(knowledgePager);

    }

    @Override
    protected void initFragments() {
        fragments = new ArrayList<>();
        KnowledgeListFragment fragment=null;
        Bundle b=null;
        for(Category c: tabs){
            fragment= new KnowledgeListFragment();
            b=new Bundle();
            b.putSerializable(String.valueOf(fragment.hashCode()),c);
            fragment.setArguments(b);
            fragments.add(fragment);
//            fragment=null;
        }
    }


}
