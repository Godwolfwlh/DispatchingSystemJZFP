package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.UserLoginBean;
import com.yhzhcs.dispatchingsystemjzfp.manages.UserManage;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.MD5Util;
import com.yhzhcs.dispatchingsystemjzfp.utils.ToastUtil;

import java.util.List;


public class EnterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editPerson, editCode;
    private Button btn;
    private boolean autoLogin = false;
    public static String username;
    private String password;
    private boolean progressShow;
    private ProgressDialog pd;
    private UserLoginBean userLoginBean;
    private List<UserLoginBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_enter_activity);
        init();
    }

    private void init() {
        btn = (Button) findViewById(R.id.bn_common_login);
        btn.setOnClickListener(this);
        editCode = (EditText) findViewById(R.id.et_password);
        editPerson = (EditText) findViewById(R.id.et_username);
        pd = new ProgressDialog(EnterActivity.this);  //初始化等待动画
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bn_common_login:  //登录按钮
                progressShow = true;
                /**
                 * 设置监听
                 * */
                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressShow = false;   //设置Boolean值为false
                    }
                });
                pd.setMessage("正在登录....");  //等待动画的标题
                pd.show();  //显示等待动画
                login(v);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (autoLogin) {
            return;
        }
    }

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {

        username = editPerson.getText().toString().trim(); //去除空格，获取用户名
        password = editCode.getText().toString().trim();  //去除空格，获取密码

        if (TextUtils.isEmpty(username)) { //判断用户名是不是为空
            pd.dismiss();    //等待条消失
            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {  //判断密码是不是空
            pd.dismiss();    //等待条消失
            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        LogUtil.v("hhhhhhhhhhhhhh", "=========getData============");
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("username", username);
        params.addBodyParameter("password", password);
        httpUtils.send(HttpMethod.POST, Constant.URL_USER_LOGIN, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("USERHTTP", "onSuccess=========>>>" + responseInfo.result.toString());
                Gson gson = new Gson();
                userLoginBean = new UserLoginBean();
                userLoginBean = gson.fromJson(responseInfo.result, UserLoginBean.class);
                if (userLoginBean.getUserId() == 0) {
                    new Thread(new Runnable() {
                        public void run() {
                            //在此处睡眠两秒
                            try {
                                Thread.sleep(2000);  //在此处睡眠两秒
                            } catch (InterruptedException e) {
                            }

                            Message message = new Message();
                            message.what = 0;
                            mHandler.sendMessage(message);

                        }
                    }).start();  //开始线程
                } else {
                    UserManage.getInstance().saveUserInfo(EnterActivity.this, userLoginBean.getUserId(), userLoginBean.getMissionId(), MD5Util.getMD5Str(username), MD5Util.getMD5Str(password));
                    LogUtil.v("userLoginBean", "onSuccess=========>>>" + userLoginBean.toString());

                    new Thread(new Runnable() {
                        public void run() {
                            //在此处睡眠两秒
                            try {
                                Thread.sleep(2000);  //在此处睡眠两秒
                            } catch (InterruptedException e) {
                            }

                            Message message = new Message();
                            message.what = 1;
                            mHandler.sendMessage(message);

                        }
                    }).start();  //开始线程
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("USERHTTP", "onFailure==========>>>" + s.toString());

                new Thread(new Runnable() {
                    public void run() {
                        //在此处睡眠两秒
                        try {
                            Thread.sleep(2000);  //在此处睡眠两秒
                        } catch (InterruptedException e) {
                        }

                        Message message = new Message();
                        message.what = 2;
                        mHandler.sendMessage(message);

                    }
                }).start();  //开始线程
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
            }
        });


    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    /**
                     * 两秒之后
                     * */
                    pd.dismiss();    //等待条消失
                    ToastUtil.showInfo(EnterActivity.this,"账户/密码错误,请从新输入...");
                    break;
                case 1:
                    /**
                     * 两秒之后
                     * */
                    pd.dismiss();    //等待条消失
                    Intent intent = new Intent(EnterActivity.this, MainActivity.class);  //进入主界面
                    startActivity(intent);  //开始跳转
                    finish();  //finish掉此界面
                    break;
                case 2:
                    /**
                     * 两秒之后
                     * */
                    pd.dismiss();    //等待条消失
                    ToastUtil.showInfo(EnterActivity.this, "网络异常，请检查您的网络！");
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
