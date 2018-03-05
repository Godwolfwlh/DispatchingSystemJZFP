package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.ProDetailsBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.Produce;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.view.MyWebView;
import com.yhzhcs.dispatchingsystemjzfp.view.PullUpToLoadMore;

/**
 * Created by Administrator on 2018/2/27.
 */

public class ProDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;
    private PullUpToLoadMore ptlm;
    private Button btn;
    private MyWebView proContent;

    private String ID;
    private Produce listPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_products_details);
        Bundle bundle = getIntent().getExtras();
        ID = bundle.getString("ID");
        LogUtil.v("PRODETAILSBUNDLE", "id==" + ID);
        getData();
        intView();
    }

    private void getData() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", ID);
        httpUtils.send(HttpMethod.POST, Constant.URL_PRIMARY_PRODUCTS_DETAILS, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String body = responseInfo.result;
                LogUtil.v("PRODETAILSHTTPS", "onSuccess=="+ "\n" + body.toString());
                Gson gson = new Gson();
                ProDetailsBean prodBean = gson.fromJson(body,ProDetailsBean.class);
                listPro = prodBean.getProduce();
                showHtmlData(listPro.getContent());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("PRODETAILSHTTPS", "onFailure" + s);
            }
        });
    }

    private void intView() {
        titleImgL = (ImageView) findViewById(R.id.title_left);
        titleName = (TextView) findViewById(R.id.title_name);
        titleImgR = (ImageView) findViewById(R.id.title_right);

        titleImgL.setVisibility(View.VISIBLE);
        titleImgL.setImageResource(R.mipmap.icon_back);
        titleName.setText("农产品详情");
        titleImgR.setImageResource(R.mipmap.icon_share);
        titleImgL.setOnClickListener(this);
        titleImgR.setOnClickListener(this);

        ptlm = (PullUpToLoadMore) findViewById(R.id.ptlm);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        proContent = (MyWebView) findViewById(R.id.pro_details_content);
        proContent.setOnScrollChangedCallback(new MyWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                if (dx == -1){
                    ptlm.scrollToTop();
                }
            }
        });

    }

    private void showHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        proContent.loadData("<html>" + head + "<body>" + bodyHTML + "</body></html>", "text/html; charset=UTF-8", null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                ptlm.scrollToTop();
                break;
            case R.id.title_left:
                finish();
                break;
        }
    }
}
