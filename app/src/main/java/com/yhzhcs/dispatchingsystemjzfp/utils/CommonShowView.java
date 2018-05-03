package com.yhzhcs.dispatchingsystemjzfp.utils;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;

/**
 * Created by Administrator on 2018/2/5.
 */

//下面这个类是简单地封装用于无数据及加载错误的一个页面。
public class CommonShowView {

    private Context mContext;// 上下文
    private ViewGroup mEmptyOrErrorView;// 页面加载无数据或加载错误时显示
    private ViewGroup mContentView;// 加载成功时显示的内容
    private ViewGroup mParentView;// 父布局viewGroup
    private LayoutInflater mInflater;
    private TextView no_net;
    private TextView load_btn;
    private boolean mViewsAdded;
    private ViewGroup.LayoutParams mLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    public final static int TYPE_EMPTY = 1;// 数据为空
    public final static int TYPE_ERROR = 2;// 加载数据失败
    public final static int TYPE_CONTENT = 3;// 直接显示内容
    private int mType = TYPE_EMPTY;// 数据类型，默认是无数据

    /**
     * 构造方法，传入上下文及内容GroupView
     */
    public CommonShowView(Context context, ViewGroup mContentView) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.mContentView = mContentView;
        mParentView = (ViewGroup) mContentView.getParent();
        initViews();
    }

    @SuppressLint("InflateParams")
    private void initViews() {
        mEmptyOrErrorView = (ViewGroup) mInflater.inflate(R.layout.network_anomaly, null);
        no_net = (TextView) mEmptyOrErrorView.findViewById(R.id.no_net);
        load_btn = (TextView) mEmptyOrErrorView.findViewById(R.id.load_btn);
        if (!mViewsAdded) {
            mViewsAdded = true;
            mParentView.addView(mEmptyOrErrorView, mLayoutParams);
        }

//        load_btn.setOnClickListener(new View.OnClickListener() {// 检查网络，进行网络设置
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = null;
//                // 判断手机系统的版本 即API大于10 就是3.0或以上版本及魅族手机
//                if (android.os.Build.VERSION.SDK_INT > 10 && !android.os.Build.MANUFACTURER.equals("Meizu")) {
//                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
//                } else if (android.os.Build.VERSION.SDK_INT > 17 && android.os.Build.MANUFACTURER.equals("Meizu")) {
//                    intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
//                } else {
//                    if (android.os.Build.VERSION.SDK_INT >= 11) {
//                        //Honeycomb
//                        intent.setClassName("com.android.settings", "com.android.settings.Settings$WifiSettingsActivity");
//                    } else {
//                        //other versions
//                        intent.setClassName("com.android.settings"
//                                , "com.android.settings.wifi.WifiSettings");
//                    }
//                }
//                mContext.startActivity(intent);
//
//            }
//        });
    }

    public ViewGroup getEmptyOrErrorView() {
        return mEmptyOrErrorView;
    }

    /**
     * 设置无数据或加载错误view
     *
     * @description：
     */
    public void setEmptyOrErrorView(ViewGroup emptyOrErrorView) {
        this.mParentView.removeView(this.mEmptyOrErrorView);
        this.mParentView.addView(emptyOrErrorView, mLayoutParams);
        this.mEmptyOrErrorView = emptyOrErrorView;
    }

    /**
     * 设置无数据或加载错误view
     *
     * @description：
     */
    public void setEmptyOrErrorView(int res) {
        ViewGroup vg = (ViewGroup) mInflater.inflate(res, null);
        this.mParentView.removeView(this.mEmptyOrErrorView);
        this.mParentView.addView(vg, mLayoutParams);
        this.mEmptyOrErrorView = vg;
    }

    /**
     * 获取内容view
     *
     * @description：
     */
    public ViewGroup getContextView() {
        return mContentView;
    }

    /**
     * 方法概述：获取内容View的父布局
     */
    public ViewGroup getRootView() {
        return mParentView;
    }

    /**
     * 方法概述：设置展示内容的视图
     */
    public void setContextView(ViewGroup contentView) {
        this.mType = TYPE_CONTENT;
        showByType(mType);
    }

    /**
     * 根据类型进行对应展示
     */
    public void showByType(int mType) {
        hideAllViews();
        if (mContentView != null) {
            switch (mType) {
                case TYPE_EMPTY:

                    if (mEmptyOrErrorView != null) {
                        mEmptyOrErrorView.setVisibility(View.VISIBLE);
                        no_net.setText("网络加载中...");
                        load_btn.setVisibility(View.GONE);
                    }
                    break;
                case TYPE_ERROR:
                    if (mEmptyOrErrorView != null) {
                        mEmptyOrErrorView.setVisibility(View.VISIBLE);
                        no_net.setText("网络加载失败...");
                        load_btn.setVisibility(View.VISIBLE);
                    }
                    break;
                case TYPE_CONTENT:
                    if (mContentView != null) {
                        mContentView.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 方法概述：将所有的子View隐藏起来
     */
    private void hideAllViews() {
        if (mParentView != null) {
            if (mParentView.getChildCount() > 0) {
                for (int i = 0; i < mParentView.getChildCount(); i++) {
                    View childView = mParentView.getChildAt(i);
                    if (childView != null) {
                        childView.setVisibility(View.GONE);
                    }
                }
            }
        }

    }
}
