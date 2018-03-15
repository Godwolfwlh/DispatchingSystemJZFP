package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
import com.yhzhcs.dispatchingsystemjzfp.bean.UserInfo;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.ExamineTextWatcher;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

/**
 * Created by Administrator on 2018/3/1.
 */

public class ModifyPassActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView titleImgL;
    private TextView titleName,Account;
    private ImageView titleImgR;
    private EditText oldPass, newPass, okNewPass;
    private Button okBut;

    private UserInfo userInfo;
    private String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,})$";
    private String oldPassStr;
    private String newPassStr;
    private String okNewPassStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        userInfo = (UserInfo) getIntent().getSerializableExtra("USER_INFO");
        LogUtil.v("BDUSERINFO", userInfo.toString());
        intView();
    }

    private void intView() {
        titleImgL = (ImageView) findViewById(R.id.title_left);
        titleName = (TextView) findViewById(R.id.title_name);
        titleImgR = (ImageView) findViewById(R.id.title_right);
        oldPass = (EditText) findViewById(R.id.modify_pass_word_old);
        newPass = (EditText) findViewById(R.id.modify_pass_word_new);
        okNewPass = (EditText) findViewById(R.id.modify_pass_word_new_ok);
        okBut = (Button) findViewById(R.id.modify_pass_ok_but);
        Account = (TextView) findViewById(R.id.modify_pass_account);
        Account.setText(userInfo.getTel());

        titleImgL.setVisibility(View.VISIBLE);
        titleImgL.setImageResource(R.mipmap.icon_back);
        titleName.setText("修改密码");
        titleImgR.setVisibility(View.GONE);
        titleImgL.setOnClickListener(this);
        titleImgR.setOnClickListener(this);
        okBut.setOnClickListener(this);

        oldPassStr = oldPass.getText().toString().trim();
        newPassStr = newPass.getText().toString().trim();
        okNewPassStr = okNewPass.getText().toString().trim();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.modify_pass_ok_but:
//                upPassword();
                finish();
                break;
        }

    }

    private void upPassword(){
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("tel",userInfo.getTel());
        params.addBodyParameter("password",oldPassStr);
        params.addBodyParameter("newpassword",newPassStr);
        httpUtils.send(HttpMethod.POST, Constant.URL_SAVE_PASSWORD, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("MODIFYPASSHTTP","onSuccess"+responseInfo.result.toString());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("MODIFYPASSHTTP","onFailure"+s);
            }
        });
    }

    /**
     * 设置监听
     */
    private void initListener() {
        newPass.addTextChangedListener(
                new ExamineTextWatcher(ExamineTextWatcher.TYPE_PASSWORD, newPass));
        okNewPass.addTextChangedListener(
                new ExamineTextWatcher(ExamineTextWatcher.TYPE_PASSWORD, okNewPass));
    }
}
