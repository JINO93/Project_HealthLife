package com.jino.zhbj.zhbj.activies;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jino.zhbj.R;
import com.jino.zhbj.zhbj.Constane.Constant;
import com.jino.zhbj.zhbj.models.NewsDetial;
import com.jino.zhbj.zhbj.net.Request;
import com.jino.zhbj.zhbj.net.RequestCallBack;
import com.jino.zhbj.zhbj.utils.LogUtils;
import com.mingle.widget.ShapeLoadingDialog;
import com.mingle.widget.ShapeLoadingView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.activity_news_detial)
public class NewsDetialActivity extends BaseAcitivity {

    @ViewInject(R.id.id_detial_toolBar)
    private Toolbar toolbar;
    @ViewInject(R.id.id_detial_time_tv)
    private TextView tv_Time;
    @ViewInject(R.id.id_detial_title_tv)
    private TextView tv_Title;
    @ViewInject(R.id.id_detial_category_tv)
    private TextView tv_Category;
    @ViewInject(R.id.id_detial_count_tv)
    private TextView tv_Count;
    @ViewInject(R.id.id_detial_contant_tv)
    private TextView tv_Content;
    @ViewInject(R.id.id_detial_img_sd)
    private SimpleDraweeView sd_Img;
    private String imgUrl;
    private ShapeLoadingDialog dialog;
    private NewsDetial newsDetial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                NewsDetialActivity.this.finish();
            }
        });


        dialog = new ShapeLoadingDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setLoadingText("正在加载中....");
        dialog.show();

    }

    private void initData() {
        int newsId = getIntent().getIntExtra(Constant.KEY_NEWS_DETAIL_ID, -1);
        imgUrl = getIntent().getStringExtra(Constant.KEY_NEWS_DETAIL_IMG);
//        LogUtils.d(newsId+">>>>>"+imgUrl);
        Map<String, Object> params = new HashMap<>();
        params.put("id", String.valueOf(newsId));
        Request.getInstance().post(Constant.NEWS_DETIALS_URL, params, new RequestCallBack<NewsDetial>() {
            @Override
            public void onPreLoad() {

            }

//            @Override
//            public void onSuccess(String s) {
//                LogUtils.d(s);
//            }

            @Override
            public void onSuccess(final NewsDetial newsDetial) {
                NewsDetialActivity.this.newsDetial = newsDetial;
                LogUtils.d(newsDetial.toString());
                loadData();

            }


            @Override
            public void onError(Throwable ex) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void loadData() {
        if (newsDetial != null) {
            sd_Img.setAspectRatio(1.33f);
            sd_Img.setImageURI(Uri.parse(Constant.IMG_BASE_URL + imgUrl));
            tv_Category.setText("分类：" + newsDetial.getTag());
            tv_Time.setText(newsDetial.getTime());
            tv_Count.setText(newsDetial.getCount() + "");
            tv_Title.setText(newsDetial.getTitle());
            LogUtils.d(newsDetial.getMessage());
            tv_Content.setText(Html.fromHtml(newsDetial.getMessage()));
            dialog.dismiss();
        }
    }


}
