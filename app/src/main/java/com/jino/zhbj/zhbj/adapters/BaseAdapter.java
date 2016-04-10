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
    protected List<T> datas;
    protected int layoutID;
    private OnItemClickListener mListener;

    public BaseAdapter(Context context, List<T> t, int _layoutID) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.datas = t;
        layoutID = _layoutID;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(layoutID, null, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {


        bindData(holder, getData(position));
    }

    @Override
    public int getItemCount() {
        if (datas == null || datas.size() < 0) {
            return 0;
        }
        return datas.size();
    }


    public T getData(int position) {
        if (position > datas.size()) {
            return null;
        }
        return datas.get(position);
    }


    public void add(int position, List<T> datas) {

        if (datas != null && datas.size() > 0) {

            this.datas.addAll(position, datas);
            notifyItemRangeInserted(position, datas.size());
        }
    }

    public void remove(T t){
        int position =datas.indexOf(t);
        datas.remove(position);
        notifyItemRemoved(position);
    }

    public void clear(){
        datas.clear();
        notifyDataSetChanged();
    }


    public void refreshData(List<T> datas){

        clear();
        add(0, datas);
    }


    public void loadMoreData(List<T> datas){
        add(this.datas.size(),datas);
    }



    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    protected abstract void bindData(BaseViewHolder holder, T data);

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

}
