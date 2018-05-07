package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.adapters.MyChildsSpinnerAdapter;
import com.yhzhcs.dispatchingsystemjzfp.adapters.MyTownSpinnerAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.Childs;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorAddBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorDetailsBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.TownBean;
import com.yhzhcs.dispatchingsystemjzfp.fragments.DetailsFragment;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ModifyPoorDetailsO extends AppCompatActivity implements View.OnClickListener {

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    private EditText poorName, poorNum, poorOne, poorTow, poorPho, poorAdd;//poorTown,poorChild;
    private Spinner childsSpi, townSpi;
    private String PorName, PorNum, PorOne, PorTow, PorPho, PorAdd, StrChilds, StrTown, TownId, ChildsId;

    PoorDetailsBean poorDetailsBean;
    List<PoorAddBean> poorAddBeans;
    private MyTownSpinnerAdapter townSpinnerAdapter;
    private MyChildsSpinnerAdapter childsSpinnerAdapter;

    private List<TownBean> townBeans;
    List<Childs> childs;
    private Intent intent = null;
    private String poorHouseId = null;
    private String poorCardNumber = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_poor_details);
        Bundle bundle = getIntent().getExtras();
        poorHouseId = bundle.getString("poorHouseId");
        poorCardNumber = bundle.getString("poorCardNumber");
        poorDetailsBean = bundle.getParcelable("POOR_LIST_BUNDLE");
        LogUtil.v("BDUSERINFO", poorDetailsBean.getPoor().toString());
        getPoorAdd();
    }

    private void intView(List<PoorAddBean> data) {
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
//        poorTown = (EditText) findViewById(R.id.mod_poor_details_town);
//        poorChild = (EditText) findViewById(R.id.mod_poor_details_childs);
        townSpi = (Spinner) findViewById(R.id.mod_poor_details_spinner_left);
        childsSpi = (Spinner) findViewById(R.id.mod_poor_details_spinner_right);

        poorName.setText(poorDetailsBean.getPoor().getName());
        poorNum.setText(poorDetailsBean.getPoor().getFamilyNumber() + "人");
        poorOne.setText(poorDetailsBean.getPoor().getMainPoorCause());
        poorTow.setText(poorDetailsBean.getPoor().getPoorProperty());
        poorPho.setText(poorDetailsBean.getPoor().getPhone());
        poorAdd.setText(poorDetailsBean.getPoor().getCounty());
//        poorTown.setText(poorDetailsBean.getPoor().getTown());
//        poorChild.setText(poorDetailsBean.getPoor().getVillage());
        LogUtil.v("djsfoiasafds", (data.get(0).getData().size()) + "");

        townBeans = new ArrayList<>();
        for (int i = 0; i < data.get(0).getData().size(); i++) {
            townBeans.add(data.get(0).getData().get(i));
            LogUtil.v("djsfoiasafds", townBeans.toString());
        }

        townSpinnerAdapter = new MyTownSpinnerAdapter(this);

        townSpinnerAdapter.setDatas(townBeans);
        townSpi.setAdapter(townSpinnerAdapter);
        childsSpinnerAdapter = new MyChildsSpinnerAdapter(this);
        townSpi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                StrTown = townBeans.get(i).getValue().toString();
                TownId = townBeans.get(i).getId().toString();
                LogUtil.logJson("StrTown", "选择的元素是：" + "\n城镇：" + StrTown);
                childs = new ArrayList<>();
                for (int j = 0; j < townBeans.get(i).getChilds().size(); j++) {
                    childs.add(townBeans.get(i).getChilds().get(j));
                    LogUtil.v("djsfoiasafds", childs.toString());
                    childsSpinnerAdapter.setDatas(childs);
                    childsSpi.setAdapter(childsSpinnerAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        childsSpi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                StrChilds = childs.get(i).getValue().toString();
                ChildsId = childs.get(i).getId().toString();
                LogUtil.logJson("StrTown", "选择的元素是：" + "\n街道（村）：" + StrChilds);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                intent = new Intent(ModifyPoorDetailsO.this, PoorDetailsActivity.class);
                intent.putExtra("FRAGMENT_ID", 0);
                intent.putExtra("poorHouseId", poorHouseId);
                intent.putExtra("poorCardNumber", poorCardNumber);
                startActivity(intent);
                finish();
                break;
            case R.id.title_right:
                updatePoor();
                break;
        }
    }

    private void updatePoor() {
        PorName = poorName.getText().toString().trim();
        PorNum = poorNum.getText().toString().trim();
        PorOne = poorOne.getText().toString().trim();
        PorTow = poorTow.getText().toString().trim();
        PorPho = poorPho.getText().toString().trim();
        PorAdd = poorAdd.getText().toString().trim();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("cradNumber", poorDetailsBean.getPoor().getCradNumber());
        params.addBodyParameter("name", PorName);
        params.addBodyParameter("mainPoorCause", PorOne);
        params.addBodyParameter("poorProperty", PorTow);
        params.addBodyParameter("phone", PorPho);
        params.addBodyParameter("city", poorDetailsBean.getPoor().getCity());
        params.addBodyParameter("county", PorAdd);
        params.addBodyParameter("town", StrTown);
        params.addBodyParameter("townId", TownId);
        params.addBodyParameter("village", StrChilds);
        params.addBodyParameter("villageId", ChildsId);
        httpUtils.send(HttpMethod.POST, Constant.URL_MODIFY_POOR, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("MODIFYPOORDETAILSOHTTP", "onSuccess：" + responseInfo.result.toString());
                intent = new Intent(ModifyPoorDetailsO.this, PoorDetailsActivity.class);
                intent.putExtra("FRAGMENT_ID", 0);
                intent.putExtra("poorHouseId", poorHouseId);
                intent.putExtra("poorCardNumber", poorCardNumber);
                LogUtil.v("poorHouseIdsssssss", poorHouseId);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("MODIFYPOORDETAILSOHTTP", "onFailure：" + s.toString());
            }
        });
    }

    private void getPoorAdd() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("areaPId", "0101000000");
        httpUtils.send(HttpMethod.GET, Constant.URL_POOR_ADD, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String body = responseInfo.result;
                Gson gson = new Gson();
                poorAddBeans = new ArrayList<PoorAddBean>();
                Type listType = new TypeToken<List<PoorAddBean>>() {
                }.getType();
                poorAddBeans = gson.fromJson(body, listType);
                intView(poorAddBeans);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("MODIFYPOORDETAILSOHTTP", "onFailure：" + s.toString());
            }
        });
    }
}
