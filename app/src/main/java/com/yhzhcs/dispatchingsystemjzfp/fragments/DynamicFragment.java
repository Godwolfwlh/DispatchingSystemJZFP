package com.yhzhcs.dispatchingsystemjzfp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.activitys.HotDetailsActivity;
import com.yhzhcs.dispatchingsystemjzfp.adapters.DynamicAdapter;
import com.yhzhcs.dispatchingsystemjzfp.adapters.PolicyAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.Dtlist;
import com.yhzhcs.dispatchingsystemjzfp.bean.InformationBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.Zclist;
import com.yhzhcs.dispatchingsystemjzfp.onscrolls.DynamicOnScerllListener;
import com.yhzhcs.dispatchingsystemjzfp.onscrolls.PolicyOnScerllListenner;
import com.yhzhcs.dispatchingsystemjzfp.utils.CommonShowView;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class DynamicFragment extends Fragment implements DynamicOnScerllListener.OnloadDataListener, AdapterView.OnItemClickListener {

    private View v;
    private List<Dtlist> dtlists;

    //加载更多布局
    private View footer;

    private ListView dynListView;

    private DynamicAdapter adapter;
    private CommonShowView mShowView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.information_fragment_dynamic,container,false);
        inView();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDatas();
    }

    private void getDatas() {
        HttpUtils httpUtils = new HttpUtils();
        LogUtil.v("ssssssssss", "==================getDatas=====================");
        httpUtils.send(HttpMethod.GET, Constant.URL_NEWS_INFORMATION, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.i("RequestCallBack", "onSuccess==>>>" + responseInfo.result.toString());
                String body = responseInfo.result;
                Gson gson = new Gson();
                InformationBean informationBean = gson.fromJson(body,InformationBean.class);
                dtlists = informationBean.getDtList();
                showListView(dtlists);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.i("RequestCallBack", "onFailure==>>>" + s.toString()+"==========="+e.toString());
                mShowView.showByType(CommonShowView.TYPE_ERROR);
            }
        });
    }

    private void inView(){
        dynListView = (ListView) v.findViewById(R.id.dynamic_list_view);
        mShowView = new CommonShowView(getActivity(), dynListView);
        mShowView.showByType(CommonShowView.TYPE_EMPTY);
        footer = LinearLayout.inflate(getActivity(), R.layout.foot_boot, null);
        footer.setVisibility(View.GONE);
        dynListView.addFooterView(footer);

        DynamicOnScerllListener onScerllListenner = new DynamicOnScerllListener(footer);
        onScerllListenner.setOnLoadDataListener(this);
        dynListView.setOnScrollListener(onScerllListenner);
    }

    /**
     * 将数据加载到ListView上
     *
     * @param data
     */
    private void showListView(List<Dtlist> data){

        if (adapter == null){
            adapter = new DynamicAdapter(getActivity(),data);
            dynListView.setAdapter(adapter);
            dynListView.setOnItemClickListener(this);
            mShowView.showByType(CommonShowView.TYPE_CONTENT);
        }else {
            dtlists.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadData(List<Dtlist> data) {
        showListView(data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Dtlist info = dtlists.get(position);
        Intent intent = new Intent(getActivity(), HotDetailsActivity.class);
        Bundle bundle = new Bundle();
        String ID = info.getId();
        String MassageType = info.getMassageType();
        bundle.putString("ID",ID);
        bundle.putString("MassageType",MassageType);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
