package com.yhzhcs.dispatchingsystemjzfp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Personalincome;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorIncomeBean;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */

public class IncomeFragment extends Fragment {

    private String poorHouseId;
    private View v;

    private List<Personalincome> listBean;
    private TextView situationOne, situationTwo, situationThree, situationFour, situationFive, situationSix, situationSeven, situationEight, situationNine;

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;

    private boolean isGetData = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_poor_income, container, false);
        Bundle bundle = getArguments();
        poorHouseId = bundle.getString("poorHouseId");
        return v;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            isGetData = true;
            //   这里可以做网络请求或者需要的数据刷新操作
            getData();
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isGetData) {
            //   这里可以做网络请求或者需要的数据刷新操作
            getData();
            isGetData = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    private void lazyLoad() {
        if (mHasLoadedOnce || !isPrepared)
            return;
        mHasLoadedOnce = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHasLoadedOnce = false;
        isPrepared = false;
    }

    private void getData() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("poorHouseId", poorHouseId);
        httpUtils.send(HttpMethod.POST, Constant.URL_POOR_INCOME, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("Income_Http", "onSuccess===>>>" + responseInfo.result.toString());
                String body = responseInfo.result;
                Gson gson = new Gson();
                PoorIncomeBean poorIncomeBean = gson.fromJson(body, PoorIncomeBean.class);
                listBean = poorIncomeBean.getPersonalIncome();
                LogUtil.v("Income_listBean", "listBean===>>>" + listBean);
                intView(listBean);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("Income_Http", "onFailure===>>>" + s);
            }
        });
    }

    private void intView(List<Personalincome> personalincomes) {
        situationOne = (TextView) v.findViewById(R.id.situation_td_one_ok_i);
        situationTwo = (TextView) v.findViewById(R.id.situation_td_two_ok_i);
        situationThree = (TextView) v.findViewById(R.id.situation_td_nine_ok_i);
        situationFour = (TextView) v.findViewById(R.id.situation_td_ten_ok_i);
        situationFive = (TextView) v.findViewById(R.id.situation_td_thirteen_ok_i);
        situationSix = (TextView) v.findViewById(R.id.situation_td_fourteen_ok_i);
        situationSeven = (TextView) v.findViewById(R.id.situation_td_eleven_ok_i);
        situationEight = (TextView) v.findViewById(R.id.situation_td_twelve_ok_i);
        situationNine = (TextView) v.findViewById(R.id.situation_td_fifteen_ok_i);
        String Is = "0";

//        situationOne.setText((personalincomes.get(0).getSalary().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getSalary());
//        situationTwo.setText((personalincomes.get(0).getYearIncome().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getYearIncome());
//        situationThree.setText((personalincomes.get(0).getProduction().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getProduction());
//        situationFour.setText((personalincomes.get(0).getProductbility().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getProductbility());
//        situationFive.setText((personalincomes.get(0).getTransfer().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getTransfer());
//        situationSix.setText((personalincomes.get(0).getNetIncome().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getNetIncome());
//        situationSeven.setText((personalincomes.get(0).getProperty().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getProperty());
//        situationEight.setText((personalincomes.get(0).getAverageIncome().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getAverageIncome());
//        situationNine.setText((String.valueOf(personalincomes.get(0).getPovertyMoney()).equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getAverageIncome());
    }
}
