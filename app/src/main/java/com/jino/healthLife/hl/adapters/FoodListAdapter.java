package com.jino.healthLife.hl.adapters;

import android.content.Context;
import android.net.Uri;

import com.jino.healthLife.R;
import com.jino.healthLife.hl.Constane.Constant;
import com.jino.healthLife.hl.models.FoodInfo;
import com.jino.healthLife.hl.models.ItemInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class FoodListAdapter extends BaseAdapter<FoodInfo,BaseViewHolder> {


    public FoodListAdapter(Context context, List<FoodInfo> t) {
        super(context, t, R.layout.item_food_list);
    }


    @Override
    protected void bindData(BaseViewHolder holder, FoodInfo data) {

        if(data==null){
            return;
        }

        String url= Constant.IMG_BASE_URL +data.getImg();
        holder.getSimpleDraweeView(R.id.iv_preview).setImageURI(Uri.parse(url));
        holder.getTextView(R.id.tv_category).setText("标签："+data.getTag());
        holder.getTextView(R.id.tv_count).setText(data.getCount() + "");
        holder.getTextView(R.id.tv_itemTitle).setText(data.getName());
        holder.getTextView(R.id.tv_scoure).setText("原料："+data.getFood());
    }
}
