package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Childs;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorAddBean;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8 0008.
 */

public class MyChildsSpinnerAdapter extends BaseAdapter {
    private List<Childs> childs;
    private Context context;
    private LayoutInflater inflater;

    public MyChildsSpinnerAdapter (Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDatas(List<Childs> childs) {
        this.childs = childs;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return childs==null?0:childs.size();
    }

    @Override
    public Object getItem(int position) {
        return childs==null?null:childs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Childs childss = childs.get(position);
        ViewHolderSpinner viewHolderSpinner;
        if (convertView == null){
            viewHolderSpinner = new ViewHolderSpinner();
            convertView = inflater.inflate(R.layout.spinner_item,null);
            viewHolderSpinner.SpinnerTxt = (TextView) convertView.findViewById(R.id.spinner_item_text);
            convertView.setTag(viewHolderSpinner);
        }else {
            viewHolderSpinner = (ViewHolderSpinner) convertView.getTag();
        }
        viewHolderSpinner.SpinnerTxt.setText(childss.getValue());
        return convertView;
    }

    public class ViewHolderSpinner{
        private TextView SpinnerTxt;
    }
}
