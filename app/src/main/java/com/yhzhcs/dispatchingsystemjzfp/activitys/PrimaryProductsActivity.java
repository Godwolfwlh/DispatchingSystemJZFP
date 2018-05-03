package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.adapters.PrimaryProductsAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.Datas;
import com.yhzhcs.dispatchingsystemjzfp.bean.ProBean;
import com.yhzhcs.dispatchingsystemjzfp.onscrolls.ProOnScerllListener;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.NetUtil;
import com.yhzhcs.dispatchingsystemjzfp.view.LoadStatusView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */

public class PrimaryProductsActivity extends AppCompatActivity implements View.OnClickListener, ProOnScerllListener.OnloadDataListener, AdapterView.OnItemClickListener {

    private SearchView mProSearch;
    private ImageView leftImage;
    private TextView Title, RightText;
    private ListView listView;
    private View footer;
    private List<Datas> listData;
    private ProOnScerllListener onScrollListener;
    private PrimaryProductsAdapter proAdapter;
    private LoadStatusView mLoadStatusView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_products_list);
        listView = (ListView) findViewById(R.id.pro_list_view);
        mLoadStatusView = (LoadStatusView) findViewById(R.id.lsv_load_status);
        mLoadStatusView.setOnRefreshListener(new LoadStatusView.OnRefreshListener() {
            @Override
            public void onRefreshListener() {
                //重新加载操作在这里
                getData();
            }
        });
        //将底部加载一个加载更多的布局
        footer = LinearLayout.inflate(this, R.layout.foot_boot, null);

        //初始状态为隐藏
        footer.setVisibility(View.GONE);
        //加入到ListView
        listView.addFooterView(footer);
        //自定义的滚动监听事件
        onScrollListener = new ProOnScerllListener(footer);
        //设置接口回调
        onScrollListener.setOnLoadDataListener(this);
        intView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        if (NetUtil.isConnected(this)) {
            mLoadStatusView.setLoading();
            HttpUtils httpUtils = new HttpUtils();
            RequestParams params = new RequestParams();
            params.addBodyParameter("pageNow", "1");
            params.addBodyParameter("pageSize", "7");
            httpUtils.send(HttpMethod.POST, Constant.URL_PRIMARY_PRODUCTS, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    LogUtil.logJson("PROHTTPS", "onSuccess" + responseInfo.result.toString());
                    String body = responseInfo.result;
                    Gson gson = new Gson();
                    ProBean proBean = gson.fromJson(body, ProBean.class);
                    listData = proBean.getDatas();
                    mLoadStatusView.setHide();
                    showListView(listData);
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    LogUtil.v("PROHTTPS", "onFailure" + s);
                    mLoadStatusView.setFailRefresh();
                }
            });
        } else {
            mLoadStatusView.setNoNet();
        }
    }

    private void intView() {
        leftImage = (ImageView) findViewById(R.id.pro_title_left);
        Title = (TextView) findViewById(R.id.pro_title_name);
        RightText = (TextView) findViewById(R.id.pro_title_right);
        leftImage.setOnClickListener(this);
        RightText.setOnClickListener(this);
        mProSearch = (SearchView) findViewById(R.id.sv_search_text);
        if (mProSearch != null) {
            try {        //--拿到字节码
                Class<?> argClass = mProSearch.getClass();
                //--指定某个私有属性,mSearchPlate是搜索框父布局的名字
                Field ownField = argClass.getDeclaredField("mSearchPlate");
                //--暴力反射,只有暴力反射才能拿到私有属性
                ownField.setAccessible(true);
                View mView = (View) ownField.get(mProSearch);
                //--设置背景
                mView.setBackgroundColor(Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showListView(final List<Datas> data) {
        if (proAdapter == null) {
            proAdapter = new PrimaryProductsAdapter(this, data);
            listView.setAdapter(proAdapter);
            listView.setOnItemClickListener(this);
            mProSearch.setSubmitButtonEnabled(false);
            mProSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    if (TextUtils.isEmpty(s)) {
                        listView.setAdapter(proAdapter);
                    } else {
                        for (int i = 0; i < data.size(); i++) {
                            Datas datas = data.get(i);
                            if (datas.getTitle().equals(s)) {
                                data.clear();
                                data.add(datas);
                                break;
                            }
                        }
                        if (data.size() == 0) {
                            Toast.makeText(PrimaryProductsActivity.this, "查找的商品不在列表中", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PrimaryProductsActivity.this, "查找成功", Toast.LENGTH_SHORT).show();
                            proAdapter = new PrimaryProductsAdapter(PrimaryProductsActivity.this, data);
                            listView.setAdapter(proAdapter);
                        }
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    if (TextUtils.isEmpty(s)) {
                        listView.setAdapter(proAdapter);
                    } else {
                        for (int i = 0; i < data.size(); i++) {
                            Datas datas = data.get(i);
                            if (datas.getTitle().contains(s)) {
                                data.clear();
                                data.add(datas);
                            }
                        }
                        proAdapter = new PrimaryProductsAdapter(PrimaryProductsActivity.this, data);
                        proAdapter.notifyDataSetChanged();
                        listView.setAdapter(proAdapter);
                    }
                    return true;
                }
            });
        } else {
            this.listData.addAll(data);
            proAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pro_title_left:
                finish();
                break;
            case R.id.pro_title_right:
                Intent intent = new Intent(PrimaryProductsActivity.this, ReleaseProductsActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onLoadData(List<Datas> data) {
        showListView(data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Datas info = listData.get(position);
        Intent intent = new Intent(this, ProDetailsActivity.class);
        Bundle bundle = new Bundle();
        String ID = info.getId();
        String MassageType = info.getMassageType();
        bundle.putString("ID", ID);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
