package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Personalincome;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.util.List;

public class ModifyDetailsEdit extends AppCompatActivity implements View.OnClickListener {

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    private List<Personalincome> listBean;
    private String Is = "0";
    private String poorHouseId = null;
    private Intent intent = null;

    private EditText situationOne, situationTwo, situationThree, situationFour, situationFive, situationSix,
            situationSeven, situationEight, situationNine;
    private EditText situationOneInco;
    private EditText situationThreeInco;
    private EditText situationFiveIncoOne, situationFiveIncoTwo, situationFiveIncoThree, situationFiveIncoFour, situationFiveIncoFive, situationFiveIncoSix, situationFiveIncoSeven, situationFiveIncoEight,
            situationFiveIncoNine, situationFiveIncoTen, situationFiveIncoEleven, situationFiveIncoTwelve, situationFiveIncoThirteen, situationFiveIncoFourteen, situationFiveIncoFifteen, situationFiveIncoSixteen;
    private EditText situationSevenIncoOne, situationSevenIncoTwo, situationSevenIncoThree, situationSevenIncoFour;
    private EditText situationNineInco;

    private String sitOne, sitTwo, sitThree, sitFour, sitFive, sitSix,
            sitSeven, sitEight, sitNine;
    private String sitIncoOne;
    private String sitIncoThree;
    private String sitFiveIncoOne, sitFiveIncoTwo, sitFiveIncoThree, sitFiveIncoFour, sitFiveIncoFive, sitFiveIncoSix, sitFiveIncoSeven, sitFiveIncoEight,
            sitFiveIncoNine, sitFiveIncoTen, sitFiveIncoEleven, sitFiveIncoTwelve, sitFiveIncoThirteen, sitFiveIncoFourteen, sitFiveIncoFifteen, sitFiveIncoSixteen;
    private String sitSevenIncoOne, sitSevenIncoTwo, sitSevenIncoThree, sitSevenIncoFour;
    private String sitIncoNine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_edit);
        Bundle bundle = getIntent().getExtras();
        listBean = bundle.getParcelableArrayList("POOR_INCOME_BUNDLE");
        poorHouseId = bundle.getString("poorHouseId");
        LogUtil.v("BDUSERINFO", listBean.toString());
        intView();
        updatePoorDetails();
    }

    private void intView() {
        titleImgL = (ImageView) findViewById(R.id.title_left);
        titleName = (TextView) findViewById(R.id.title_name);
        titleImgR = (ImageView) findViewById(R.id.title_right);

        titleImgL.setVisibility(View.VISIBLE);
        titleImgL.setImageResource(R.mipmap.icon_back);
        titleName.setText("基本情况");
        titleImgR.setImageResource(R.mipmap.icon_sure_1);
        titleImgL.setOnClickListener(this);
        titleImgR.setOnClickListener(this);

        //收入情况
        situationOne = (EditText) findViewById(R.id.modify_poor_details_one);
        situationTwo = (EditText) findViewById(R.id.modify_poor_details_two);
        situationThree = (EditText) findViewById(R.id.modify_poor_details_three);
        situationFour = (EditText) findViewById(R.id.modify_poor_details_four);
        situationFive = (EditText) findViewById(R.id.modify_poor_details_five);
        situationSix = (EditText) findViewById(R.id.modify_poor_details_six);
        situationSeven = (EditText) findViewById(R.id.modify_poor_details_seven);
        situationEight = (EditText) findViewById(R.id.modify_poor_details_eight);
        situationNine = (EditText) findViewById(R.id.modify_poor_details_nine);

        if (null != listBean || listBean.size() != 0) {
            situationOne.setText((listBean.get(0).getSalary().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getSalary());
            situationTwo.setText((listBean.get(0).getYearIncome().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getYearIncome());
            situationThree.setText((listBean.get(0).getProduction().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getProduction());
            situationFour.setText((listBean.get(0).getProductbility().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getProductbility());
            situationFive.setText((listBean.get(0).getTransfer().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getTransfer());
            situationSix.setText((listBean.get(0).getNetIncome().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getNetIncome());
            situationSeven.setText((listBean.get(0).getProperty().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getProperty());
            situationEight.setText((listBean.get(0).getAverageIncome().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getAverageIncome());
            situationNine.setText((String.valueOf(listBean.get(0).getPovertyMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getAverageIncome());
        }
        //工资性收入子项详情
        situationOneInco = (EditText) findViewById(R.id.modify_poor_details_one_inco);
        if (listBean != null || listBean.size() != 0) {
            situationOneInco.setText((listBean.get(0).getSalary().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getSalary());
        }
        //生产经营性收入子项详情
        situationThreeInco = (EditText) findViewById(R.id.modify_poor_details_three_inco);
        if (null != listBean || listBean.size() != 0) {
            situationThreeInco.setText((listBean.get(0).getProduction().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getProduction());
        }
        //转移性收入子项详情
        situationFiveIncoOne = (EditText) findViewById(R.id.modify_poor_details_five_inco_one);
        situationFiveIncoTwo = (EditText) findViewById(R.id.modify_poor_details_five_inco_two);
        situationFiveIncoThree = (EditText) findViewById(R.id.modify_poor_details_five_inco_three);
        situationFiveIncoFour = (EditText) findViewById(R.id.modify_poor_details_five_inco_four);
        situationFiveIncoFive = (EditText) findViewById(R.id.modify_poor_details_five_inco_five);
        situationFiveIncoSix = (EditText) findViewById(R.id.modify_poor_details_five_inco_six);
        situationFiveIncoSeven = (EditText) findViewById(R.id.modify_poor_details_five_inco_seven);
        situationFiveIncoEight = (EditText) findViewById(R.id.modify_poor_details_five_inco_eight);
        situationFiveIncoNine = (EditText) findViewById(R.id.modify_poor_details_five_inco_nine);
        situationFiveIncoTen = (EditText) findViewById(R.id.modify_poor_details_five_inco_ten);
        situationFiveIncoEleven = (EditText) findViewById(R.id.modify_poor_details_five_inco_eleven);
        situationFiveIncoTwelve = (EditText) findViewById(R.id.modify_poor_details_five_inco_twelve);
        situationFiveIncoThirteen = (EditText) findViewById(R.id.modify_poor_details_five_inco_thirteen);
        situationFiveIncoFourteen = (EditText) findViewById(R.id.modify_poor_details_five_inco_fourteen);
        situationFiveIncoFifteen = (EditText) findViewById(R.id.modify_poor_details_five_inco_fifteen);
        situationFiveIncoSixteen = (EditText) findViewById(R.id.modify_poor_details_five_inco_sixteen);
        if (null != listBean || listBean.size() != 0) {
            situationFiveIncoOne.setText((String.valueOf(listBean.get(0).getMzjCdbMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjCdbMoney());
            situationFiveIncoTwo.setText((String.valueOf(listBean.get(0).getYlbxMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getYlbxMoney());
            situationFiveIncoThree.setText((listBean.get(0).getEcological().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getEcological());
            situationFiveIncoFour.setText((String.valueOf(listBean.get(0).getEducationMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getEducationMoney());
            situationFiveIncoFive.setText((String.valueOf(listBean.get(0).getMzjGlbtMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjGlbtMoney());
            situationFiveIncoSix.setText((String.valueOf(listBean.get(0).getMzjWbMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjWbMoney());
            situationFiveIncoSeven.setText((String.valueOf(listBean.get(0).getMzjNdbMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjNdbMoney());
            situationFiveIncoEight.setText((String.valueOf(listBean.get(0).getMzjSjbtMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjSjbtMoney());
            situationFiveIncoNine.setText((String.valueOf(listBean.get(0).getMzjFlybtMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getMzjFlybtMoney());
            situationFiveIncoTen.setText((String.valueOf(listBean.get(0).getWfgzMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getWfgzMoney());
            situationFiveIncoEleven.setText((String.valueOf(listBean.get(0).getSocialAssMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getSocialAssMoney());
            situationFiveIncoTwelve.setText((String.valueOf(listBean.get(0).getLandExpropMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getLandExpropMoney());
            situationFiveIncoThirteen.setText((String.valueOf(listBean.get(0).getAgMachineryMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getAgMachineryMoney());
            situationFiveIncoFourteen.setText((String.valueOf(listBean.get(0).getJsjJsbzMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getJsjJsbzMoney());
            situationFiveIncoFifteen.setText((String.valueOf(listBean.get(0).getComprehensiveMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getComprehensiveMoney());
            situationFiveIncoSixteen.setText((listBean.get(0).getOtherTransfer().equals("")) ? "￥" + Is : "￥" + listBean.get(0).getProductbility());
        }
        //财产性收入子项详情
        situationSevenIncoOne = (EditText) findViewById(R.id.modify_poor_details_seven_inco_one);
        situationSevenIncoTwo = (EditText) findViewById(R.id.modify_poor_details_seven_inco_two);
        situationSevenIncoThree = (EditText) findViewById(R.id.modify_poor_details_seven_inco_three);
        situationSevenIncoFour = (EditText) findViewById(R.id.modify_poor_details_seven_inco_four);
        if (null != listBean || listBean.size() != 0) {
            situationSevenIncoOne.setText((String.valueOf(listBean.get(0).getFpjThdMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getFpjThdMoney());
            situationSevenIncoTwo.setText((String.valueOf(listBean.get(0).getLandRentMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getLandRentMoney());
            situationSevenIncoThree.setText((String.valueOf(listBean.get(0).getInterestDeposit()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getInterestDeposit());
            situationSevenIncoFour.setText((String.valueOf(listBean.get(0).getPropertyOther()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getPropertyOther());
        }
        //精准扶贫收入子项详情
        situationNineInco = (EditText) findViewById(R.id.modify_poor_details_nine_inco_one);
        if (null != listBean || listBean.size() != 0) {
            situationNineInco.setText((String.valueOf(listBean.get(0).getPovertyMoney()).equals("")) ? "￥" + Is : "￥" + listBean.get(0).getAverageIncome());
        }
    }

    private void updatePoorDetails(){
        //收入情况
        sitOne = situationOne.getText().toString().trim();
        sitTwo = situationTwo.getText().toString().trim();
        sitThree = situationThree.getText().toString().trim();
        sitFour = situationFour.getText().toString().trim();
        sitFive = situationFive.getText().toString().trim();
        sitSix = situationSix.getText().toString().trim();
        sitSeven = situationSeven.getText().toString().trim();
        sitEight = situationEight.getText().toString().trim();
        sitNine = situationNine.getText().toString().trim();
        //工资性收入子项详情
        sitIncoOne = situationOneInco.getText().toString().trim();
        //生产经营性收入子项详情
        sitIncoThree = situationThreeInco.getText().toString().trim();
        //转移性收入子项详情
        sitFiveIncoOne = situationFiveIncoOne.getText().toString().trim();
        sitFiveIncoTwo = situationFiveIncoTwo.getText().toString().trim();
        sitFiveIncoThree = situationFiveIncoThree.getText().toString().trim();
        sitFiveIncoFour = situationFiveIncoFour.getText().toString().trim();
        sitFiveIncoFive = situationFiveIncoFive.getText().toString().trim();
        sitFiveIncoSix = situationFiveIncoSix.getText().toString().trim();
        sitFiveIncoSeven = situationFiveIncoSeven.getText().toString().trim();
        sitFiveIncoEight = situationFiveIncoEight.getText().toString().trim();
        sitFiveIncoNine = situationFiveIncoNine.getText().toString().trim();
        sitFiveIncoTen = situationFiveIncoTen.getText().toString().trim();
        sitFiveIncoEleven = situationFiveIncoEleven.getText().toString().trim();
        sitFiveIncoTwelve = situationFiveIncoTwelve.getText().toString().trim();
        sitFiveIncoThirteen = situationFiveIncoThirteen.getText().toString().trim();
        sitFiveIncoFourteen = situationFiveIncoFourteen.getText().toString().trim();
        sitFiveIncoFifteen = situationFiveIncoFifteen.getText().toString().trim();
        sitFiveIncoSixteen = situationFiveIncoSixteen.getText().toString().trim();
        //财产性收入子项详情
        sitSevenIncoOne = situationSevenIncoOne.getText().toString().trim();
        sitSevenIncoTwo = situationSevenIncoTwo.getText().toString().trim();
        sitSevenIncoThree = situationSevenIncoThree.getText().toString().trim();
        sitSevenIncoFour = situationSevenIncoFour.getText().toString().trim();
        //精准扶贫收入子项详情
        sitIncoNine = situationNineInco.getText().toString().trim();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                intent = new Intent(ModifyDetailsEdit.this,PoorDetailsActivity.class);
                intent.putExtra("FRAGMENT_ID",2);
                intent.putExtra("poorHouseId",poorHouseId);
                startActivity(intent);
                finish();
                break;
            case R.id.title_right:
                intent = new Intent(ModifyDetailsEdit.this,PoorDetailsActivity.class);
                intent.putExtra("FRAGMENT_ID",2);
                intent.putExtra("poorHouseId",poorHouseId);
                startActivity(intent);
                finish();
                break;
        }
    }
}
