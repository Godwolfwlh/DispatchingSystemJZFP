package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.RSpinnerBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class PoorRSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<RSpinnerBean> rSpinnerBeanList;
    private LayoutInflater inflater;

    public PoorRSpinnerAdapter (Context context){

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDatas(List<RSpinnerBean> rSpinnerBeanList) {
        this.rSpinnerBeanList = rSpinnerBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rSpinnerBeanList==null?0:rSpinnerBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return rSpinnerBeanList==null?null:rSpinnerBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RSpinnerBean rSpinnerBean = rSpinnerBeanList.get(position);
        ViewHolderRSpinner viewHolderRSpinner;
        if (convertView == null){
            viewHolderRSpinner = new ViewHolderRSpinner();
            convertView = inflater.inflate(R.layout.poor_rspinner_item,null);
            viewHolderRSpinner.RSpinnerImg = (ImageView) convertView.findViewById(R.id.rspinner_item_img);
            viewHolderRSpinner.RSpinnerPlans = (TextView) convertView.findViewById(R.id.rspinner_item_plan);
            convertView.setTag(viewHolderRSpinner);
        }else {
            viewHolderRSpinner = (ViewHolderRSpinner) convertView.getTag();
        }
        viewHolderRSpinner.RSpinnerPlans.setText(rSpinnerBean.getPlans());
        return convertView;
    }

    public class ViewHolderRSpinner{
        private ImageView RSpinnerImg;
        private TextView RSpinnerPlans;
    }
}
