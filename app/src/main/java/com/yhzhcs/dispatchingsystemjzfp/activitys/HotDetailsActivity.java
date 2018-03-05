package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.umeng.socialize.UMShareAPI;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.HotDetailsBean;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/23.
 */

public class HotDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    private String ID;
    private String MassageType;
    private HotDetailsBean hotDetailsBean;
    private TextView hotTitle, pageView, hotTime;
    private WebView hotContent;

    private final int TYPE_ONE = 0;

    private final int TYPE_TWO = 1;

    private final int TYPE_THREE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_spot_details);
        Bundle bundle = getIntent().getExtras();
        ID = bundle.getString("ID");
        MassageType = bundle.getString("MassageType");
        LogUtil.v("HOTDETAILSBUNDLE", "id==" + ID + "<<<<>>>>MassageType==" + MassageType);
        getData();
        intView();
    }

    public void getData() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("massageType", MassageType);
        params.addBodyParameter("id", ID);
        httpUtils.send(HttpMethod.POST, Constant.URL_NEWS_DETAILS, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String body = responseInfo.result;
                Gson gson = new Gson();
                hotDetailsBean = gson.fromJson(body, HotDetailsBean.class);
                LogUtil.v("HOTDETAILSHTTP", "onSuccess========>>" + hotDetailsBean.toString());
                Date dateTime = new Date(hotDetailsBean.getCreatedDate().getTime());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String HotTime = dateFormat.format(dateTime);
                showHtmlData(hotDetailsBean.getTitle(), 4000, HotTime, hotDetailsBean.getContent());

            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("HOTDETAILSHTTP", "onFailure========>>" + s);
            }
        });

    }

    public void intView() {
        titleImgL = (ImageView) findViewById(R.id.title_left);
        titleName = (TextView) findViewById(R.id.title_name);
        titleImgR = (ImageView) findViewById(R.id.title_right);

        hotTitle = (TextView) findViewById(R.id.hot_details_title);
        pageView = (TextView) findViewById(R.id.hot_details_page_view);
        hotTime = (TextView) findViewById(R.id.hot_details_time);
        hotContent = (WebView) findViewById(R.id.hot_details_content);

        titleImgL.setVisibility(View.VISIBLE);
        titleImgL.setImageResource(R.mipmap.icon_back);
        titleImgR.setImageResource(R.mipmap.icon_share);
        titleImgL.setOnClickListener(this);
        titleImgR.setOnClickListener(this);
        int type = Integer.parseInt(MassageType);
        if (type == TYPE_ONE){
            titleName.setText("动态详情");
        }
        else if (type == TYPE_TWO){
            titleName.setText("政策详情");
        }
        else if(type == TYPE_THREE){
            titleName.setText("农产品详情");
        }

    }

    private void showHtmlData(String Title, int page, String time, String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
//        "<script type='text/javascript'>alert(111)</script>"+
                hotTitle.setText(Title);
        pageView.setText(page+"次");
        hotTime.setText(time);
        hotContent.loadData("<html>" + head + "<body>" + bodyHTML + "</body></html>", "text/html; charset=UTF-8", null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_left:
                finish();
                break;
        }

    }

    //QQ与新浪微博分享的回调
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }

}
