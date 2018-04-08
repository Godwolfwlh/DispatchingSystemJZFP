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
import com.yhzhcs.dispatchingsystemjzfp.utils.ToastUtil;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */

public class IncomeFragment extends Fragment implements View.OnClickListener {

    private String poorHouseId, poorCardNumber;
    private View v;
    private String Is = "0";

    private List<Personalincome> listBean;
    private TextView situationOne, situationTwo, situationThree, situationFour, situationFive, situationSix, situationSeven, situationEight, situationNine;
    private TextView subTitle, subOne, subTwo, subThree, subFour, subFive, subSix, subSeven, subEight, subNine, subTen, subEleven, subTwelve, subThirteen, subFourteen, subFifteen, subSixteen;
    private TextView subTextOne,subTextTwo,subTextThree,subTextFour;
    private PercentLinearLayout incomeSub;
    private PercentLinearLayout incomeSubOne, incomeSubTwo, incomeSubThree, incomeSubFour, incomeSubFive, incomeSubSix, incomeSubSeven, incomeSubEight;

    /**
     * Fragment当前状态是否可见
     */
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
        poorCardNumber = bundle.getString("poorCardNumber");
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
        if (getUserVisibleHint()) {
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
        params.addBodyParameter("cardNumber", poorCardNumber);
        httpUtils.send(HttpMethod.POST, Constant.URL_POOR_INCOME, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.logJson("Income_Http", "onSuccess===>>>" + responseInfo.result.toString());
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
        listBean = new ArrayList<>();
        listBean = personalincomes;
        incomeSub = (PercentLinearLayout) v.findViewById(R.id.income_sub);
        incomeSub.setVisibility(View.GONE);
        incomeSubOne = (PercentLinearLayout) v.findViewById(R.id.income_tr_one);
        incomeSubTwo = (PercentLinearLayout) v.findViewById(R.id.income_tr_two);
        incomeSubThree = (PercentLinearLayout) v.findViewById(R.id.income_tr_three);
        incomeSubFour = (PercentLinearLayout) v.findViewById(R.id.income_tr_four);
        incomeSubFive = (PercentLinearLayout) v.findViewById(R.id.income_tr_five);
        incomeSubSix = (PercentLinearLayout) v.findViewById(R.id.income_tr_six);
        incomeSubSeven = (PercentLinearLayout) v.findViewById(R.id.income_tr_seven);
        incomeSubEight = (PercentLinearLayout) v.findViewById(R.id.income_tr_eight);

        situationOne = (TextView) v.findViewById(R.id.situation_td_one_ok_i);
        situationTwo = (TextView) v.findViewById(R.id.situation_td_two_ok_i);
        situationThree = (TextView) v.findViewById(R.id.situation_td_nine_ok_i);
        situationFour = (TextView) v.findViewById(R.id.situation_td_ten_ok_i);
        situationFive = (TextView) v.findViewById(R.id.situation_td_thirteen_ok_i);
        situationSix = (TextView) v.findViewById(R.id.situation_td_fourteen_ok_i);
        situationSeven = (TextView) v.findViewById(R.id.situation_td_eleven_ok_i);
        situationEight = (TextView) v.findViewById(R.id.situation_td_twelve_ok_i);
        situationNine = (TextView) v.findViewById(R.id.situation_td_fifteen_ok_i);

        subTitle = (TextView) v.findViewById(R.id.income_title_sub);
        subOne = (TextView) v.findViewById(R.id.income_td_one_ok);
        subTwo = (TextView) v.findViewById(R.id.income_td_two_ok);
        subThree = (TextView) v.findViewById(R.id.income_td_three_ok);
        subFour = (TextView) v.findViewById(R.id.income_td_four_ok);
        subFive = (TextView) v.findViewById(R.id.income_td_five_ok);
        subSix = (TextView) v.findViewById(R.id.income_td_six_ok);
        subSeven = (TextView) v.findViewById(R.id.income_td_seven_ok);
        subEight = (TextView) v.findViewById(R.id.income_td_eight_ok);
        subNine = (TextView) v.findViewById(R.id.income_td_nine_ok);
        subTen = (TextView) v.findViewById(R.id.income_td_ten_ok);
        subEleven = (TextView) v.findViewById(R.id.income_td_eleven_ok);
        subTwelve = (TextView) v.findViewById(R.id.income_td_twelve_ok);
        subThirteen = (TextView) v.findViewById(R.id.income_td_thirteen_ok);
        subFourteen = (TextView) v.findViewById(R.id.income_td_fourteen_ok);
        subFifteen = (TextView) v.findViewById(R.id.income_td_fifteen_ok);
        subSixteen = (TextView) v.findViewById(R.id.income_td_sixteen_ok);

        subTextOne = (TextView) v.findViewById(R.id.income_td_one);
        subTextTwo = (TextView) v.findViewById(R.id.income_td_two);
        subTextThree = (TextView) v.findViewById(R.id.income_td_three);
        subTextFour = (TextView) v.findViewById(R.id.income_td_four);

        situationOne.setOnClickListener(this);
        situationThree.setOnClickListener(this);
        situationFour.setOnClickListener(this);
        situationFive.setOnClickListener(this);
        situationSeven.setOnClickListener(this);
        situationNine.setOnClickListener(this);

        if (null == personalincomes || personalincomes.size() == 0) {
            ToastUtil.showInfo(getActivity(), "該用戶無收入！");
        } else {
            situationOne.setText((personalincomes.get(0).getSalary().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getSalary());
            situationTwo.setText((personalincomes.get(0).getYearIncome().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getYearIncome());
            situationThree.setText((personalincomes.get(0).getProduction().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getProduction());
            situationFour.setText((personalincomes.get(0).getProductbility().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getProductbility());
            situationFive.setText((personalincomes.get(0).getTransfer().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getTransfer());
            situationSix.setText((personalincomes.get(0).getNetIncome().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getNetIncome());
            situationSeven.setText((personalincomes.get(0).getProperty().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getProperty());
            situationEight.setText((personalincomes.get(0).getAverageIncome().equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getAverageIncome());
            situationNine.setText((String.valueOf(personalincomes.get(0).getPovertyMoney()).equals("")) ? "￥" + Is : "￥" + personalincomes.get(0).getAverageIncome());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.situation_td_one_ok_i:
                showsituationOne();
                break;
            case R.id.situation_td_nine_ok_i:
                showsituationThree();
                break;
            case R.id.situation_td_ten_ok_i:
                showsituatonFour();
                break;
            case R.id.situation_td_thirteen_ok_i:
                showsituationFive();
                break;
            case R.id.situation_td_eleven_ok_i:
                showsituationSeven();
                break;
            case R.id.situation_td_fifteen_ok_i:
                showsituationNine();
                break;
        }
    }

    private void showsituationOne(){
        incomeSub.setVisibility(View.VISIBLE);
        incomeSubTwo.setVisibility(View.GONE);
        incomeSubThree.setVisibility(View.GONE);
        incomeSubFour.setVisibility(View.GONE);
        incomeSubFive.setVisibility(View.GONE);
        incomeSubSix.setVisibility(View.GONE);
        incomeSubSeven.setVisibility(View.GONE);
        incomeSubEight.setVisibility(View.GONE);
        subTitle.setText("工资性收入");
        subTextOne.setText("工资性收入：");
        subTextTwo.setVisibility(View.GONE);
        subOne.setText((listBean.get(0).getSalary().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getSalary());
    }
    private void showsituationThree(){
        incomeSub.setVisibility(View.VISIBLE);
        incomeSubTwo.setVisibility(View.GONE);
        incomeSubThree.setVisibility(View.GONE);
        incomeSubFour.setVisibility(View.GONE);
        incomeSubFive.setVisibility(View.GONE);
        incomeSubSix.setVisibility(View.GONE);
        incomeSubSeven.setVisibility(View.GONE);
        incomeSubEight.setVisibility(View.GONE);
        subTitle.setText("生产经营性收入");
        subTextOne.setText("生产经营性收入：");
        subTextTwo.setVisibility(View.GONE);
        subOne.setText((listBean.get(0).getProduction().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getProduction());
    }

    private void showsituatonFour(){
        incomeSub.setVisibility(View.VISIBLE);
        incomeSubTwo.setVisibility(View.GONE);
        incomeSubThree.setVisibility(View.GONE);
        incomeSubFour.setVisibility(View.GONE);
        incomeSubFive.setVisibility(View.GONE);
        incomeSubSix.setVisibility(View.GONE);
        incomeSubSeven.setVisibility(View.GONE);
        incomeSubEight.setVisibility(View.GONE);
        subTitle.setText("生产经营性支出");
        subTextOne.setText("生产经营性支出：");
        subTextTwo.setVisibility(View.GONE);
        subOne.setText((listBean.get(0).getProductbility().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getProductbility());
    }
    private void showsituationFive(){
        incomeSub.setVisibility(View.VISIBLE);
        incomeSubTwo.setVisibility(View.VISIBLE);
        incomeSubThree.setVisibility(View.VISIBLE);
        incomeSubFour.setVisibility(View.VISIBLE);
        incomeSubFive.setVisibility(View.VISIBLE);
        incomeSubSix.setVisibility(View.VISIBLE);
        incomeSubSeven.setVisibility(View.VISIBLE);
        incomeSubEight.setVisibility(View.VISIBLE);
        subTitle.setText("转移性收入子项详情");
        subTextOne.setText("城低保：");
        subTextTwo.setText("养老保险金：");
        subTextThree.setText("生态补偿金：");
        subTextFour.setText("教育补助：");
        subTextTwo.setVisibility(View.VISIBLE);
        subOne.setText((String.valueOf(listBean.get(0).getMzjCdbMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjCdbMoney());
        subTwo.setText((String.valueOf(listBean.get(0).getYlbxMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getYlbxMoney());
        subThree.setText((listBean.get(0).getEcological().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getEcological());
        subFour.setText((String.valueOf(listBean.get(0).getEducationMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getEducationMoney());
        subFive.setText((String.valueOf(listBean.get(0).getMzjGlbtMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjGlbtMoney());
        subSix.setText((String.valueOf(listBean.get(0).getMzjWbMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjWbMoney());
        subSeven.setText((String.valueOf(listBean.get(0).getMzjNdbMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjNdbMoney());
        subEight.setText((String.valueOf(listBean.get(0).getMzjSjbtMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjSjbtMoney());
        subNine.setText((String.valueOf(listBean.get(0).getMzjFlybtMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjFlybtMoney());
        subTen.setText((String.valueOf(listBean.get(0).getWfgzMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getWfgzMoney());
        subEleven.setText((String.valueOf(listBean.get(0).getSocialAssMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getSocialAssMoney());
        subTwelve.setText((String.valueOf(listBean.get(0).getLandExpropMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getLandExpropMoney());
        subThirteen.setText((String.valueOf(listBean.get(0).getAgMachineryMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getAgMachineryMoney());
        subFourteen.setText((String.valueOf(listBean.get(0).getJsjJsbzMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getJsjJsbzMoney());
        subFifteen.setText((String.valueOf(listBean.get(0).getComprehensiveMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getComprehensiveMoney());
        subSixteen.setText((listBean.get(0).getOtherTransfer().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getProductbility());
    }
    private void showsituationSeven(){
        incomeSub.setVisibility(View.VISIBLE);
        incomeSubTwo.setVisibility(View.VISIBLE);
        incomeSubThree.setVisibility(View.GONE);
        incomeSubFour.setVisibility(View.GONE);
        incomeSubFive.setVisibility(View.GONE);
        incomeSubSix.setVisibility(View.GONE);
        incomeSubSeven.setVisibility(View.GONE);
        incomeSubEight.setVisibility(View.GONE);
        subTitle.setText("财产性收入");
        subTextOne.setText("特惠贷分红：");
        subTextTwo.setText("土地租金：");
        subTextThree.setText("存款利息：");
        subTextFour.setText("其它收入：");
        subTextTwo.setVisibility(View.VISIBLE);
        subOne.setText((String.valueOf(listBean.get(0).getFpjThdMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getFpjThdMoney());
        subTwo.setText((String.valueOf(listBean.get(0).getLandRentMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getLandRentMoney());
        subThree.setText((String.valueOf(listBean.get(0).getInterestDeposit()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getInterestDeposit());
        subFour.setText((String.valueOf(listBean.get(0).getPropertyOther()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getPropertyOther());
    }
    private void showsituationNine(){
        incomeSub.setVisibility(View.VISIBLE);
        incomeSubTwo.setVisibility(View.GONE);
        incomeSubThree.setVisibility(View.GONE);
        incomeSubFour.setVisibility(View.GONE);
        incomeSubFive.setVisibility(View.GONE);
        incomeSubSix.setVisibility(View.GONE);
        incomeSubSeven.setVisibility(View.GONE);
        incomeSubEight.setVisibility(View.GONE);
        subTitle.setText("精准扶贫收入");
        subTextOne.setText("精准扶贫收入：");
        subTextTwo.setVisibility(View.GONE);
        subOne.setText((String.valueOf(listBean.get(0).getPovertyMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getAverageIncome());
    }

}
