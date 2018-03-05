package com.yhzhcs.dispatchingsystemjzfp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.activitys.ForumActivity;
import com.yhzhcs.dispatchingsystemjzfp.activitys.HotDetailsActivity;
import com.yhzhcs.dispatchingsystemjzfp.activitys.PoorActivity;
import com.yhzhcs.dispatchingsystemjzfp.activitys.PrimaryProductsActivity;
import com.yhzhcs.dispatchingsystemjzfp.adapters.MainListAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.Datas;
import com.yhzhcs.dispatchingsystemjzfp.bean.MainData;
import com.yhzhcs.dispatchingsystemjzfp.onscrolls.MainOnScerllListener;
import com.yhzhcs.dispatchingsystemjzfp.view.BottomScrollView;
import com.yhzhcs.dispatchingsystemjzfp.utils.CommonShowView;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/1/12.
 * <p>
 * author Luhuai Wu
 */

public class MainFragment extends Fragment implements MainOnScerllListener.OnloadDataListener, AdapterView.OnItemClickListener, View.OnClickListener, BottomScrollView.OnScrollToBottomListener {
    //轮播图布局容器
    private FrameLayout fl;
    //轮播图控件
    private ViewPager mViewPaper;
    //轮播图图片集合
    private List<ImageView> images;
    //存放轮播图显示图片
    private int[] imageIds = new int[]{R.mipmap.img_banner, R.mipmap.img_banner1, R.mipmap.img_banner2, R.mipmap.img_banner3};
    //存放图片的标题
    private String[] titles = new String[]{"精准扶贫1", "精准扶贫2", "精准扶贫3", "精准扶贫4"};
    //轮播图显示文字控件
    private TextView title;
    //轮播图点集合
    private List<View> dots;
    //记录当前跳转的轮播图ID
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;

    //轮播图适配器
    private MViewPagerAdapter mViewPagerAdapter;
    //执行定时任务
    private ScheduledExecutorService scheduledExecutorService;

    private List<Datas> listData;
    private ListView mainListView;
    private MainListAdapter adapter;
    private View v;
    //按钮布局
    private ImageView poor, produce, forum;
    //加载更多布局
    private View footer;
    private RequestParams params;
    private HttpUtils httpUtils;
    private MainOnScerllListener onScrollListener;
    private BottomScrollView sv;
    private CommonShowView mShowView;
    private boolean isSvToBottom = false;
    private float mLastX;
    private float mLastY;

    /**
     * listview竖向滑动的阈值
     */
    private static final int THRESHOLD_Y_LIST_VIEW = 20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_main_list, null);
        sv = (BottomScrollView) v.findViewById(R.id.act_solution_4_sv);
        sv.smoothScrollTo(0, 0);
        sv.setScrollToBottomListener(this);

        //查到View控件
        mainListView = (ListView) v.findViewById(R.id.main_list_view);
        mShowView = new CommonShowView(getActivity(), mainListView);
        mShowView.showByType(CommonShowView.TYPE_EMPTY);
        //将底部加载一个加载更多的布局
        footer = LinearLayout.inflate(getActivity(), R.layout.foot_boot, null);

        //初始状态为隐藏
        footer.setVisibility(View.GONE);
        //加入到ListView
        mainListView.addFooterView(footer);
        //创建点击事件
        poor = (ImageView) v.findViewById(R.id.poor_img);
        produce = (ImageView) v.findViewById(R.id.produce_img);
        forum = (ImageView) v.findViewById(R.id.forum_img);
        poor.setOnClickListener(this);
        produce.setOnClickListener(this);
        forum.setOnClickListener(this);

        getContent();
        //自定义的滚动监听事件
        onScrollListener = new MainOnScerllListener(footer);
        //设置接口回调
        onScrollListener.setOnLoadDataListener(this);
        inView();
        return v;
    }

    private void getContent() {

        httpUtils = new HttpUtils();
        params = new RequestParams();
        params.addBodyParameter("pageNow", "1");
        params.addBodyParameter("pageSize", "5");
        httpUtilsConnection(Constant.URL_NEWS, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {

        httpUtils.send(method, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("CESHITAG", "onSuccess===>>>" + responseInfo.result.toString());
                String body = responseInfo.result;
                Gson gosn = new Gson();
                MainData mainData = gosn.fromJson(body, MainData.class);
                listData = mainData.getDatas();
                showListView(listData);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("CESHITAG", "onFailure===>>>" + s);
                ToastUtil.showInfo(getActivity(), "提示：您的网络有异常，请检查您的网络");
                mShowView.showByType(CommonShowView.TYPE_ERROR);
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                LogUtil.i("ONLOADINGTAG", "total：" + total + "______current：" + current);
            }
        });

    }

    private void inView() {
        fl = (FrameLayout) v.findViewById(R.id.fl);
        //获取屏幕宽高
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        // 屏幕宽度（像素）
        int width = metric.widthPixels;
        //设置布局容器宽度
        fl.getLayoutParams().width = width;
        //设置布局容器的高度
        fl.getLayoutParams().height = width / 2;
        //声明轮播图控件
        mViewPaper = (ViewPager) v.findViewById(R.id.vp);
        //初始化显示的图片
        images = new ArrayList<ImageView>();
        //添加图片到图片集合
        for (int i = 0; i < imageIds.length; i++) {
            //初始化控件
            ImageView imageView = new ImageView(getActivity());
            //设置图片背景
            imageView.setBackgroundResource(imageIds[i]);
            //图片添加到集合
            images.add(imageView);
        }

        //初始化轮播图文字
        title = (TextView) v.findViewById(R.id.title);
        //设置轮播图显示文字
        title.setText(titles[0]);

        //小点集合
        dots = new ArrayList<View>();
        //初始化小点添加到集合
        dots.add(v.findViewById(R.id.dot_0));
        dots.add(v.findViewById(R.id.dot_1));
        dots.add(v.findViewById(R.id.dot_2));
        dots.add(v.findViewById(R.id.dot_3));

        //初始化适配器
        mViewPagerAdapter = new MViewPagerAdapter();
        //轮播图绑定适配器
        mViewPaper.setAdapter(mViewPagerAdapter);
        //轮播图滑动事件监听
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //页面切换后触发
            @Override
            public void onPageSelected(int position) {
                //设置轮播图文字
                title.setText(titles[position]);
                //设置当前小点图片
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                //设置前一个小点图片
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                //记录小点id
                oldPosition = position;
                //记录当前位置
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    /**
     * 将数据加载到ListView上
     *
     * @param data
     */
    private void showListView(List<Datas> data) {
        //首先判断适配器是否为空，首次运行肯定是为空的
        if (adapter == null) {
            //创建adpter数据
            adapter = new MainListAdapter(getActivity(), data);
            //设置adapter
            mainListView.setAdapter(adapter);
            mShowView.showByType(CommonShowView.TYPE_CONTENT);
            //设置ListView的滚动监听事件
            mainListView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();

                    if(action == MotionEvent.ACTION_DOWN) {
                        mLastY = event.getY();
                    }
                    if(action == MotionEvent.ACTION_MOVE) {
                        int top = mainListView.getChildAt(0).getTop();
                        float nowY = event.getY();
                        if(!isSvToBottom) {
                            // 允许scrollview拦截点击事件, scrollView滑动
                            sv.requestDisallowInterceptTouchEvent(false);
                        } else if(top == 0 && nowY - mLastY > THRESHOLD_Y_LIST_VIEW) {
                            // 允许scrollview拦截点击事件, scrollView滑动
                            sv.requestDisallowInterceptTouchEvent(false);
                        } else {
                            // 不允许scrollview拦截点击事件， listView滑动
                            sv.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                    return false;
                }
            });
            mainListView.setOnScrollListener(onScrollListener);
            mainListView.setOnItemClickListener(this);
        } else {
            //不为空，则刷新数据
            listData.addAll(data);
            //提醒ListView重新更新数据
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadData(List<Datas> data) {
        showListView(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.poor_img:
                Intent intent = new Intent(getActivity(), PoorActivity.class);
                startActivity(intent);
                LogUtil.e("贫困户");
                break;
            case R.id.produce_img:
                Intent intentPri = new Intent(getActivity(), PrimaryProductsActivity.class);
                startActivity(intentPri);
                LogUtil.e("农产品");
                break;
            case R.id.forum_img:
                Intent intentFor = new Intent(getActivity(), ForumActivity.class);
                startActivity(intentFor);
                LogUtil.e("讨论区");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Datas info = listData.get(position);
        Intent intent = new Intent(getActivity(), HotDetailsActivity.class);
        Bundle bundle = new Bundle();
        String ID = info.getId();
        String MassageType = info.getMassageType();
        bundle.putString("ID", ID);
        bundle.putString("MassageType", MassageType);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onScrollToBottom() {
        isSvToBottom = true;
    }

    @Override
    public void onNotScrollToBottom() {
        isSvToBottom = false;
    }

    public class MViewPagerAdapter extends PagerAdapter {
        //返回页卡的数量
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;//官方提示这样写
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
            view.removeView(images.get(position));//删除页卡
        }

        //这个方法用来实例化页卡
        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            //添加图片控件到轮播图控件
            view.addView(images.get(position));
            return images.get(position);
        }
    }

    /**
     * 利用线程池定时执行动画轮播
     */

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        //初始化定时线程
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 设定执行线程计划,初始2s延迟,每次任务完成后延迟2s再执行一次任务
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 5, 5,
                TimeUnit.SECONDS);
    }

    private class ViewPageTask implements Runnable {
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            //发送消息
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        }
    };

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }
}