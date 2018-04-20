package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Poorhouses;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class PoorListAdapter extends BaseAdapter {

    private SharedPreferences sp;
    private String missionId;
    private Context context;
    private String poorIds;
    private List<Poorhouses> listPoor;
    LayoutInflater inflater;

    private BitmapUtils bitmapUtils;

    public PoorListAdapter(Context context, List<Poorhouses> listPoor) {
        this.context = context;
        this.listPoor = listPoor;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<Poorhouses> listPoor) {
        this.listPoor = listPoor;
    }

    public void clear() {
        listPoor.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listPoor.size();
    }

    @Override
    public Object getItem(int position) {
        return listPoor.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Poorhouses poorBean = listPoor.get(position);
        poorIds = poorBean.getId();
        sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        missionId = sp.getString("MISSION_ID", "");
        ViewHolderPoor holderPoor;
        if (convertView == null){
            holderPoor = new ViewHolderPoor();
            convertView = inflater.inflate(R.layout.poor_list_item,null);
            holderPoor.PoorName = (TextView) convertView.findViewById(R.id.poor_item_name);
            holderPoor.PoorDelete = (TextView) convertView.findViewById(R.id.poor_item_delete);
            holderPoor.PoorImg = (ImageView) convertView.findViewById(R.id.poor_item_img);
            holderPoor.PoorIconFamily = (ImageView) convertView.findViewById(R.id.poor_item_icon_family);
            holderPoor.PoorFamilyNum = (TextView) convertView.findViewById(R.id.poor_item_family_num);
            holderPoor.PoorFamilyTxt = (TextView) convertView.findViewById(R.id.poor_item_family_txt);
            holderPoor.PoorIconIncome = (ImageView) convertView.findViewById(R.id.poor_item_icon_income);
            holderPoor.PoorIncomeNum = (TextView) convertView.findViewById(R.id.poor_item_income_num);
            holderPoor.PoorIncomeTxt = (TextView) convertView.findViewById(R.id.poor_item_income_txt);
            holderPoor.PoorIconAdderss = (ImageView) convertView.findViewById(R.id.poor_item_icon_address);
            holderPoor.PoorAdderssTxt = (TextView) convertView.findViewById(R.id.poor_item_adderss_txt);
            holderPoor.PoorIconThing = (ImageView) convertView.findViewById(R.id.poor_item_icon_thing);
            holderPoor.PoorThingTxt = (TextView) convertView.findViewById(R.id.poor_item_thing_txt);
            convertView.setTag(holderPoor);
        }else {
            holderPoor = (ViewHolderPoor) convertView.getTag();
        }
        holderPoor.PoorName.setText(poorBean.getName());
        holderPoor.PoorDelete.setText(R.string.poor_list_item_delete);
        holderPoor.PoorImg.setImageResource(R.mipmap.img_new);
        holderPoor.PoorIconFamily.setImageResource(R.mipmap.icon_family);
        holderPoor.PoorFamilyNum.setText(poorBean.getFamilyNumber()+"");
        holderPoor.PoorFamilyTxt.setText(R.string.poor_list_item_family_txt);
        holderPoor.PoorIconIncome.setImageResource(R.mipmap.icon_income);
        holderPoor.PoorIncomeNum.setText(poorBean.getAverageIncome()+"");
        holderPoor.PoorIncomeTxt.setText(R.string.poor_list_item_income_txt);
        holderPoor.PoorIconAdderss.setImageResource(R.mipmap.icon_address);
        holderPoor.PoorAdderssTxt.setText(poorBean.getCounty()+poorBean.getTown()+poorBean.getVillage());
        holderPoor.PoorIconThing.setImageResource(R.mipmap.icon_poor_thing);
        holderPoor.PoorThingTxt.setText(poorBean.getMainPoorCause());

        bitmapUtils = new BitmapUtils(context);    //创建BitmapUtils对象，通过xUtils框架获取
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);   //设置图片清晰度
        bitmapUtils.display(holderPoor.PoorImg, poorBean.getPhoto());

        holderPoor.PoorDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return convertView;
    }

    public class ViewHolderPoor {
        TextView PoorName;
        TextView PoorDelete;
        ImageView PoorImg;
        ImageView PoorIconFamily;
        TextView PoorFamilyNum;
        TextView PoorFamilyTxt;
        ImageView PoorIconIncome;
        TextView PoorIncomeNum;
        TextView PoorIncomeTxt;
        ImageView PoorIconAdderss;
        TextView PoorAdderssTxt;
        ImageView PoorIconThing;
        TextView PoorThingTxt;
    }

    private void deleteData(){
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("missionId",missionId);
        params.addBodyParameter("poorIds",poorIds);
        httpUtils.send(HttpMethod.POST, Constant.URL_REMOVE_POOR, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

}
