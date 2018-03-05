package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Ncplist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class ProduceAdapter extends BaseAdapter {
    private Context context;

    private List<Ncplist> dataList;

    private LayoutInflater inflater;

    public ProduceAdapter(Context context, List<Ncplist> dataList) {
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

        Ncplist ncplist = dataList.get(position);
        //根据currentType来加载不同的布局,并复用convertview
        ViewHolderHotPro viewHolderHotPro;
        //首先判断convertview==null
        if (convertView == null) {
            viewHolderHotPro = new ViewHolderHotPro();
            convertView = inflater.inflate(R.layout.activity_main_list_hot_produce, null);
            viewHolderHotPro.HotInfoContent = (TextView) convertView.findViewById(R.id.hot_pro_content);
            viewHolderHotPro.HotInfoButt = (TextView) convertView.findViewById(R.id.hot_pro_butt);
            viewHolderHotPro.HotInfoPublisher = (TextView) convertView.findViewById(R.id.hot_pro_publisher);
            viewHolderHotPro.HotInfoTime = (TextView) convertView.findViewById(R.id.hot_pro_time);
            viewHolderHotPro.HotInfoImage = (ImageView) convertView.findViewById(R.id.hot_pro_image);
            convertView.setTag(viewHolderHotPro);
        } else {
            viewHolderHotPro = (ViewHolderHotPro) convertView.getTag();
        }

        viewHolderHotPro.HotInfoContent.setText(ncplist.getTitle());
        viewHolderHotPro.HotInfoPublisher.setText(ncplist.getCreatedBy());
        Date dateTime = new Date(ncplist.getCreatedDate().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String HotTime = dateFormat.format(dateTime);
        viewHolderHotPro.HotInfoTime.setText(HotTime);

        return convertView;
    }

    public class ViewHolderHotPro {

        TextView HotInfoContent;
        TextView HotInfoButt;
        TextView HotInfoPublisher;
        TextView HotInfoTime;
        ImageView HotInfoImage;
    }

}
