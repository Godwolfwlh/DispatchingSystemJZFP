package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.yhzhcs.dispatchingsystemjzfp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yhzhcs.dispatchingsystemjzfp.bean.Datas;

/**
 * Created by Administrator on 2018/1/15.
 */

public class MainListAdapter extends BaseAdapter {
    private Context context;

    private List<Datas> dataList;

    private LayoutInflater inflater;
    private BitmapUtils bitmapUtils;

    private final int TYPE_COUNT = 3;

    private final int TYPE_ONE = 0;

    private final int TYPE_TWO = 1;

    private final int TYPE_THREE = 2;

    private int currentType;

    public MainListAdapter(Context context, List<Datas> dataList) {
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
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        Datas datas = dataList.get(position);
        currentType = getItemViewType(position);
        //根据currentType来加载不同的布局,并复用convertview
        if (currentType == TYPE_TWO) {    //加载第一种布局
            ViewHolderHotInfo viewHolderHotInfo;
            //首先判断convertview==null
            if (convertView == null) {
                viewHolderHotInfo = new ViewHolderHotInfo();
                convertView = inflater.inflate(R.layout.activity_main_list_hot_policy, null);
                viewHolderHotInfo.HotInfoContent = (TextView) convertView.findViewById(R.id.hot_pol_content);
                viewHolderHotInfo.HotInfoButt = (TextView) convertView.findViewById(R.id.hot_pol_butt);
                viewHolderHotInfo.HotInfoPublisher = (TextView) convertView.findViewById(R.id.hot_pol_publisher);
                viewHolderHotInfo.HotInfoTime = (TextView) convertView.findViewById(R.id.hot_pol_time);
                convertView.setTag(viewHolderHotInfo);
            } else {
                viewHolderHotInfo = (ViewHolderHotInfo) convertView.getTag();
            }

            viewHolderHotInfo.HotInfoContent.setText(datas.getTitle());
            viewHolderHotInfo.HotInfoPublisher.setText(datas.getCreatedBy());

            Date dateTime = new Date(datas.getCreatedDate().getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String HotTime = dateFormat.format(dateTime);
            viewHolderHotInfo.HotInfoTime.setText(HotTime);

        } else if (currentType == TYPE_ONE) {  //加载第二种布局
            ViewHolderHotImg viewHolderHotImg;
            if (convertView == null) {
                viewHolderHotImg = new ViewHolderHotImg();
                convertView = inflater.inflate(R.layout.activity_main_list_hot_dynamic, null);
                viewHolderHotImg.HotImgContent = (TextView) convertView.findViewById(R.id.hot_dyn_content);
                viewHolderHotImg.HotImgButt = (TextView) convertView.findViewById(R.id.hot_dyn_butt);
                viewHolderHotImg.HotImgPublisher = (TextView) convertView.findViewById(R.id.hot_dyn_publisher);
                viewHolderHotImg.HotImgTime = (TextView) convertView.findViewById(R.id.hot_dyn_time);
                viewHolderHotImg.HotImgImage = (ImageView) convertView.findViewById(R.id.hot_dyn_image);
                convertView.setTag(viewHolderHotImg);
            } else {
                viewHolderHotImg = (ViewHolderHotImg) convertView.getTag();
            }
            viewHolderHotImg.HotImgContent.setText(datas.getTitle());
            viewHolderHotImg.HotImgPublisher.setText(datas.getCreatedBy());

            bitmapUtils = new BitmapUtils(context);    //创建BitmapUtils对象，通过xUtils框架获取
            bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);   //设置图片清晰度
            bitmapUtils.display(viewHolderHotImg.HotImgImage, datas.getImgPath());

            Date dateTime = new Date(datas.getCreatedDate().getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String HotTime = dateFormat.format(dateTime);
            viewHolderHotImg.HotImgTime.setText(HotTime);

        } else if (currentType == TYPE_THREE) {  //加载第三种布局

            ViewHolderHotPro viewHolderHotPro;
            //首先判断convertview==null
            if (convertView == null) {
                viewHolderHotPro = new ViewHolderHotPro();
                convertView = inflater.inflate(R.layout.activity_main_list_hot_produce, null);
                viewHolderHotPro.HotProContent = (TextView) convertView.findViewById(R.id.hot_pro_content);
                viewHolderHotPro.HotProButt = (TextView) convertView.findViewById(R.id.hot_pro_butt);
                viewHolderHotPro.HotProPublisher = (TextView) convertView.findViewById(R.id.hot_pro_publisher);
                viewHolderHotPro.HotProTime = (TextView) convertView.findViewById(R.id.hot_pro_time);
                viewHolderHotPro.HotProImage = (ImageView) convertView.findViewById(R.id.hot_pro_image);
                convertView.setTag(viewHolderHotPro);
            } else {
                viewHolderHotPro = (ViewHolderHotPro) convertView.getTag();
            }

            viewHolderHotPro.HotProContent.setText(datas.getTitle());
            viewHolderHotPro.HotProPublisher.setText(datas.getCreatedBy());

            bitmapUtils = new BitmapUtils(context);    //创建BitmapUtils对象，通过xUtils框架获取
            bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);   //设置图片清晰度
            bitmapUtils.display(viewHolderHotPro.HotProImage, datas.getImgPath());

            Date dateTime = new Date(datas.getCreatedDate().getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String HotTime = dateFormat.format(dateTime);
            viewHolderHotPro.HotProTime.setText(HotTime);
        }
        return convertView;
    }

    public static abstract class MyClickListener implements View.OnClickListener {
        /**
         * 基类的onClick方法
         */
        @Override
        public void onClick(View v) {
            myOnClick((Integer) v.getTag(), v);
        }

        public abstract void myOnClick(int position, View v);
    }

    public class ViewHolderHotInfo {

        TextView HotInfoContent;
        TextView HotInfoButt;
        TextView HotInfoPublisher;
        TextView HotInfoTime;
    }

    public class ViewHolderHotImg {

        TextView HotImgContent;
        TextView HotImgButt;
        TextView HotImgPublisher;
        TextView HotImgTime;
        ImageView HotImgImage;
    }

    public class ViewHolderHotPro {
        TextView HotProContent;
        TextView HotProButt;
        TextView HotProPublisher;
        TextView HotProTime;
        ImageView HotProImage;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("debug", "position=" + position);
        Datas datas = dataList.get(position);
        Log.i("ddd", "result=" + datas);
        int type = Integer.parseInt(datas.getMassageType());
        switch (type) {
            case TYPE_ONE:
                return TYPE_ONE;

            case TYPE_TWO:
                return TYPE_TWO;

            default:
                return -1;
        }
    }

}
