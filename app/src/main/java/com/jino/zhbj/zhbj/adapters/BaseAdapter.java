package com.jino.zhbj.zhbj.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public abstract class BaseAdapter<T, H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context context;
    protected LayoutInflater mInflater;
    protected List<T> t;
    protected int layoutID;
    private OnItemClickListener mListener;

    public BaseAdapter(Context context, List<T> t, int _layoutID) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.t = t;
        layoutID = _layoutID;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(layoutID, null, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {


        bindData(holder, getData(position));
    }

    @Override
    public int getItemCount() {
        if(t==null||t.size()<0){
            return 0;
        }
        return t.size();
    }



    public T getData(int position) {
        if(position>t.size()){
            return null;
        }
        return t.get(position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }


    protected abstract void bindData(BaseViewHolder holder, T data);

    public interface OnItemClickListener{
        public void onItemClick(View v,int position);
    }

}
