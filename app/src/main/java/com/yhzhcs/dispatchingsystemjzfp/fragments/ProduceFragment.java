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
import com.yhzhcs.dispatchingsystemjzfp.adapters.ProduceAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.Dtlist;
import com.yhzhcs.dispatchingsystemjzfp.bean.InformationBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.Ncplist;
import com.yhzhcs.dispatchingsystemjzfp.onscrolls.ProduceOnScerllListenner;
import com.yhzhcs.dispatchingsystemjzfp.utils.CommonShowView;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class ProduceFragment extends Fragment implements ProduceOnScerllListenner.OnloadDataListener, AdapterView.OnItemClickListener {

    private View v;
    private List<Ncplist> listNcplist;

    //加载更多布局
    private View footer;

    private ListView proListView;

    private ProduceAdapter adapter;
    private CommonShowView mShowView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.information_fragment_produce,container,false);
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
                listNcplist = informationBean.getNcpList();
                showListView(listNcplist);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.i("RequestCallBack", "onFailure==>>>" + s.toString()+"==========="+e.toString());
                mShowView.showByType(CommonShowView.TYPE_ERROR);
            }
        });
    }

    private void inView(){

        proListView = (ListView) v.findViewById(R.id.produce_list_view);
        mShowView = new CommonShowView(getActivity(), proListView);
        mShowView.showByType(CommonShowView.TYPE_EMPTY);
        footer = LinearLayout.inflate(getActivity(), R.layout.foot_boot, null);
        footer.setVisibility(View.GONE);
        proListView.addFooterView(footer);

        ProduceOnScerllListenner onScerllListenner = new ProduceOnScerllListenner(footer);
        onScerllListenner.setOnLoadDataListener(this);
        proListView.setOnScrollListener(onScerllListenner);
    }

    /**
     * 将数据加载到ListView上
     *
     * @param data
     */
    private void showListView(List<Ncplist> data){

        if (adapter == null){
            adapter = new ProduceAdapter(getActivity(),data);
            proListView.setAdapter(adapter);
            proListView.setOnItemClickListener(this);
            mShowView.showByType(CommonShowView.TYPE_CONTENT);
        }else {
            listNcplist.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadData(List<Ncplist> data) {
        showListView(data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Ncplist info = listNcplist.get(position);
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
