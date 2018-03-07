package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.LSpinnerBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class PoorLSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<LSpinnerBean> lSpinnerBeanList;
    private LayoutInflater inflater;

    public PoorLSpinnerAdapter (Context context){

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDatas(List<LSpinnerBean> lSpinnerBeanList) {
        this.lSpinnerBeanList = lSpinnerBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lSpinnerBeanList==null?0:lSpinnerBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return lSpinnerBeanList==null?null:lSpinnerBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LSpinnerBean lSpinnerBean = lSpinnerBeanList.get(position);
        ViewHolderLSpinner viewHolderLSpinner;
        if (convertView == null){
            viewHolderLSpinner = new ViewHolderLSpinner();
            convertView = inflater.inflate(R.layout.poor_lspinner_item,null);
            viewHolderLSpinner.LSpinnerImg = (ImageView) convertView.findViewById(R.id.lspinner_item_img);
            viewHolderLSpinner.LSpinnerTxt = (TextView) convertView.findViewById(R.id.lspinner_item_txt);
            convertView.setTag(viewHolderLSpinner);
        }else {
            viewHolderLSpinner = (ViewHolderLSpinner) convertView.getTag();
        }
        viewHolderLSpinner.LSpinnerTxt.setText(lSpinnerBean.getTimes());
        return convertView;
    }

    public class ViewHolderLSpinner{
        private ImageView LSpinnerImg;
        private TextView LSpinnerTxt;
    }
}
