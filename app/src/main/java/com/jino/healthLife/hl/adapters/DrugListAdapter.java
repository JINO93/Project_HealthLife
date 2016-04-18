package com.jino.healthLife.hl.adapters;

import android.content.Context;
import android.net.Uri;

import com.jino.healthLife.R;
import com.jino.healthLife.hl.Constane.Constant;
import com.jino.healthLife.hl.models.DrugInfo;
import com.jino.healthLife.hl.models.FoodInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class DrugListAdapter extends BaseAdapter<DrugInfo,BaseViewHolder> {


    public DrugListAdapter(Context context, List<DrugInfo> t) {
        super(context, t, R.layout.item_drug_list);
    }


    @Override
    protected void bindData(BaseViewHolder holder, DrugInfo data) {

        if(data==null){
            return;
        }

        String url= Constant.IMG_BASE_URL +data.getImage();
        holder.getSimpleDraweeView(R.id.iv_preview).setImageURI(Uri.parse(url));
        holder.getTextView(R.id.tv_price).setText("价格："+data.getPrice());
        holder.getTextView(R.id.tv_count).setText(data.getCount() + "");
        holder.getTextView(R.id.tv_itemTitle).setText(data.getName());
        holder.getTextView(R.id.tv_factory).setText("制造商："+data.getFactory());
    }
}
