package com.jino.zhbj.zhbj.net;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mingle.widget.LoadingView;

/**
 * Created by Administrator on 2016/4/6.
 */
public abstract class LoadingCallBack<T> extends RequestCallBack<T> {

    private final Context context;
    private Dialog dialog;
    private View loadingView;

    public LoadingCallBack(Context context) {
        this.context = context;
        initView(this.context);
    }

    private void initView(Context context) {
        loadingView = new LoadingView(context);
        ((LoadingView) loadingView).setLoadingText("拼命加载中...");
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.addContentView(loadingView, params);
        dialog.setCancelable(false);
    }


    public void setText(int resID){
        ((LoadingView) loadingView).setLoadingText(context.getString(resID));
    }

    public void dismissDialog() {
        dialog.dismiss();
    }

    public void showDialog() {
        dialog.show();
    }

    @Override
    public void onPreLoad() {
        showDialog();
    }


    @Override
    public void onError(Throwable ex) {
        dismissDialog();
    }

    @Override
    public void onFinish() {
        dismissDialog();
    }
}
