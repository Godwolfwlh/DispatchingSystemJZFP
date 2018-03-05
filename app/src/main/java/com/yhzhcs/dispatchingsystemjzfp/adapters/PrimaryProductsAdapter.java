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
import com.yhzhcs.dispatchingsystemjzfp.bean.Datas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */

public class PrimaryProductsAdapter extends BaseAdapter {
    private Context context;

    private List<Datas> dataList;

    private LayoutInflater inflater;
    private BitmapUtils bitmapUtils;

    public PrimaryProductsAdapter(Context context, List<Datas> dataList) {
        this.context = context;
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Datas datas = dataList.get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_primary_products_item, null);
            holder.ProName = (TextView) convertView.findViewById(R.id.primary_products_name);
            holder.ProImage = (ImageView) convertView.findViewById(R.id.primary_products_image);
            holder.ProPrice = (TextView) convertView.findViewById(R.id.primary_products_price);
            holder.ProFarmer = (TextView) convertView.findViewById(R.id.primary_products_farmer);
            holder.ProTime = (TextView) convertView.findViewById(R.id.primary_products_time);
            holder.ProPhone = (TextView) convertView.findViewById(R.id.primary_products_phone);
            holder.ProLabel = (TextView) convertView.findViewById(R.id.primary_products_label);
            holder.ProAdd = (TextView) convertView.findViewById(R.id.primary_products_adds);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ProPrice.setText(datas.getPrice()+"");

        bitmapUtils = new BitmapUtils(context);    //创建BitmapUtils对象，通过xUtils框架获取
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);   //设置图片清晰度
        bitmapUtils.display(holder.ProImage, datas.getImgPath());

        Date dateTime = new Date(datas.getCreatedDate().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ProTime = dateFormat.format(dateTime);
        holder.ProTime.setText(ProTime);

        return convertView;
    }

    public class ViewHolder{
        private TextView ProName,ProPrice,ProFarmer,ProTime,ProPhone,ProLabel,ProAdd;
        private ImageView ProImage;
    }
}
