package com.yhzhcs.dispatchingsystemjzfp.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.adapters.MyFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/12.
 * <p>
 * author Luhuai Wu
 */

public class InformationFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    private ViewPager Infoviewpager;

    private View view;

    //选项卡中的按钮
    private TextView Information_first;
    private TextView Information_second;
    private TextView Information_third;
    //作为指示标签的按钮
    private ImageView cursorInformation;
    //标志指示标签的横坐标
    float cursorX = 0;
    //所有按钮的宽度的数组
    private int[] widthArgs;
    //所有标题按钮的数组
    private TextView[] InformationArgs;
    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.information_fragment,container,false);
        initView();
        initEvent();                        //调用初始化事件
        return view;
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

    /**
     * 初始化事件
     */
    private void initEvent() {
        Infoviewpager.addOnPageChangeListener(this);
    }

    //初始化布局
    public void initView() {

        titleImgL = (ImageView) view.findViewById(R.id.title_left);
        titleName = (TextView) view.findViewById(R.id.title_name);
        titleImgR = (ImageView) view.findViewById(R.id.title_right);

        titleName.setText("新闻资讯");
        titleImgR.setVisibility(View.GONE);

        Infoviewpager = (ViewPager) this.view.findViewById(R.id.information_myviewpager);

        Information_first = (TextView) this.view.findViewById(R.id.information_first);
        Information_second = (TextView) this.view.findViewById(R.id.information_second);
        Information_third = (TextView) this.view.findViewById(R.id.information_third);
        //初始化按钮数组
        InformationArgs = new TextView[]{Information_first, Information_second, Information_third};
        //指示标签设置为蓝色
        cursorInformation = (ImageView) this.view.findViewById(R.id.cursor_information);
        cursorInformation.setBackgroundColor(Color.parseColor("#0090FF"));

        Information_first.setOnClickListener(this);
        Information_second.setOnClickListener(this);
        Information_third.setOnClickListener(this);

        fragments = new ArrayList<Fragment>();
        fragments.add(new PolicyFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new ProduceFragment());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments);
        Infoviewpager.setOffscreenPageLimit(fragments.size());//设定预加载Fragment
        Infoviewpager.setAdapter(adapter);
        Infoviewpager.setOnPageChangeListener(this);

        //先重置所有按钮颜色
        resetButtonColor();
        //再将第一个按钮字体设置为蓝色，表示默认选中第一个
        Information_first.setTextColor(Color.parseColor("#0090FF"));

    }

    //重置所有按钮的颜色
    public void resetButtonColor() {
        Information_first.setBackgroundColor(Color.parseColor("#FFFFFF"));
        Information_second.setBackgroundColor(Color.parseColor("#FFFFFF"));
        Information_third.setBackgroundColor(Color.parseColor("#FFFFFF"));
        Information_first.setTextColor(Color.parseColor("#666666"));
        Information_second.setTextColor(Color.parseColor("#666666"));
        Information_third.setTextColor(Color.parseColor("#666666"));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.title_left:
                break;
            case R.id.information_first:
                Infoviewpager.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.information_second:
                Infoviewpager.setCurrentItem(1);
                cursorAnim(1);
                break;
            case R.id.information_third:
                Infoviewpager.setCurrentItem(2);
                cursorAnim(2);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // TODO Auto-generated method stub
        if (widthArgs == null) {
            widthArgs = new int[]{Information_first.getWidth(),
                    Information_second.getWidth(),
                    Information_third.getWidth(),
            };
        }

        //每次滑动首先重置所有按钮的颜色
        resetButtonColor();
        //将滑动到的当前按钮颜色设置为蓝色
        cursorAnim(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }

    //指示器的跳转，传入当前所处的页面的下标
    public void cursorAnim(int curItem) {
        //每次调用，就将指示器的横坐标设置为0，即开始的位置
        cursorX = 0;
        //再根据当前的curItem来设置指示器的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursorInformation.getLayoutParams();
        //减去边距*2，以对齐标题栏文字
        lp.width = widthArgs[curItem] - InformationArgs[0].getPaddingLeft() * 2;
        cursorInformation.setLayoutParams(lp);
        //循环获取当前页之前的所有页面的宽度
        for (int i = 0; i < curItem; i++) {
            cursorX = cursorX + InformationArgs[i].getWidth();
        }
        //再加上当前页面的左边距，即为指示器当前应处的位置
        cursorInformation.setX(cursorX + InformationArgs[curItem].getPaddingLeft());
        InformationArgs[curItem].setTextColor(Color.parseColor("#0090FF"));
    }

}
