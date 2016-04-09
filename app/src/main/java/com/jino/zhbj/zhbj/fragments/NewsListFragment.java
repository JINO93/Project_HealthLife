package com.jino.zhbj.zhbj.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jino.zhbj.R;
import com.jino.zhbj.zhbj.Constane.Constant;
import com.jino.zhbj.zhbj.activies.NewsDetialActivity;
import com.jino.zhbj.zhbj.adapters.BaseAdapter;
import com.jino.zhbj.zhbj.adapters.NewsListAdapter;
import com.jino.zhbj.zhbj.models.Category;
import com.jino.zhbj.zhbj.models.NewsInfo;
import com.jino.zhbj.zhbj.net.Request;
import com.jino.zhbj.zhbj.net.RequestCallBack;
import com.jino.zhbj.zhbj.utils.LogUtils;
import com.mingle.widget.ShapeLoadingDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.fragment_news_list)
public class NewsListFragment extends BaseFragment {


    public static final String NEWSFRAGMENT_NEWSINFO = "newInfo";

    @ViewInject(R.id.id_news_list_rv)
    RecyclerView newsList;

//    private Category tab;

    private NewsListAdapter adapter;
    private List<NewsInfo> newsInfos;
    private ShapeLoadingDialog loadingDialog;

    private Category category;

    public int showPage = 2;
    public int showItems = 20;


    public static NewsListFragment newInstance(Category newInfo) {
        LogUtils.d("FFF");
        NewsListFragment newsListFragment = new NewsListFragment();
        Bundle b = new Bundle();
        b.putSerializable(NEWSFRAGMENT_NEWSINFO, newInfo);
        newsListFragment.setArguments(b);
        return newsListFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new ShapeLoadingDialog(getActivity());
        loadingDialog.setLoadingText("拼命加载中....");
        loadingDialog.setCanceledOnTouchOutside(false);
        showLoading();
        category = (Category) getArguments().getSerializable(NEWSFRAGMENT_NEWSINFO);
        LogUtils.d(category.toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        initData(Constant.NEWS_LIST_URL);
    }


    protected Map<String, Object> buildMap(Category bean) {
        LogUtils.d(bean.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("page", showPage);
        map.put("limit", showItems);
        map.put("id", bean.getId());
        return map;
    }


    protected void initView() {
//        LogUtils.d(datas.toString());
//        newsInfos=datas;
        adapter = new NewsListAdapter(getContext(), newsInfos);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                goDetialView(position);
//                LogUtils.d("V:" + v + "..." + position);
            }
        });
        newsList.setAdapter(adapter);
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsList.setItemAnimator(new DefaultItemAnimator());
    }


    protected void initData(String url) {
        Request.getInstance().post(url, buildMap(category), new RequestCallBack<List<NewsInfo>>() {
            @Override
            public void onPreLoad() {
                LogUtils.d("FFF");
            }

            @Override
            public void onSuccess(List<NewsInfo> newsInfos) {
                NewsListFragment.this.newsInfos = newsInfos;
//                isLoading = false;
                LogUtils.d(newsInfos.toString());
//                adapter.notifyDataSetChanged();
                initView();
                dismissLoading();
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


    private void goDetialView(int position) {
        //TODO
        Intent intent = setDetialData(position);
        getActivity().startActivity(intent);
    }


    private Intent setDetialData(int position) {
        NewsInfo newsInfo = newsInfos.get(position);
        String imgUri = newsInfo.getImg();
        int newsID = newsInfo.getId();
        LogUtils.d("FFF");
        LogUtils.d(newsID + "");
        Intent intent = new Intent();
        intent.setClass(getContext(), NewsDetialActivity.class);
        Bundle newsDetial = new Bundle();
        newsDetial.putInt(Constant.KEY_NEWS_DETAIL_ID, newsID);
        newsDetial.putCharSequence(Constant.KEY_NEWS_DETAIL_IMG, imgUri);
        intent.putExtras(newsDetial);
        return intent;
    }

    public void showLoading() {
        loadingDialog.show();
    }

    public void dismissLoading() {
        loadingDialog.dismiss();
    }

}
