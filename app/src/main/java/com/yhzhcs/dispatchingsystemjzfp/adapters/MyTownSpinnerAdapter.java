package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorAddBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.TownBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8 0008.
 */

public class MyTownSpinnerAdapter extends BaseAdapter {
    private List<TownBean> townBeans;
    private Context context;
    private LayoutInflater inflater;

    public MyTownSpinnerAdapter (Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDatas(List<TownBean> poorAddBeans) {
        this.townBeans = poorAddBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return townBeans==null?0:townBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return townBeans==null?null:townBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TownBean townBean = townBeans.get(position);
        ViewHolderSpinner viewHolderSpinner;
        if (convertView == null){
            viewHolderSpinner = new ViewHolderSpinner();
            convertView = inflater.inflate(R.layout.spinner_item,null);
            viewHolderSpinner.SpinnerTxt = (TextView) convertView.findViewById(R.id.spinner_item_text);
            convertView.setTag(viewHolderSpinner);
        }else {
            viewHolderSpinner = (ViewHolderSpinner) convertView.getTag();
        }
        viewHolderSpinner.SpinnerTxt.setText(townBean.getValue());
        return convertView;
    }

    public class ViewHolderSpinner{
        private TextView SpinnerTxt;
    }
}
