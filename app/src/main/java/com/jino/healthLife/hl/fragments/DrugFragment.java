package com.jino.healthLife.hl.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jino.healthLife.R;
import com.jino.healthLife.hl.Constane.Constant;
import com.jino.healthLife.hl.adapters.ListsPagerAdapter;
import com.jino.healthLife.hl.models.Category;
import com.jino.healthLife.hl.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_drug)
public class DrugFragment extends BaseFragment {

    @ViewInject(R.id.id_drug_topTaps)
    TabLayout topTab;
    @ViewInject(R.id.id_drug_vp)
    ViewPager drugsPager;
    private List<DrugListFragment> fragments;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.d("FFF");

        initData(Constant.DRUG_CATEGORY_URL);

    }

    @Override
    protected void initView() {
        initFragments();
        LogUtils.d(tabs.toString());
        adapter = new ListsPagerAdapter(fm, tabs, fragments);
        drugsPager.setAdapter(adapter);
        topTab.setupWithViewPager(drugsPager);

    }

    @Override
    protected void initFragments() {
        fragments = new ArrayList<>();
        DrugListFragment fragment=null;
        Bundle b=null;
        for(Category c: tabs){
            fragment= new DrugListFragment();
            b=new Bundle();
            b.putSerializable(String.valueOf(fragment.hashCode()),c);
            fragment.setArguments(b);
            fragments.add(fragment);
//            fragment=null;
        }
    }


}
