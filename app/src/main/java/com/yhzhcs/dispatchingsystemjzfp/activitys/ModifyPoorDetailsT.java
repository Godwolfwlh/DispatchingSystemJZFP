package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Intent;
import android.os.Bundle;
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
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorDetailsBean;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ModifyPoorDetailsT extends AppCompatActivity implements View.OnClickListener {

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;
    PoorDetailsBean poorDetailsBean;

    private EditText situationOne, situationTwo, situationThree, situationFour, situationFive, situationSix,
            situationSeven, situationEight, situationNine, situationTen, situationEleven, situationTwelve, situationThirteen, situationFourteen, situationFifteen;
    private String One, Two, Three, Four, Five, Six,
            Seven, Eight, Nine, Ten, Eleven, Twelve, Thirteen, Fourteen, Fifteen;

    private Intent intent = null;
    private String poorHouseId = null;
    private String poorCardNumber = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_poordetails_dw);
        Bundle bundle = getIntent().getExtras();
        poorDetailsBean = bundle.getParcelable("POOR_LIST_BUNDLE");
        poorHouseId = bundle.getString("poorHouseId");
        poorCardNumber = bundle.getString("poorCardNumber");
        LogUtil.v("BDUSERINFO", poorDetailsBean.getLifeRequire().toString());
        intView();
    }

    private void intView() {
        titleImgL = (ImageView) findViewById(R.id.title_left);
        titleName = (TextView) findViewById(R.id.title_name);
        titleImgR = (ImageView) findViewById(R.id.title_right);//modify_poor_dd_fifteen

        titleImgL.setVisibility(View.VISIBLE);
        titleImgL.setImageResource(R.mipmap.icon_back);
        titleName.setText("基本情况");
        titleImgR.setImageResource(R.mipmap.icon_sure_1);
        titleImgL.setOnClickListener(this);
        titleImgR.setOnClickListener(this);

        situationOne = (EditText) findViewById(R.id.modify_poor_dd_one);
        situationTwo = (EditText) findViewById(R.id.modify_poor_dd_two);
        situationThree = (EditText) findViewById(R.id.modify_poor_dd_three);
        situationFour = (EditText) findViewById(R.id.modify_poor_dd_four);
        situationFive = (EditText) findViewById(R.id.modify_poor_dd_five);
        situationSix = (EditText) findViewById(R.id.modify_poor_dd_six);
        situationSeven = (EditText) findViewById(R.id.modify_poor_dd_seven);
        situationEight = (EditText) findViewById(R.id.modify_poor_dd_eight);
        situationNine = (EditText) findViewById(R.id.modify_poor_dd_nine);
        situationTen = (EditText) findViewById(R.id.modify_poor_dd_ten);
        situationEleven = (EditText) findViewById(R.id.modify_poor_dd_eleven);
        situationTwelve = (EditText) findViewById(R.id.modify_poor_dd_twelve);
        situationThirteen = (EditText) findViewById(R.id.modify_poor_dd_thirteen);
        situationFourteen = (EditText) findViewById(R.id.modify_poor_dd_fourteen);
        situationFifteen = (EditText) findViewById(R.id.modify_poor_dd_fifteen);

        situationOne.setText((poorDetailsBean.getLifeRequire().getIsCooperative().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsCooperative());
        situationThree.setText((poorDetailsBean.getPoor().getPoverty().equals("")) ? "否" : poorDetailsBean.getPoor().getPoverty());
        situationFour.setText((poorDetailsBean.getLifeRequire().getIsDrinkingWater().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsDrinkingWater());
        situationFive.setText((poorDetailsBean.getLifeRequire().getIsElectricity().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsElectricity());
        situationSix.setText((poorDetailsBean.getLifeRequire().getIsDrinkingSafe().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsDrinkingSafe());
        situationSeven.setText((poorDetailsBean.getLifeRequire().getIsHe().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsHe());
        situationEight.setText((poorDetailsBean.getLifeRequire().getIsDilapidatedHouse().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsDilapidatedHouse());
        situationNine.setText((poorDetailsBean.getLifeRequire().getIsRelocatedHouse().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsRelocatedHouse());
        situationTen.setText((poorDetailsBean.getLifeRequire().getDangerouLevel().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getDangerouLevel());
        situationEleven.setText((poorDetailsBean.getPoor().getIsTeach().equals("")) ? "否" : poorDetailsBean.getPoor().getIsTeach());
        situationTwelve.setText((poorDetailsBean.getLifeRequire().getIsCommodityHouse().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsCommodityHouse());
        situationThirteen.setText((poorDetailsBean.getLifeRequire().getIsCar().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsCar());
        situationFourteen.setText((poorDetailsBean.getLifeRequire().getIsCivilServant().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsCivilServant());
        situationFifteen.setText((poorDetailsBean.getLifeRequire().getIsBusinessRegister().equals("")) ? "否" : poorDetailsBean.getLifeRequire().getIsBusinessRegister());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                intent = new Intent(ModifyPoorDetailsT.this, PoorDetailsActivity.class);
                intent.putExtra("FRAGMENT_ID", 0);
                intent.putExtra("poorHouseId", poorHouseId);
                intent.putExtra("poorCardNumber", poorCardNumber);
                startActivity(intent);
                finish();
                break;
            case R.id.title_right:
                updatePoorAndLifeRequire();
                break;
        }
    }

    private void updatePoorAndLifeRequire() {

        One = situationOne.getText().toString().trim();
        Two = situationTwo.getText().toString().trim();
        Three = situationThree.getText().toString().trim();
        Four = situationFour.getText().toString().trim();
        Five = situationFive.getText().toString().trim();
        Six = situationSix.getText().toString().trim();
        Seven = situationSeven.getText().toString().trim();
        Eight = situationEight.getText().toString().trim();
        Nine = situationNine.getText().toString().trim();
        Ten = situationTen.getText().toString().trim();
        Eleven = situationEleven.getText().toString().trim();
        Twelve = situationTwelve.getText().toString().trim();
        Thirteen = situationThirteen.getText().toString().trim();
        Fourteen = situationFourteen.getText().toString().trim();
        Fifteen = situationFifteen.getText().toString().trim();

        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("cradNumber", poorDetailsBean.getPoor().getCradNumber());
        params.addBodyParameter("poverty", Three);
        params.addBodyParameter("isTeach", Eleven);
        params.addBodyParameter("isCooperative", One);
        params.addBodyParameter("isDrinkingWater", Four);
        params.addBodyParameter("isElectricity", Five);
        params.addBodyParameter("isDrinkingSafe", Six);
        params.addBodyParameter("isHe", Seven);
        params.addBodyParameter("isDilapidatedHouse", Eight);
        params.addBodyParameter("isRelocatedHouse", Nine);
        params.addBodyParameter("dangerouLevel", Ten);
        params.addBodyParameter("isCommodityHouse", Twelve);
        params.addBodyParameter("isCar", Thirteen);
        params.addBodyParameter("isCivilServant", Fourteen);
        params.addBodyParameter("isBusinessRegister", Fifteen);
//        params.addBodyParameter("admin",Four);
//        params.addBodyParameter("1",Four);

        httpUtils.send(HttpMethod.POST, Constant.URL_MODIFY_LIFEREQUIRE, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("MODIFYLIFEREQUIREDETAILSOHTTP", "onSuccess：" + responseInfo.result.toString());
                intent = new Intent(ModifyPoorDetailsT.this, PoorDetailsActivity.class);
                intent.putExtra("FRAGMENT_ID", 0);
                intent.putExtra("poorHouseId", poorHouseId);
                intent.putExtra("poorCardNumber", poorCardNumber);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("MODIFYLIFEREQUIREDETAILSOHTTP", "onFailure：" + s.toString());
            }
        });
    }
}
