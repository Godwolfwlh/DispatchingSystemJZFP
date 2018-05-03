package com.yhzhcs.dispatchingsystemjzfp.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.adapters.TaskAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.TaskBeans;
import com.yhzhcs.dispatchingsystemjzfp.bean.Tasklists;
import com.yhzhcs.dispatchingsystemjzfp.onscrolls.TaskOnScerllListener;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.NetUtil;
import com.yhzhcs.dispatchingsystemjzfp.view.LoadStatusView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 * <p>
 * author Luhuai Wu
 */

public class TaskFragment extends Fragment implements TaskOnScerllListener.OnloadDataListener {

    private View v;

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    private List<Tasklists> taskBeanList;

    //加载更多布局
    private View footer;

    private ListView taskListView;

    private TaskAdapter adapter;

    private SharedPreferences sp;
    private String missionId;
    private int userId;

    private LoadStatusView mLoadStatusView;
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.task_fragment, null);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        missionId = sp.getString("MISSION_ID", "");
        userId = sp.getInt("USER_ID", 0);
        inView();
        getData();
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

    private void getData() {
        if (NetUtil.isConnected(getActivity())) {
            mLoadStatusView.setLoading();
            HttpUtils httpUtils = new HttpUtils();
            RequestParams params = new RequestParams();
            params.addBodyParameter("missionId", missionId);
            params.addBodyParameter("userId", String.valueOf(userId));
            httpUtils.send(HttpMethod.POST, Constant.URL_TASK, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    LogUtil.v("TASK_HTTP", "onSuccess" + responseInfo.result.toString());
                    String body = responseInfo.result;
                    if (!body.equals("")) {
                        Gson gson = new Gson();
                        TaskBeans taskBeans = gson.fromJson(body, TaskBeans.class);
                        taskBeanList = taskBeans.getTaskLists();
                        mLoadStatusView.setHide();
                        showListView(taskBeanList);
                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    LogUtil.v("TASK_HTTP", "onFailure" + s);
                    mLoadStatusView.setFailRefresh();
                }
            });
        } else {
            mLoadStatusView.setNoNet();
        }
    }

    private void inView() {
        titleImgL = (ImageView) v.findViewById(R.id.title_left);
        titleName = (TextView) v.findViewById(R.id.title_name);
        titleImgR = (ImageView) v.findViewById(R.id.title_right);
        taskListView = (ListView) v.findViewById(R.id.task_list_view);

        titleName.setText("任  务");
        titleImgR.setVisibility(View.GONE);
        mLoadStatusView = (LoadStatusView) v.findViewById(R.id.lsv_load_status);
        mLoadStatusView.setOnRefreshListener(new LoadStatusView.OnRefreshListener() {
            @Override
            public void onRefreshListener() {
                //重新加载操作在这里
                getData();
            }
        });

        footer = LinearLayout.inflate(getActivity(), R.layout.foot_boot, null);
        footer.setVisibility(View.GONE);
        taskListView.addFooterView(footer);


        TaskOnScerllListener onScerllListenner = new TaskOnScerllListener(footer, missionId);
        onScerllListenner.setOnLoadDataListener(this);
        taskListView.setOnScrollListener(onScerllListenner);
    }

    /**
     * 将数据加载到ListView上
     *
     * @param data
     */
    private void showListView(List<Tasklists> data) {

        if (adapter == null) {
            adapter = new TaskAdapter(getActivity(), data);
            taskListView.setAdapter(adapter);
        } else {
            taskBeanList.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadData(List<Tasklists> data) {
        showListView(data);
    }
}
