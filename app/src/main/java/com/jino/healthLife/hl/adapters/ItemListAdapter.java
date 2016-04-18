package com.jino.healthLife.hl.adapters;

import android.content.Context;
import android.net.Uri;

import java.util.List;

import com.jino.healthLife.R;
import com.jino.healthLife.hl.Constane.Constant;
import com.jino.healthLife.hl.models.ItemInfo;

/**
 * Created by Administrator on 2016/4/7.
 */
public class ItemListAdapter extends BaseAdapter<ItemInfo,BaseViewHolder> {


    public ItemListAdapter(Context context, List<ItemInfo> t) {
        super(context, t, R.layout.item_news_list);
    }

    @Override
    protected void bindData(BaseViewHolder holder, ItemInfo data) {
        String url= Constant.IMG_BASE_URL +data.getImg();
        holder.getSimpleDraweeView(R.id.iv_preview).setImageURI(Uri.parse(url));
        holder.getTextView(R.id.tv_itemDate).setText(data.getTime());
        holder.getTextView(R.id.tv_ViewCount).setText(data.getCount() + "");
        holder.getTextView(R.id.tv_itemTitle).setText(data.getTitle());

    }
}
