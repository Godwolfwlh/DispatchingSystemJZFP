package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Dtlist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class DynamicAdapter extends BaseAdapter {
    private Context context;

    private List<Dtlist> dataList;

    private LayoutInflater inflater;
    private BitmapUtils bitmapUtils;

    public DynamicAdapter(Context context, List<Dtlist> dataList) {
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

        Dtlist dtlist = dataList.get(position);
        //根据currentType来加载不同的布局,并复用convertview
        ViewHolderHotDyn viewHolderHotDyn;
        //首先判断convertview==null
        if (convertView == null) {
            viewHolderHotDyn = new ViewHolderHotDyn();
            convertView = inflater.inflate(R.layout.activity_main_list_hot_dynamic, null);
            viewHolderHotDyn.HotInfoContent = (TextView) convertView.findViewById(R.id.hot_dyn_content);
            viewHolderHotDyn.HotInfoButt = (TextView) convertView.findViewById(R.id.hot_dyn_butt);
            viewHolderHotDyn.HotInfoPublisher = (TextView) convertView.findViewById(R.id.hot_dyn_publisher);
            viewHolderHotDyn.HotInfoTime = (TextView) convertView.findViewById(R.id.hot_dyn_time);
            viewHolderHotDyn.HotInfoImage = (ImageView) convertView.findViewById(R.id.hot_dyn_image);
            convertView.setTag(viewHolderHotDyn);
        } else {
            viewHolderHotDyn = (ViewHolderHotDyn) convertView.getTag();
        }

        viewHolderHotDyn.HotInfoContent.setText(dtlist.getTitle());
        viewHolderHotDyn.HotInfoPublisher.setText(dtlist.getCreatedBy());

        bitmapUtils = new BitmapUtils(context);    //创建BitmapUtils对象，通过xUtils框架获取
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);   //设置图片清晰度
        bitmapUtils.display(viewHolderHotDyn.HotInfoImage, dtlist.getImgPath());

        Date dateTime = new Date(dtlist.getCreatedDate().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String HotTime = dateFormat.format(dateTime);
        viewHolderHotDyn.HotInfoTime.setText(HotTime);

        return convertView;
    }

    public class ViewHolderHotDyn {

        TextView HotInfoContent;
        TextView HotInfoButt;
        TextView HotInfoPublisher;
        TextView HotInfoTime;
        ImageView HotInfoImage;
    }

}
