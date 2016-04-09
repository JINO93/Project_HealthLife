package com.jino.zhbj.zhbj.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jino.zhbj.R;

/**
 * Created by Administrator on 2016/4/7.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final BaseAdapter.OnItemClickListener mListener;
    private SparseArray<View> views;

    public BaseViewHolder(View itemView, BaseAdapter.OnItemClickListener mListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        views = new SparseArray<>();
       this.mListener=mListener;
    }

    public TextView getTextView(int id) {
        return getView(id);
    }

    public Button getButton(int id) {
        return getView(id);
    }

    public SimpleDraweeView getSimpleDraweeView(int id) {
        return getView(id);
    }

    protected <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        if(mListener!=null){
            mListener.onItemClick(v,getLayoutPosition());
        }
    }
}
