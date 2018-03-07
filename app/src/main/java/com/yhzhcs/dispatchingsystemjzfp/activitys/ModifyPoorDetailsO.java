package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Poor;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorDetailsBean;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ModifyPoorDetailsO extends AppCompatActivity implements View.OnClickListener{

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    private EditText poorName,poorNum,poorOne,poorTow,poorPho,poorAdd;
    private String PorName,PorNum,PorOne,PorTow,PorPho,PorAdd;

    PoorDetailsBean poorDetailsBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_poor_details);
        Bundle bundle = getIntent().getExtras();
        poorDetailsBean = bundle.getParcelable("POOR_LIST_BUNDLE");
        LogUtil.v("BDUSERINFO",poorDetailsBean.getPoor().toString());
        intView();
    }

    private void intView(){
        titleImgL = (ImageView) findViewById(R.id.title_left);
        titleName = (TextView) findViewById(R.id.title_name);
        titleImgR = (ImageView) findViewById(R.id.title_right);

        titleImgL.setVisibility(View.VISIBLE);
        titleImgL.setImageResource(R.mipmap.icon_back);
        titleName.setText("基本情况");
        titleImgR.setImageResource(R.mipmap.icon_sure_1);
        titleImgL.setOnClickListener(this);
        titleImgR.setOnClickListener(this);

        poorName = (EditText) findViewById(R.id.mod_poor_details_name);
        poorNum = (EditText) findViewById(R.id.mod_poor_details_num);
        poorOne = (EditText) findViewById(R.id.mod_poor_details_one);
        poorTow = (EditText) findViewById(R.id.mod_poor_details_two);
        poorPho = (EditText) findViewById(R.id.mod_poor_details_pho);
        poorAdd = (EditText) findViewById(R.id.mod_poor_details_add);

        poorName.setText(poorDetailsBean.getPoor().getName());
        poorNum.setText(poorDetailsBean.getPoor().getFamilyNumber() + "人");
        poorOne.setText(poorDetailsBean.getPoor().getMainPoorCause());
        poorTow.setText(poorDetailsBean.getPoor().getPoorProperty());
        poorPho.setText(poorDetailsBean.getPoor().getPhone());
        poorAdd.setText(poorDetailsBean.getPoor().getCompanyName() + poorDetailsBean.getPoor().getCounty() + poorDetailsBean.getPoor().getTown() + poorDetailsBean.getPoor().getVillage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right:
                break;
        }
    }

    private void updatePoor(){
        PorName = poorName.getText().toString().trim();
        PorNum = poorNum.getText().toString().trim();
        PorOne = poorOne.getText().toString().trim();
        PorTow = poorTow.getText().toString().trim();
        PorPho = poorPho.getText().toString().trim();
        PorAdd = poorAdd.getText().toString().trim();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
    }
}
