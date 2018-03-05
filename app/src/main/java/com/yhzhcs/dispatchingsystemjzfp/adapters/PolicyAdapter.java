package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Zclist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class PolicyAdapter extends BaseAdapter {
    private Context context;

    private List<Zclist> dataList;

    private LayoutInflater inflater;

    public PolicyAdapter(Context context, List<Zclist> dataList) {
        this.context = context;
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        Zclist zclist = dataList.get(position);
        //根据currentType来加载不同的布局,并复用convertview
        ViewHolderHotPoli viewHolderHotPoli;
        //首先判断convertview==null
        if (convertView == null) {
            viewHolderHotPoli = new ViewHolderHotPoli();
            convertView = inflater.inflate(R.layout.activity_main_list_hot_policy, null);
            viewHolderHotPoli.HotInfoContent = (TextView) convertView.findViewById(R.id.hot_pol_content);
            viewHolderHotPoli.HotInfoButt = (TextView) convertView.findViewById(R.id.hot_pol_butt);
            viewHolderHotPoli.HotInfoPublisher = (TextView) convertView.findViewById(R.id.hot_pol_publisher);
            viewHolderHotPoli.HotInfoTime = (TextView) convertView.findViewById(R.id.hot_pol_time);
            convertView.setTag(viewHolderHotPoli);
        } else {
            viewHolderHotPoli = (ViewHolderHotPoli) convertView.getTag();
        }

        viewHolderHotPoli.HotInfoContent.setText(zclist.getTitle());
        viewHolderHotPoli.HotInfoPublisher.setText(zclist.getCreatedBy());

        Date dateTime = new Date(zclist.getCreatedDate().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String HotTime = dateFormat.format(dateTime);
        viewHolderHotPoli.HotInfoTime.setText(HotTime);

        return convertView;
    }

    public class ViewHolderHotPoli {

        TextView HotInfoContent;
        TextView HotInfoButt;
        TextView HotInfoPublisher;
        TextView HotInfoTime;
    }

}
