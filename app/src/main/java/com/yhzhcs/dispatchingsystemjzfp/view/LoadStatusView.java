package com.yhzhcs.dispatchingsystemjzfp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.utils.NetUtil;

public class LoadStatusView extends FrameLayout implements View.OnClickListener {
    public static int STATUS_LOADING = 1;
    public static int STATUS_NO_NET = 2;
    public static int STATUS_FAIL_REFRESH = 3;
    public static int STATUS_HIDE = 4;

    private ImageView ivLoadStatus;
    private TextView tvLoadStatus;
    private AnimationDrawable loadAnimationDrawable;

    public LoadStatusView(Context context) {
        this(context, null);
    }

    public LoadStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View.inflate(getContext(), R.layout.load_status, this);
        setClickable(true);//这个是为了不让点击穿透到下层界面去，以免下层的控件看不见却莫名其妙的响应了
        ivLoadStatus = (ImageView) findViewById(R.id.iv_load_anim);
        tvLoadStatus = (TextView) findViewById(R.id.tv_load_text);
        loadAnimationDrawable = (AnimationDrawable) ivLoadStatus.getBackground();
        tvLoadStatus.setOnClickListener(this);
    }

    public void setLoading(){
        setVisibility(View.VISIBLE);
        ivLoadStatus.setVisibility(VISIBLE);
        tvLoadStatus.setVisibility(VISIBLE);
        loadAnimationDrawable.start();
        tvLoadStatus.setText(getResources().getString(R.string.load_status_loading));
        tvLoadStatus.setClickable(false);
        tvLoadStatus.setEnabled(false);
    }

    public void setNoNet(){
        setVisibility(View.VISIBLE);
        ivLoadStatus.setVisibility(GONE);
        tvLoadStatus.setVisibility(VISIBLE);
        tvLoadStatus.setText(getResources().getString(R.string.load_status_no_net));
        tvLoadStatus.setClickable(true);
        tvLoadStatus.setEnabled(true);
    }

    public void setFailRefresh(){
        setVisibility(View.VISIBLE);
        ivLoadStatus.setVisibility(GONE);
        tvLoadStatus.setVisibility(VISIBLE);
        tvLoadStatus.setText(getResources().getString(R.string.load_status_fail_refresh));
        tvLoadStatus.setClickable(true);
        tvLoadStatus.setEnabled(true);
    }

    public void setHide(){
        setVisibility(View.GONE);
        tvLoadStatus.setClickable(false);
        tvLoadStatus.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        if(v == tvLoadStatus){
            if(!NetUtil.isConnected(getContext())){
                openSettings();
            }else{
                if(onRefreshListener != null){
                    onRefreshListener.onRefreshListener();
                }
            }
        }
    }

    /**
     * 设置点击刷新监听
     * @param listener listener
     */
    public void setOnRefreshListener(OnRefreshListener listener) {
        onRefreshListener = listener;
    }

    /**
     * 打开设置
     */
    private void openSettings(){
        Intent intent = new Intent();
        intent.setAction(android.provider.Settings.ACTION_SETTINGS);
        getContext().startActivity(intent);
    }

    OnRefreshListener onRefreshListener = null;

    public interface OnRefreshListener{
        void onRefreshListener();
    }
}