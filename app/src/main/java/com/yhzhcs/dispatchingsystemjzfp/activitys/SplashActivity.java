package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.manages.UserManage;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.Random;

/**
 * 启动页，app刚打开时的activity
 */
public class SplashActivity extends AppCompatActivity {

    private static final int GO_HOME = 0;//去主页
    private static final int GO_LOGIN = 1;//去登录页

    private PercentRelativeLayout percentRelativeLayout;
    private TextView SplaText;
    private Animation animation;
    private int resids[];          //用于加载图片

    private MyCountDownTimer mc;
    /**
     * 跳转判断
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME://去主页
                    percentRelativeLayout.startAnimation(animation);  //给图片设置放大的动画
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case GO_LOGIN://去登录页
                    percentRelativeLayout.startAnimation(animation);  //给图片设置放大的动画
                    Intent intent2 = new Intent(SplashActivity.this, EnterActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        animation = AnimationUtils.loadAnimation(this, R.anim.enlarge);
        percentRelativeLayout = (PercentRelativeLayout) findViewById(R.id.slap_image);
        SplaText = (TextView) findViewById(R.id.slap_text);
        mc = new MyCountDownTimer(5000, 1000);
        mc.start();

        TypedArray ar = getResources().obtainTypedArray(R.array.imgArray); //获取图片数组
        int len = ar.length();  //获取数组的长度
        resids = new int[len];   //初始化加载图片的数组
        for (int i = 0; i < len; i++)
            resids[i] = ar.getResourceId(i, 0);   //for循环吧图片读取出来
        Random rand = new Random();          //初始化随机数
        int pos = rand.nextInt(resids.length);   //随机获取出小于数组长度的数
        percentRelativeLayout.setBackgroundResource(resids[pos]);   //把图片显示出来

        if (UserManage.getInstance().hasUserInfo(this))//自动登录判断，SharePrefences中有数据，则跳转到主页，没数据则跳转到登录页
        {
            mHandler.sendEmptyMessageDelayed(GO_HOME, 5000);
        } else {
            mHandler.sendEmptyMessageAtTime(GO_LOGIN, 5000);
        }
    }

    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *                          <p>
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         *                          <p>
         *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onFinish() {
            SplaText.setText(" 正在跳转 ");
        }

        public void onTick(long millisUntilFinished) {
            SplaText.setText("跳转中( " + millisUntilFinished / 1000 + " )");
        }

    }

}
