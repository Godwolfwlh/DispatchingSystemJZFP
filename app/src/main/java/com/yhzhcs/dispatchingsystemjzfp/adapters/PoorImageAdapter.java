package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Inglists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class PoorImageAdapter extends BaseAdapter {
    private Context context;

    private List<Inglists> listBean;
    private BitmapUtils bitmapUtils;

    public PoorImageAdapter(List<Inglists> listBean, Context context) {
        super();
        this.context = context;
        this.listBean = listBean;


//        for (int i = 0; i < images.length; i++) {
//            Picture picture = new Picture(images[i]);
//            pictures.add(picture);
//        }

    }

    @Override
    public int getCount() {

        if (null != listBean) {
            return listBean.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {

        return listBean.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        Inglists inglists = listBean.get(position);

        if (convertView == null) {

            viewHolder = new ViewHolder();
            // 获得容器
            convertView = LayoutInflater.from(this.context).inflate(R.layout.grid_view_item, null);

            // 初始化组件
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image_grid_item);
            // 给converHolder附加一个对象
            convertView.setTag(viewHolder);
        } else {
            // 取得converHolder附加的对象
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 给组件设置资源
        bitmapUtils = new BitmapUtils(context);    //创建BitmapUtils对象，通过xUtils框架获取
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);   //设置图片清晰度
        bitmapUtils.display(viewHolder.image, inglists.getAnnexPath());

        return convertView;
    }

    class ViewHolder {
        public ImageView image;
    }

    class Picture {

        private int imageId;

        public Picture(Integer imageId) {
            this.imageId = imageId;
        }

        public int getImageId() {
            return imageId;
        }

    }
}
