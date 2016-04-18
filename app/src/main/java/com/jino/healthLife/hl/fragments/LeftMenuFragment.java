package com.jino.healthLife.hl.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jino.healthLife.R;
import com.jino.healthLife.hl.Constane.Constant;
import com.jino.healthLife.hl.adapters.ListsPagerAdapter;
import com.jino.healthLife.hl.models.Category;
import com.jino.healthLife.hl.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_left_menu)
public class LeftMenuFragment extends Fragment {


    private boolean injected;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        LogUtils.d("FFF");
        return x.view().inject(this, inflater, container);

//        View view=inflater.inflate(R.layout.fragment_left_menu,container,false);
//        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//

        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }



}
