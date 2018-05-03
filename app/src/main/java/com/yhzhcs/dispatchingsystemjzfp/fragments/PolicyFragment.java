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
import com.yhzhcs.dispatchingsystemjzfp.adapters.PolicyAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.InformationBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.Zclist;
import com.yhzhcs.dispatchingsystemjzfp.onscrolls.PolicyOnScerllListenner;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.NetUtil;
import com.yhzhcs.dispatchingsystemjzfp.view.LoadStatusView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class PolicyFragment extends Fragment implements PolicyOnScerllListenner.OnloadDataListener, AdapterView.OnItemClickListener {

    private View v;
    private List<Zclist> listZclist;

    //加载更多布局
    private View footer;

    private ListView policyListView;

    private PolicyAdapter adapter;
    private LoadStatusView mLoadStatusView;
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.information_fragment_policy, container, false);
        inView();
        getDatas();
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
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

    private void getDatas() {
        if (NetUtil.isConnected(getActivity())) {
            mLoadStatusView.setLoading();
            HttpUtils httpUtils = new HttpUtils();
            httpUtils.send(HttpMethod.GET, Constant.URL_NEWS_INFORMATION, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    LogUtil.i("RequestCallBack", "onSuccess==>>>" + responseInfo.result.toString());
                    String body = responseInfo.result;
                    Gson gson = new Gson();
                    InformationBean informationBean = gson.fromJson(body, InformationBean.class);
                    listZclist = informationBean.getZcList();
                    mLoadStatusView.setHide();
                    showListView(listZclist);
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    LogUtil.i("RequestCallBack", "onFailure==>>>" + s.toString() + "===========" + e.toString());
                    mLoadStatusView.setFailRefresh();
                }
            });
        } else {
            mLoadStatusView.setNoNet();
        }
    }

    private void inView() {
        policyListView = (ListView) v.findViewById(R.id.policy_list_view);
        mLoadStatusView = (LoadStatusView) v.findViewById(R.id.lsv_load_status);
        mLoadStatusView.setOnRefreshListener(new LoadStatusView.OnRefreshListener() {
            @Override
            public void onRefreshListener() {
                //重新加载操作在这里
                getDatas();
            }
        });
        footer = LinearLayout.inflate(getActivity(), R.layout.foot_boot, null);
        footer.setVisibility(View.GONE);
        policyListView.addFooterView(footer);

        PolicyOnScerllListenner onScerllListenner = new PolicyOnScerllListenner(footer);
        onScerllListenner.setOnLoadDataListener(this);
        policyListView.setOnScrollListener(onScerllListenner);
    }

    /**
     * 将数据加载到ListView上
     *
     * @param data
     */
    private void showListView(List<Zclist> data) {

        if (adapter == null) {
            adapter = new PolicyAdapter(getActivity(), data);
            policyListView.setAdapter(adapter);
            policyListView.setOnItemClickListener(this);
        } else {
            listZclist.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadData(List<Zclist> data) {
        showListView(data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Zclist info = listZclist.get(position);
        Intent intent = new Intent(getActivity(), HotDetailsActivity.class);
        Bundle bundle = new Bundle();
        String ID = info.getId();
        String MassageType = info.getMassageType();
        bundle.putString("ID", ID);
        bundle.putString("MassageType", MassageType);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
