package com.jino.zhbj.zhbj.activies;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.jino.zhbj.R;
import com.jino.zhbj.zhbj.Constane.Constant;
import com.jino.zhbj.zhbj.adapters.MyPagerAdapter;
import com.jino.zhbj.zhbj.utils.PreferencesUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_guide)
public class GuideActivity extends BaseAcitivity {

    @ViewInject(R.id.vp_guide)
    private ViewPager vp_guide;

    @ViewInject(R.id.btn_enter)
    private Button btn_enter;

    List<ImageView> guidePics;
    private int[] guide_pics = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ImageView view = null;
        guidePics = new ArrayList<>();
        for (int i = 0; i < guide_pics.length; i++) {
            view = new ImageView(this);
            view.setImageResource(guide_pics[i]);
            guidePics.add(view);
            view = null;
        }
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(guidePics);
        vp_guide.setAdapter(myPagerAdapter);
        vp_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == guidePics.size() - 1) {
                    TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                            Animation.RELATIVE_TO_SELF, 0,
                            Animation.RELATIVE_TO_SELF, 50, Animation.RELATIVE_TO_SELF, 0);
                    translateAnimation.setDuration(1000);
                    btn_enter.startAnimation(translateAnimation);
                    btn_enter.setVisibility(View.VISIBLE);
                } else {
                    btn_enter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Event(R.id.btn_enter)
    private void enter(View view){
//        System.out.println("JJJJ");
        startActivity(new Intent(GuideActivity.this, MainActivity.class));
        PreferencesUtils.setBoolean(GuideActivity.this, Constant.IS_FIRST_ENTER,false);
        finish();
    }
}
