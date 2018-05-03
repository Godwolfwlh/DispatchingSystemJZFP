package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Poorlist;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.zhy.android.percent.support.PercentLinearLayout;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class ModifyPoorFamily extends AppCompatActivity implements View.OnClickListener{

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    private Poorlist poorlist;

    private EditText editName,editSex,editWithNelation,editHealth;
//    private EditText editAge;
    private String strName,strSex,strWithNelation,strHealth;
//    private String strAge;
    private PercentLinearLayout withPercentLinear;
    private String poorHouseId = null;
    private Intent intent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_poor_family);
        Bundle bundle = getIntent().getExtras();
        poorlist = bundle.getParcelable("POOR_LIST");
        poorHouseId = bundle.getString("poorHouseId");
        LogUtil.v("POOR_LIST",poorlist.toString());
        intView();
    }

    private void intView(){
        titleImgL = (ImageView) findViewById(R.id.title_left);
        titleName = (TextView) findViewById(R.id.title_name);
        titleImgR = (ImageView) findViewById(R.id.title_right);

        titleImgL.setVisibility(View.VISIBLE);
        titleImgL.setImageResource(R.mipmap.icon_back);
        titleName.setText("家庭成员");
        titleImgR.setImageResource(R.mipmap.icon_sure_1);
        titleImgL.setOnClickListener(this);
        titleImgR.setOnClickListener(this);

        editName = (EditText) findViewById(R.id.mod_poor_family_name);
        editSex = (EditText) findViewById(R.id.mod_poor_family_sex);
//        editAge = (EditText) findViewById(R.id.mod_poor_family_age);
        editWithNelation = (EditText) findViewById(R.id.mod_poor_family_withnelation);
        editHealth = (EditText) findViewById(R.id.mod_poor_family_health);
        withPercentLinear = (PercentLinearLayout) findViewById(R.id.mod_poor_family_withnelation_linear);


        editName.setText(poorlist.getName());
        editSex.setText(poorlist.getSex());
//        editAge.setText(poorlist.getAge()+"");
        editHealth.setText(poorlist.getHealth());
        if (poorlist.getWithNelation().equals("户主")){
            withPercentLinear.setVisibility(View.GONE);
        }else {
            withPercentLinear.setVisibility(View.VISIBLE);
            editWithNelation.setText(poorlist.getWithNelation());
        }
    }

    private void updateFamily(){
        strName = editName.getText().toString().trim();
        strSex = editSex.getText().toString().trim();
//        strAge = editAge.getText().toString().trim();
        strWithNelation = poorlist.getWithNelation();
        strHealth = editHealth.getText().toString().trim();

        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("cradNumber",poorlist.getCradNumber());
        params.addBodyParameter("name",strName);
        params.addBodyParameter("sex",strSex);
//        params.addBodyParameter("age",strAge);
        params.addBodyParameter("withNelation",strWithNelation);
        params.addBodyParameter("health",strHealth);
        httpUtils.send(HttpMethod.POST, Constant.URL_MODIFY_FAMILY, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("MODIFY_FAMILY_HTTP","onSuccess：" + responseInfo.result.toString());
                intent = new Intent(ModifyPoorFamily.this,PoorDetailsActivity.class);
                intent.putExtra("FRAGMENT_ID",1);
                intent.putExtra("poorHouseId",poorHouseId);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("MODIFY_FAMILY_HTTP","onFailure：" + s);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                intent = new Intent(ModifyPoorFamily.this,PoorDetailsActivity.class);
                intent.putExtra("FRAGMENT_ID",1);
                intent.putExtra("poorHouseId",poorHouseId);
                startActivity(intent);
                finish();
                break;
            case R.id.title_right:
                updateFamily();
                break;
        }
    }
}
