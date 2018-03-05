package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.model.Moment;
import com.yhzhcs.dispatchingsystemjzfp.utils.CommentFun;
import com.yhzhcs.dispatchingsystemjzfp.utils.CustomTagHandler;

import java.util.ArrayList;

/**
 * Created by on 2018-2-128.
 */
public class MomentAdapter extends BaseAdapter implements View.OnClickListener {

    public static final int VIEW_HEADER = 0;
    public static final int VIEW_MOMENT = 1;


    private ArrayList<Moment> mList;
    private Context mContext;
    private CustomTagHandler mTagHandler;
    HeaderViewHolder headerViewHolder = new HeaderViewHolder();

    public MomentAdapter(Context context, ArrayList<Moment> list, CustomTagHandler tagHandler) {
        mList = list;
        mContext = context;
        mTagHandler = tagHandler;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_HEADER : VIEW_MOMENT;
    }

    @Override
    public int getCount() {
        // heanderView
        return mList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (position == 0) {
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.item_header, null);
                    headerViewHolder.UpToData = (TextView) convertView.findViewById(R.id.up_to_date);
                    headerViewHolder.UpToDataIndex = convertView.findViewById(R.id.up_to_date_index);
                    headerViewHolder.HotSpot = (TextView) convertView.findViewById(R.id.hot_spot);
                    headerViewHolder.HotSpotIndex = convertView.findViewById(R.id.hot_spot_index);
                    convertView.setTag(headerViewHolder);
                } else {
                    headerViewHolder = (HeaderViewHolder) convertView.getTag();
                }
                headerViewHolder.UpToData.setOnClickListener(this);
                headerViewHolder.HotSpot.setOnClickListener(this);

            } else {
                convertView = View.inflate(mContext, R.layout.item_moment, null);
                ViewHolder holder = new ViewHolder();
                holder.mCommentList = (LinearLayout) convertView.findViewById(R.id.comment_list);
                holder.mBtnInput = convertView.findViewById(R.id.btn_input_comment);
                holder.mContent = (TextView) convertView.findViewById(R.id.content);
                convertView.setTag(holder);
            }
        }
        //防止ListView的OnItemClick与item里面子view的点击发生冲突
        ((ViewGroup) convertView).setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        if (position == 0) {

        } else {
            int index = position - 1;
            ViewHolder holder = (ViewHolder) convertView.getTag();
//            holder.mContent.setText(mList.get(index).mContent);
            CommentFun.parseCommentList(mContext, mList.get(index).mComment,
                    holder.mCommentList, holder.mBtnInput, mTagHandler);
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.up_to_date:
                headerViewHolder.HotSpotIndex.setVisibility(View.GONE);
                headerViewHolder.UpToDataIndex.setVisibility(View.VISIBLE);
                break;
            case R.id.hot_spot:
                headerViewHolder.UpToDataIndex.setVisibility(View.GONE);
                headerViewHolder.HotSpotIndex.setVisibility(View.VISIBLE);
                break;
        }
    }

    private static class ViewHolder {
        LinearLayout mCommentList;
        View mBtnInput;
        TextView mContent;
    }

    private static class HeaderViewHolder{
        TextView UpToData;
        TextView HotSpot;
        View UpToDataIndex;
        View HotSpotIndex;
    }
}
