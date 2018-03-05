package com.yhzhcs.dispatchingsystemjzfp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.adapters.PoorFamilyAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorFamilyBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.Poorlist;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */

public class FamilyFragment extends Fragment {

    private List<Poorlist> poorlists;
    private PoorFamilyAdapter familyAdapter;
    private ListView familyListView;

    private String poorHouseId;

    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_poor_family,container,false);
        Bundle bundle = getArguments();
        poorHouseId = bundle.getString("poorHouseId");
        getData();
        familyListView = (ListView) v.findViewById(R.id.fragment_poor_family_list);
        return v;
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

//    private void intData(){
//        PoorFamilyBean familyBean = null;
//        familyBeanList = new ArrayList<>();
//        for (int i = 0; i < 3; i++){
//            familyBean = new PoorFamilyBean();
//            familyBean.setFamilyNum(i+1);
//            familyBean.setFamilyName("尹秀菊");
//            familyBean.setFamilySex("男");
//            familyBean.setFamilyAge(45);
//            familyBean.setFamilyNexus("户主");
//            familyBean.setFamilyHealth("健康");
//            familyBean.setFamilyPaper("522628199304040404");
//            familyBeanList.add(familyBean);
//        }
//    }
}
