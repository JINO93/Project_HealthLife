package com.jino.healthLife.hl.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jino.healthLife.R;
import com.jino.healthLife.hl.models.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/13.
 */
public class ExpandableMenu extends ToggleButton implements View.OnClickListener {
    private static final String tag = "ExpandableMenu";

    private Context context;
    private PopupWindow listMenu;
    private int windowHeight = 0;

    private Map<String, List<Category>> datas;
    private List<String> leftTitle;
    private LeftLlistAdapter leftListAdapter;
    private RightListAdapter rightListAdapter;
    private ListView leftList;
    private ListView rightList;
    private OnRightItemClickListener mListener;
    private SparseArray selectedItem;


    public ExpandableMenu(Context context) {
        this(context, null);
    }

    public ExpandableMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init();
    }

    private void init() {
        selectedItem = new SparseArray();
        datas = new HashMap<>();
        leftTitle = new ArrayList<>();
        windowHeight = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();

        setOnClickListener(this);
        LinearLayout layout = new LinearLayout(context);
        leftList = new ListView(context);
        rightList = new ListView(context);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.list_divider));
        leftList.setDivider(colorDrawable);
        leftList.setDividerHeight(1);
        rightList.setDivider(colorDrawable);
        rightList.setDividerHeight(1);

        layout.setOrientation(LinearLayout.HORIZONTAL);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;

//         final RightListAdapter rightListAdapter = new RightListAdapter(0);
        leftListAdapter = new LeftLlistAdapter();
        leftList.setAdapter(leftListAdapter);
        leftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v(tag, position + "");
                leftListAdapter.setSelected(position);
//                rightListAdapter.updateDatas(leftPosition);
                rightListAdapter = new RightListAdapter(position);
                rightList.setAdapter(rightListAdapter);
            }
        });
        rightList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.v(tag, rightListAdapter.rightDatas.get(leftPosition));
                selectedItem.clear();
                selectedItem.put(rightListAdapter.leftPosition, position);
                rightListAdapter.setSelected(position);
                ExpandableMenu.this.setText(rightListAdapter.rightDatas.get(position).getName());

                if (mListener != null) {

                    mListener.onClick(String.valueOf(rightListAdapter.rightDatas.get(position).getId()), position);
                }
                dismissMenu();
            }
        });


        layout.addView(leftList, params);
        layout.addView(rightList, params);
        listMenu = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, windowHeight / 2, true) {
            @Override
            public void dismiss() {
                super.dismiss();
                ExpandableMenu.this.setBackgroundResource(R.drawable.choosebar_down);
            }
        };
        listMenu.setBackgroundDrawable(new BitmapDrawable());

        Log.v(tag, "init");

    }


    public void dismissMenu() {
        if (listMenu.isShowing()) {
            listMenu.dismiss();
        }
    }

    public void setRightItemClickListener(OnRightItemClickListener mListener) {
        this.mListener = mListener;
    }

    public void setDatas(Map<String, List<Category>> datas) {
        this.datas = datas;

        leftTitle = new ArrayList<>(datas.keySet());
//        Log.v(tag, datas.toString());
//        Log.v(tag, leftTitle.toString());
        Log.v(tag, "setdata");
        leftList.performItemClick(leftList.getChildAt(0), 0, leftList.getItemIdAtPosition(0));
        rightList.performItemClick(rightList.getChildAt(0), 0, rightList.getItemIdAtPosition(0));
    }

    @Override
    public void onClick(View v) {
        ExpandableMenu.this.setBackgroundResource(R.drawable.choosebar_press_up);
        if (!listMenu.isShowing()) {

            Log.v("FFF", "FFF");
            listMenu.showAsDropDown(v);
        } else {

            listMenu.dismiss();
        }

    }


    class LeftLlistAdapter extends BaseAdapter {

        private int seclected = 0;

        public LeftLlistAdapter() {
        }

        @Override
        public int getCount() {
            return leftTitle.size();
        }

        @Override
        public Object getItem(int position) {
            return leftTitle.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.popupwindow_list_item, null);
            }

            TextView tv_item = (TextView) convertView.findViewById(R.id.id_item_tv);
            tv_item.setText(leftTitle.get(position));
            tv_item.setBackgroundResource(seclected == position ? android.R.color.darker_gray : android.R.color.white);
            return convertView;
        }


        public void setSelected(int position) {
            seclected = position;
            notifyDataSetChanged();
        }
    }


    class RightListAdapter extends BaseAdapter {

        private int leftPosition;
        private int selected = -1;
        public List<Category> rightDatas = null;


        public RightListAdapter(int mPosition) {

            this.leftPosition = mPosition;
            updateDatas(mPosition);
            Log.v(tag, rightDatas.toString());
        }


        public void updateDatas(int position) {

            rightDatas = datas.get(leftTitle.get(position));
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (rightDatas != null & rightDatas.size() > 0) {

                return rightDatas.size();
            } else
                return 0;
        }

        @Override
        public Object getItem(int position) {

            if (rightDatas.size() > 0) {

                return rightDatas.get(position).getName();
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.popupwindow_list_item, null);
            }

            TextView tv_item = (TextView) convertView.findViewById(R.id.id_item_tv);
            tv_item.setText(rightDatas.get(position).getName());
            if (selectedItem.get(leftPosition) != null)
                selected = (int) selectedItem.get(leftPosition);
            tv_item.setBackgroundResource(selected == position ? R.color.colorPrimary : android.R.color.darker_gray);
            return convertView;
        }

        public void setSelected(int selected) {
            this.selected = selected;
            notifyDataSetChanged();
        }
    }


    public interface OnRightItemClickListener {
        void onClick(String id, int position);
    }

}
