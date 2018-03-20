package com.yhzhcs.dispatchingsystemjzfp.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.activitys.ModifyPoorDetailsO;
import com.yhzhcs.dispatchingsystemjzfp.activitys.ModifyPoorDetailsT;
import com.yhzhcs.dispatchingsystemjzfp.bean.Liferequire;
import com.yhzhcs.dispatchingsystemjzfp.bean.Poor;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorDetailsBean;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.ToastUtil;

/**
 * Created by Administrator on 2018/1/24.
 */

public class DetailsFragment extends Fragment implements View.OnClickListener {

    private String poorHouseId;
    private View v;
    private ImageView poorImghead;
    private TextView nameInput, familyNum, reasonInput, attributeInput, telephoneInput, paperInput, addressInput;
    private TextView situationOne, situationTwo, situationThree, situationFour, situationFive, situationSix,
            situationSeven, situationEight, situationNine, situationTen, situationEleven, situationTwelve, situationThirteen, situationFourteen, situationFifteen;
    private TextView poorHeadEdit, poorDetailsEdit;
    private BitmapUtils bitmapUtils;
    private String Is = "否";
    private Bundle bundle;
    private PoorDetailsBean poorDetailsBean;

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;

    private boolean isGetData = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        v = inflater.inflate(R.layout.fragment_poor_base_situation, container, false);
        Bundle bundle = getArguments();
        poorHouseId = bundle.getString("poorHouseId");
        isPrepared = true;
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

    private void getData() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("poorHouseId", poorHouseId);
        httpUtils.send(HttpMethod.POST, Constant.URL_POOR_DETAILS, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("DetailsHttp", "onSuccess" + responseInfo.result.toString());
                String body = responseInfo.result;
                Gson gson = new Gson();
                poorDetailsBean = gson.fromJson(body, PoorDetailsBean.class);
                Poor poor = poorDetailsBean.getPoor();
                Liferequire liferequire = poorDetailsBean.getLifeRequire();
                intViewOne(poor);
                intSituation(poor, liferequire);

                bundle = new Bundle();
                bundle.putParcelable("POOR_LIST_BUNDLE", poorDetailsBean);

            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("DetailsHttp", "onFailure" + s);
            }
        });
    }

    private void intViewOne(Poor poor) {
        poorImghead = (ImageView) v.findViewById(R.id.poor_head_img_head);
        nameInput = (TextView) v.findViewById(R.id.poor_head_name_input);
        familyNum = (TextView) v.findViewById(R.id.poor_head_num_people_input);
        reasonInput = (TextView) v.findViewById(R.id.poor_head_reason_input);
        attributeInput = (TextView) v.findViewById(R.id.poor_head_attribute_i_input);
        telephoneInput = (TextView) v.findViewById(R.id.poor_head_telephone_input);
        paperInput = (TextView) v.findViewById(R.id.poor_head_paper_input);
        addressInput = (TextView) v.findViewById(R.id.poor_head_address_input);

        poorHeadEdit = (TextView) v.findViewById(R.id.poor_head_edit);
        poorDetailsEdit = (TextView) v.findViewById(R.id.poor_details_edit);
        poorHeadEdit.setOnClickListener(this);
        poorDetailsEdit.setOnClickListener(this);

        bitmapUtils = new BitmapUtils(getActivity());    //创建BitmapUtils对象，通过xUtils框架获取
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);   //设置图片清晰度
        bitmapUtils.display(poorImghead, poor.getPhoto());

        nameInput.setText(poor.getName());
        familyNum.setText(poor.getFamilyNumber() + "人");
        reasonInput.setText(poor.getMainPoorCause());
        attributeInput.setText(poor.getPoorProperty());
        telephoneInput.setText(poor.getPhone());
        paperInput.setText(poor.getCradNumber());
        addressInput.setText(poor.getCompanyName() + poor.getCounty() + poor.getTown() + poor.getVillage());

    }

    private void intSituation(Poor poor, Liferequire liferequire) {

        situationOne = (TextView) v.findViewById(R.id.situation_td_one_ok);
        situationTwo = (TextView) v.findViewById(R.id.situation_td_two_ok);
        situationThree = (TextView) v.findViewById(R.id.situation_td_three_ok);
        situationFour = (TextView) v.findViewById(R.id.situation_td_four_ok);
        situationFive = (TextView) v.findViewById(R.id.situation_td_five_ok);
        situationSix = (TextView) v.findViewById(R.id.situation_td_six_ok);
        situationSeven = (TextView) v.findViewById(R.id.situation_td_seven_ok);
        situationEight = (TextView) v.findViewById(R.id.situation_td_eight_ok);
        situationNine = (TextView) v.findViewById(R.id.situation_td_nine_ok);
        situationTen = (TextView) v.findViewById(R.id.situation_td_ten_ok);
        situationEleven = (TextView) v.findViewById(R.id.situation_td_eleven_ok);
        situationTwelve = (TextView) v.findViewById(R.id.situation_td_twelve_ok);
        situationThirteen = (TextView) v.findViewById(R.id.situation_td_thirteen_ok);
        situationFourteen = (TextView) v.findViewById(R.id.situation_td_fourteen_ok);
        situationFifteen = (TextView) v.findViewById(R.id.situation_td_fifteen_ok);

        situationOne.setText((liferequire.getIsCooperative().equals("")) ? Is : liferequire.getIsCooperative());
        situationThree.setText((poor.getPoverty().equals("")) ? Is : poor.getPoverty());
        situationFour.setText((liferequire.getIsDrinkingWater().equals("")) ? Is : liferequire.getIsDrinkingWater());
        situationFive.setText((liferequire.getIsElectricity().equals("")) ? Is : liferequire.getIsElectricity());
        situationSix.setText((liferequire.getIsDrinkingSafe().equals("")) ? Is : liferequire.getIsDrinkingSafe());
        situationSeven.setText((liferequire.getIsHe().equals("")) ? Is : liferequire.getIsHe());
        situationEight.setText((liferequire.getIsDilapidatedHouse().equals("")) ? Is : liferequire.getIsDilapidatedHouse());
        situationNine.setText((liferequire.getIsRelocatedHouse().equals("")) ? Is : liferequire.getIsRelocatedHouse());
        situationTen.setText((liferequire.getDangerouLevel().equals("")) ? Is : liferequire.getDangerouLevel());
        situationEleven.setText((poor.getIsTeach().equals("")) ? Is : poor.getIsTeach());
        situationTwelve.setText((liferequire.getIsCommodityHouse().equals("")) ? Is : liferequire.getIsCommodityHouse());
        situationThirteen.setText((liferequire.getIsCar().equals("")) ? Is : liferequire.getIsCar());
        situationFourteen.setText((liferequire.getIsCivilServant().equals("")) ? Is : liferequire.getIsCivilServant());
        situationFifteen.setText((liferequire.getIsBusinessRegister().equals("")) ? Is : liferequire.getIsBusinessRegister());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.poor_head_edit:
                Intent intento = new Intent(getActivity(), ModifyPoorDetailsO.class);
                if (bundle == null) {
                    ToastUtil.showInfo(getActivity(), "您尚未登入，或者网络异常！");
                } else {
                    intento.putExtras(bundle);
                    startActivity(intento);
                }
                break;
            case R.id.poor_details_edit:
                Intent intentt = new Intent(getActivity(), ModifyPoorDetailsT.class);
                if (bundle == null) {
                    ToastUtil.showInfo(getActivity(), "您尚未登入，或者网络异常！");
                } else {
                    intentt.putExtras(bundle);
                    startActivity(intentt);
                }
                break;
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
}
