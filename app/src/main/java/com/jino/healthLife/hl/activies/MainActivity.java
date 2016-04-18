package com.jino.healthLife.hl.activies;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jino.healthLife.R;
import com.jino.healthLife.hl.adapters.FoodListAdapter;
import com.jino.healthLife.hl.adapters.ListsPagerAdapter;
import com.jino.healthLife.hl.fragments.DrugFragment;
import com.jino.healthLife.hl.fragments.FoodFragment;
import com.jino.healthLife.hl.fragments.KnowledgeFragment;
import com.jino.healthLife.hl.fragments.LeftMenuFragment;
import com.jino.healthLife.hl.fragments.NewsFragment;
import com.jino.healthLife.hl.models.Category;

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
    @ViewInject(R.id.id_main_leftMenu)
    NavigationView leftMenu;
    @ViewInject(R.id.id_main_drawer)
    DrawerLayout drawer;

    KnowledgeFragment knowledgeFragment;
    private FoodFragment foodFragment;
    private NewsFragment newsFragment;
    private DrugFragment drugFragment;
    FragmentManager fm;
    long firstTime = 0;
    private ActionBarDrawerToggle toggle;


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

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.id_main_leftMenu, new LeftMenuFragment());
        transaction.commit();
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

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
                        showFragment(3);
                        setToolbarTitle(getString(R.string.tab_food));
                        break;

                    case R.id.id_bottomTap_drug:
                        showFragment(4);
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
                    fragmentTransaction.add(R.id.container, newsFragment);
                } else {

                    fragmentTransaction.show(newsFragment);
                }
                break;
            case 2:
                if (knowledgeFragment == null) {
                    knowledgeFragment = new KnowledgeFragment();
                    fragmentTransaction.add(R.id.container, knowledgeFragment);
                } else {

                    fragmentTransaction.show(knowledgeFragment);
                }
                break;
            case 3:
                if (foodFragment == null) {
                    foodFragment = new FoodFragment();
                    fragmentTransaction.add(R.id.container, foodFragment);
                } else {

                    fragmentTransaction.show(foodFragment);
                }
                break;
            case 4:
                if (drugFragment == null) {
                    drugFragment = new DrugFragment();
                    fragmentTransaction.add(R.id.container, drugFragment);
                } else {

                    fragmentTransaction.show(drugFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }


    private void clearSelection() {
        FragmentTransaction transaction = fm.beginTransaction();
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (knowledgeFragment != null) {
            transaction.hide(knowledgeFragment);
        }
        if(foodFragment!=null){
            transaction.hide(foodFragment);
        }
        if(drugFragment!=null){
            transaction.hide(drugFragment);
        }

        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - firstTime) > 2000) {
            Toast.makeText(getApplicationContext(), R.string.clickAgainExit, Toast.LENGTH_SHORT).show();
            firstTime = System.currentTimeMillis();
        } else {
            this.finish();
        }

//        super.onBackPressed();
    }

    private void setToolbarTitle(String title) {
        toolBarTitle.setText(title);
    }



    public void checkFragmentListData(int tag,Category category) {
        switch (tag) {
            case 1:
                newsFragment.checkListData(category);
                break;
            case 2:
                knowledgeFragment.checkListData(category);
                break;
            case 3:
                foodFragment.checkListData(category);
//                ListsPagerAdapter adapter = (ListsPagerAdapter) foodFragment.foodLists.getAdapter();
//                adapter.removeItem(category);
                break;
            case 4:
                break;
        }
    }
}
