package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.adapters.PoorListAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorListBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.Poorhouses;
import com.yhzhcs.dispatchingsystemjzfp.onscrolls.PoorOnScerllListenner;
import com.yhzhcs.dispatchingsystemjzfp.utils.CommonShowView;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public class PoorActivity extends AppCompatActivity implements View.OnClickListener, PoorOnScerllListenner.OnloadDataListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    private ListView poorList;
    public Spinner poorLSpinner;
    private Spinner poorRSpinner;

    private List<Poorhouses> poorListBean;

    private PoorListAdapter poorListAdapter;

    //加载更多布局
    private View footer;

    private SharedPreferences sp;
    private String missionId;
    private int userId;

    private CommonShowView mShowView;

    private String querStrTime;
    private String querStrPoverty;

    private HttpUtils httpUtils;
    private RequestParams params;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poor);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        missionId = sp.getString("MISSION_ID", "");
        userId = sp.getInt("USER_ID", 0);
        LogUtil.v("cheshicheshi", "=========" + userId + "============" + missionId);
        initView();
    }

    private void initView() {
        titleImgL = (ImageView) findViewById(R.id.title_left);
        titleName = (TextView) findViewById(R.id.title_name);
        titleImgR = (ImageView) findViewById(R.id.title_right);
        poorLSpinner = (Spinner) findViewById(R.id.spinner_left);
        poorRSpinner = (Spinner) findViewById(R.id.spinner_right);

        poorList = (ListView) findViewById(R.id.poor_list);
        mShowView = new CommonShowView(this, poorList);
        mShowView.showByType(CommonShowView.TYPE_EMPTY);

        footer = LinearLayout.inflate(this, R.layout.foot_boot, null);
        footer.setVisibility(View.GONE);
        poorList.addFooterView(footer);

        titleImgL.setVisibility(View.VISIBLE);
        titleImgL.setImageResource(R.mipmap.icon_back);
        titleName.setText(R.string.poor_title_name);
        titleImgR.setImageResource(R.mipmap.icon_add);
        titleImgL.setOnClickListener(this);
        titleImgR.setOnClickListener(this);

        PoorOnScerllListenner onScrollListener = new PoorOnScerllListenner(footer, missionId, userId);
        onScrollListener.setOnLoadDataListener(this);
        poorList.setOnScrollListener(onScrollListener);

        poorLSpinner.setOnItemSelectedListener(this);
        poorRSpinner.setOnItemSelectedListener(this);
    }

    private void getData() {

        LogUtil.v("querStrselect", "querStrTime====" + querStrTime+"=========querStrPoverty====" + querStrPoverty);
        httpUtils = new HttpUtils();
        params = new RequestParams();
        params.addBodyParameter("pageNow", "1");
        params.addBodyParameter("pageSize", "10");
        params.addBodyParameter("missionId", missionId);
        params.addBodyParameter("userId", String.valueOf(userId));
        params.addBodyParameter("selectPoverty",querStrPoverty);
        params.addBodyParameter("selectYear",querStrTime);
        httpUtilsConnection(Constant.URL_POOR_LIST, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUtils.send(method, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.logJson("POOR_HTTP", "onSuccess" + responseInfo.result.toString());
                Gson gson = new Gson();
                PoorListBean listBean = gson.fromJson(responseInfo.result, PoorListBean.class);
                poorListBean = listBean.getPoorhouses();
                showListView(poorListBean);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("POOR_HTTP", "onFailure" + s);
                mShowView.showByType(CommonShowView.TYPE_ERROR);

            }
        });

    }

    private void showListView(List<Poorhouses> data) {
        if (data.size() != 0) {
            if (poorListAdapter == null) {
                LogUtil.v("hhhhhhhhhhlist","==1==>>>>"+data.toString());
                poorListAdapter = new PoorListAdapter(this, data);
                poorList.setAdapter(poorListAdapter);
                mShowView.showByType(CommonShowView.TYPE_CONTENT);
                poorList.setOnItemClickListener(this);
            }else {
                LogUtil.v("hhhhhhhhhhlist","==2==>>>>"+data.toString());
                //不为空，则刷新数据
                poorListBean.addAll(data);
                poorListAdapter.setData(poorListBean);
                //提醒ListView重新更新数据
                poorListAdapter.notifyDataSetChanged();
            }
        } else {
            poorListAdapter.clear();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLoadData(List<Poorhouses> data) {
        showListView(data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Poorhouses info = poorListBean.get(position);
        Intent intent = new Intent(PoorActivity.this, PoorDetailsActivity.class);
        Bundle bundle = new Bundle();
        String poorHouseId = info.getId();
        String poorName = info.getName();
        String poorCardNumber = info.getCradNumber();
        bundle.putString("poorHouseId", poorHouseId);
        bundle.putString("poorName", poorName);
        bundle.putString("poorCardNumber", poorCardNumber);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinner_left:
                querStrTime = (String) parent.getSelectedItem();
                LogUtil.v("querStr", "querStrTime====" + querStrTime);
                getData();
                break;
            case R.id.spinner_right:
                querStrPoverty = (String) parent.getSelectedItem();
                LogUtil.v("querStr", "querStrPoverty====" + querStrPoverty);
                getData();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    private void querConditionalPoorBean(List<Poorhouses> pData) {
//        for (int i = 0; i < pData.size() - 1; i++) {
//            Time = pData.get(i).getYear();
//            Poverty = pData.get(i).getPoverty();
//            Map<String, List<Poorhouses>> lp = new HashMap<String, List<Poorhouses>>();//时间查询Map
//            Map<String, List<Poorhouses>> jp = new HashMap<String, List<Poorhouses>>();//计划查询Map
//
//            for (Poorhouses p : pData) {
//                List<Poorhouses> t = lp.get(p.getYear());//时间查询结果
//                List<Poorhouses> j = jp.get(p.getPoverty());//计划查询结果
//                if (t == null) {
//                    t = new ArrayList<Poorhouses>();
//                    t.add(p);
//                    lp.put(String.valueOf(p.getYear()), t);
//                } else if (t != null) {
//                    t.add(p);
//                } else if (j == null) {
//                    j = new ArrayList<Poorhouses>();
//                    j.add(p);
//                    jp.put(p.getPoverty(), j);
//                } else if (j != null) {
//                    j.add(p);
//                }
//            }
//
//            if (querStrTime.equals("全部") && querStrPoverty.equals("全部")) {
//                showListView(pData);
//
//            } else if (querStrTime.equals(Time) && querStrPoverty.equals("全部")) {
//                LogUtil.v("spinner_left", "========时间=======全部==========");
//                for (String keyTime : lp.keySet()) {
//                    keyTime = String.valueOf(Time);
//                    pData = lp.get(keyTime);
//                    showListView(pData);
//                }
//
//            } else if (querStrTime.equals("全部") && querStrPoverty.equals(Poverty)) {
//                LogUtil.v("spinner_left", "========全部=======计划==========");
//                for (String keyPoverty : jp.keySet()) {
//                    keyPoverty = Poverty;
//                    pData = jp.get(keyPoverty);
//                    showListView(pData);
//                }
//
//            } else if (querStrTime.equals(Time) && querStrPoverty.equals(Poverty)) {
//                LogUtil.v("spinner_left", "========时间=======计划==========");
//                for (String keyTime : lp.keySet()) {
//                    keyTime = String.valueOf(Time);
//                    pData = lp.get(keyTime);
//                    querPovertytoTime(pData);
//                }
//            } else {
//                LogUtil.v("spinner_left", "=========占无数据=========");
//                ToastUtil.showInfo(PoorActivity.this, "占无数据");
//                pData = new ArrayList<Poorhouses>();
//                int size = pData.size();
//                showListView(pData);
//            }
//        }
//
//    }
//
//    private void querPovertytoTime(List<Poorhouses> Data) {
//        Map<String, List<Poorhouses>> jp = new HashMap<String, List<Poorhouses>>();//计划查询Map
//        for (Poorhouses p : Data) {
//            List<Poorhouses> j = jp.get(p.getPoverty());//计划查询结果
//            if (j == null) {
//                j = new ArrayList<Poorhouses>();
//                j.add(p);
//                jp.put(p.getPoverty(), j);
//            } else {
//                j.add(p);
//            }
//        }
//        for (String keyPoverty : jp.keySet()) {
//            keyPoverty = Poverty;
//            Data = jp.get(keyPoverty);
//            showListView(Data);
//        }
//    }

}
