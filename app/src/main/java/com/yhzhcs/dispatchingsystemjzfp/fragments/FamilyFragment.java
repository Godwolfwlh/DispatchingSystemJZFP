package com.yhzhcs.dispatchingsystemjzfp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
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
import com.yhzhcs.dispatchingsystemjzfp.activitys.ModifyPoorFamily;
import com.yhzhcs.dispatchingsystemjzfp.adapters.PoorFamilyAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorFamilyBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.Poorlist;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.ToastUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */

public class FamilyFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{

    private List<Poorlist> poorlists;
    private PoorFamilyAdapter familyAdapter;
    private ListView familyListView;
    private TextView headEdit;

    private String poorHouseId;

    private View v;

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;

    private boolean isGetData = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_poor_family,container,false);
        Bundle bundle = getArguments();
        poorHouseId = bundle.getString("poorHouseId");
        familyListView = (ListView) v.findViewById(R.id.fragment_poor_family_list);
        headEdit = (TextView) v.findViewById(R.id.poor_head_edit);
        headEdit.setOnClickListener(this);
        return v;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            isGetData = true;
            //   这里可以做网络请求或者需要的数据刷新操作
            getData();
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isGetData) {
            //   这里可以做网络请求或者需要的数据刷新操作
            getData();
            isGetData = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
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

    private void getData(){
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("poorHouseId",poorHouseId);
        httpUtils.send(HttpMethod.POST, Constant.URL_POOR_FAMILY, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("FamilyHttp","onSuccess"+responseInfo.result.toString());
                String body = responseInfo.result;
                Gson gson = new Gson();
                PoorFamilyBean familyBean = gson.fromJson(body,PoorFamilyBean.class);
                poorlists = familyBean.getPoorList();
                familyAdapter = new PoorFamilyAdapter(getActivity(),poorlists);
                familyListView.setAdapter(familyAdapter);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("FamilyHttp","onFailure"+s);
            }
        });
    }

    @Override
    public void onClick(View view) {
        ToastUtil.showInfo(getActivity(),"请选择需要编辑的家庭成员");
        familyListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Poorlist poorlist = poorlists.get(i);
        Intent intent = new Intent(getActivity(), ModifyPoorFamily.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("POOR_LIST",poorlist);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
