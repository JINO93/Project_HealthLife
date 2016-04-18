package com.jino.healthLife.hl.activies;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jino.healthLife.R;
import com.jino.healthLife.hl.Constane.Constant;
import com.jino.healthLife.hl.models.FoodDetial;
import com.jino.healthLife.hl.models.ItemDetial;
import com.jino.healthLife.hl.net.Request;
import com.jino.healthLife.hl.net.RequestCallBack;
import com.jino.healthLife.hl.utils.LogUtils;
import com.komi.slider.SliderConfig;
import com.komi.slider.SliderUtils;
import com.mingle.widget.ShapeLoadingDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.activity_food_detial)
public class FoodDetialActivity extends BaseAcitivity {

    @ViewInject(R.id.id_detial_toolBar)
    private Toolbar toolbar;
    @ViewInject(R.id.id_detial_img_sd)
    private SimpleDraweeView sd_Img;
    @ViewInject(R.id.id_detial_toolBarLayout)
    private CollapsingToolbarLayout toolbarLayout;

//    @ViewInject(R.id.id_detial_title_tv)
//    private TextView tv_Title;
//    @ViewInject(R.id.id_detial_source_tv)
//    private TextView tv_Source;
    @ViewInject(R.id.id_detial_contant_tv)
    private TextView tv_Content;


    private String imgUrl;
    private ShapeLoadingDialog dialog;
    private FoodDetial foodDetial;
    private SliderConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config=new SliderConfig.Builder().slideExit(true).edge(true).build();
        SliderUtils.attachActivity(this, config);
        initView();
        initData();
    }

    private void initView() {
        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodDetialActivity.this.finish();
            }
        });


        dialog = new ShapeLoadingDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setLoadingText("正在加载中....");
        dialog.show();

    }

    private void initData() {
        int foodId = getIntent().getIntExtra(Constant.KEY_ITEM_DETAIL_ID, -1);
        imgUrl = getIntent().getStringExtra(Constant.KEY_ITEM_DETAIL_IMG);
//        String url = getIntent().getStringExtra(Constant.KEY_ITEM_DETAIL_URL);
        LogUtils.d(foodId + ">>>>>" + imgUrl);
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", foodId);
        String url=Constant.FOOD_DETIALS_URL+"?id="+String.valueOf(foodId);
//        String url="http://api.1ccf.com/cook/show?id=44807";
        Request.getInstance().get(url, new RequestCallBack<FoodDetial>() {
            @Override
            public void onPreLoad() {

                LogUtils.d("onPreLoad");
            }

            @Override
            public void onSuccess(FoodDetial foodDetial) {
                FoodDetialActivity.this.foodDetial = foodDetial;
                LogUtils.d(foodDetial.toString());
                loadData();
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

    private void loadData() {
        if (foodDetial != null) {
            sd_Img.setAspectRatio(1.33f);
            sd_Img.setImageURI(Uri.parse(Constant.IMG_BASE_URL + imgUrl));
            toolbarLayout.setTitle(foodDetial.getName());
//            tv_Source.setText(foodDetial.getFood());
//            tv_Title.setText(foodDetial.getName());
//            LogUtils.d(foodDetial.getMessage());
            tv_Content.setText(Html.fromHtml(foodDetial.getMessage()));
            dialog.dismiss();
        }
    }


}
