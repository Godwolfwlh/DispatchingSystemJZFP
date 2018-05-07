package com.yhzhcs.dispatchingsystemjzfp.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.yhzhcs.dispatchingsystemjzfp.activitys.BasicDocumentActivity;
import com.yhzhcs.dispatchingsystemjzfp.activitys.EnterActivity;
import com.yhzhcs.dispatchingsystemjzfp.activitys.ModifyPassActivity;
import com.yhzhcs.dispatchingsystemjzfp.bean.UserInfo;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.ToastUtil;
import com.yhzhcs.dispatchingsystemjzfp.view.CircleImageView;
import com.zhy.android.percent.support.PercentLinearLayout;

/**
 * Created by Administrator on 2018/1/12.
 * <p>
 * author Luhuai Wu
 */

public class PersonalFragment extends Fragment implements View.OnClickListener {

    private View v;
    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    private CircleImageView PerImage;
    private TextView PerName;
    private TextView PerAdd;
    private PercentLinearLayout basicLayout;
    private PercentLinearLayout xgPassword;
    private Button entLogin;

    private SharedPreferences sp;
    private String missionId;

    private UserInfo userInfo;
    private BitmapUtils bitmapUtils;
    private Bundle bundle;

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.personal_fragment, null);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        missionId = sp.getString("MISSION_ID", "");
        getData();
        ininview();
        return v;
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

    private void ininview() {

        titleImgL = (ImageView) v.findViewById(R.id.title_left);
        titleName = (TextView) v.findViewById(R.id.title_name);
        titleImgR = (ImageView) v.findViewById(R.id.title_right);
        PerImage = (CircleImageView) v.findViewById(R.id.personal_image);
        PerName = (TextView) v.findViewById(R.id.personal_name);
        PerAdd = (TextView) v.findViewById(R.id.personal_add);
        basicLayout = (PercentLinearLayout) v.findViewById(R.id.basic_linear_layout);
        xgPassword = (PercentLinearLayout) v.findViewById(R.id.modify_the_password_linear_layout);
        entLogin = (Button) v.findViewById(R.id.personal_ent_login);

        basicLayout.setOnClickListener(this);
        xgPassword.setOnClickListener(this);
        entLogin.setOnClickListener(this);

        titleName.setText("个人中心");
        titleImgR.setVisibility(View.GONE);

    }

    private void getData() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("missionId", missionId);
        httpUtils.send(HttpMethod.POST, Constant.URL_USER_INFO, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("Personal_HTTP", "onSuccess" + responseInfo.result.toString());
                String body = responseInfo.result;
                Gson gson = new Gson();
                userInfo = gson.fromJson(body,UserInfo.class);
                LogUtil.v("listUserInfo","listUserInfo===>>>"+userInfo.toString());
                PerName.setText(userInfo.getMissionName());
                PerAdd.setText(userInfo.getMissionCompany());

                bitmapUtils = new BitmapUtils(getActivity());    //创建BitmapUtils对象，通过xUtils框架获取
                bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);   //设置图片清晰度
                bitmapUtils.display(PerImage, userInfo.getPhoto());

                bundle = new Bundle();
                bundle.putSerializable("USER_INFO", userInfo);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("Personal_HTTP", "onFailure" + s);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basic_linear_layout:
                Intent intentBD = new Intent(getActivity(), BasicDocumentActivity.class);
                if (bundle == null){
                    ToastUtil.showInfo(getActivity(), "您尚未登入，或者网络异常！");
                }else {
                    intentBD.putExtras(bundle);
                    startActivity(intentBD);
                }
                break;
            case R.id.modify_the_password_linear_layout:
                Intent intentMPW = new Intent(getActivity(), ModifyPassActivity.class);
                if (bundle == null){
                    ToastUtil.showInfo(getActivity(), "您尚未登入，或者网络异常！");
                }else {
                    intentMPW.putExtras(bundle);
                    startActivity(intentMPW);
                }
                break;
            case R.id.personal_ent_login:
                deleteUserMss();
                Intent intent = new Intent(getActivity(), EnterActivity.class);  //进入主界面
                startActivity(intent);  //开始跳转
                getActivity().finish();
                break;
        }
    }

    private void deleteUserMss() {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("USER_ID");
        editor.remove("MISSION_ID");
        editor.remove("USER_NAME");
        editor.remove("PASSWORD");
        editor.commit();
    }
}
