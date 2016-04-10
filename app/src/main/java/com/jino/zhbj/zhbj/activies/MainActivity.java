package com.jino.zhbj.zhbj.activies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jino.zhbj.R;
import com.jino.zhbj.zhbj.fragments.BaseFragment;
import com.jino.zhbj.zhbj.fragments.KnowledgeFragment;
import com.jino.zhbj.zhbj.fragments.NewsFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseAcitivity {

    @ViewInject(R.id.toolbar_main)
    private Toolbar toolbar;
    @ViewInject(R.id.rg_bottomTap)
    RadioGroup bottomTap;
    @ViewInject(R.id.tv_toolbar_title)
    TextView toolBarTitle;

    KnowledgeFragment knowledgeFragment;
    NewsFragment newsFragment;
    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        fm = getSupportFragmentManager();
        initView();
        initData();
        showFragment(1);
    }

    private void initData() {


    }

    private void initView() {
        bottomTap.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.id_bottomTap_news:
                        setToolbarTitle(getString(R.string.tab_news));
                        showFragment(1);
                        break;
                    case R.id.id_bottomTap_knowledge:
                        setToolbarTitle(getString(R.string.tab_knowledge));
                        showFragment(2);
                        break;

                    case R.id.id_bottomTap_food:
                        setToolbarTitle(getString(R.string.tab_food));
                        break;

                    case R.id.id_bottomTap_drug:
                        setToolbarTitle(getString(R.string.tab_drug));
                        break;

                }
            }
        });

    }

    private void showFragment(int fragmentId) {
        clearSelection();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (fragmentId) {
            case 1:

                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    fragmentTransaction.add(R.id.container,newsFragment);
                } else {

                    fragmentTransaction.show(newsFragment);
                }
                break;
            case 2:
                if (knowledgeFragment == null) {
                    knowledgeFragment = new KnowledgeFragment();
                    fragmentTransaction.add(R.id.container,knowledgeFragment);
                } else {

                    fragmentTransaction.show(knowledgeFragment);
                }
                break;
            case 3:
                break;
            case 4:
                break;
        }
        fragmentTransaction.commit();
    }


    private void clearSelection(){
        FragmentTransaction transaction = fm.beginTransaction();
        if (newsFragment!=null){
            transaction.hide(newsFragment);
        }
        if (knowledgeFragment!=null){
            transaction.hide(knowledgeFragment);
        }

        transaction.commit();
    }


    private void setToolbarTitle(String title) {
        toolBarTitle.setText(title);
    }
}
