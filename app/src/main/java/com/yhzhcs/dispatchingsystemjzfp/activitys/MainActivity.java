package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.fragments.TaskFragment;
import com.yhzhcs.dispatchingsystemjzfp.fragments.PersonalFragment;
import com.yhzhcs.dispatchingsystemjzfp.fragments.InformationFragment;
import com.yhzhcs.dispatchingsystemjzfp.fragments.MainFragment;
import com.yhzhcs.dispatchingsystemjzfp.view.ChangeIcon;
import com.yhzhcs.dispatchingsystemjzfp.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private MyViewPager lViewPager;   //定义用于滚动视图的ViewPager

    private Intent intent;
    private ChangeIcon one;
    private ChangeIcon two;
    private ChangeIcon three;
    private ChangeIcon four;
    //定义一个视图集合（用来装左右滑动的页面视图）
    private List<Fragment> viewList = new ArrayList<Fragment>();
    private FragmentStatePagerAdapter mAdapter;  //定义适配器
    //定义绘制图标控件集合
    private List<ChangeIcon> lTabIndicators = new ArrayList<ChangeIcon>();

    private Bitmap lIconBitmap;         //定义图标获取参数的Bitmap
    private AttributeSet attrs;

    /**
     * 获取自定义属性的值
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();                         //调用初始化方法
        initDatas();                        //调用保存初始化数据的方法
        lViewPager.setOffscreenPageLimit(viewList.size());//设定预加载的Fragment
        lViewPager.setAdapter(mAdapter);  //配置适配器
        initEvent();                        //调用初始化事件
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        lViewPager.addOnPageChangeListener(this);
    }

    //初始化方法
    private void initView() {
        lViewPager = (MyViewPager) findViewById(R.id.id_viewpager);   //获取布局文件中滚动视图的ViewPager
        lViewPager.setNestedpParent((ViewGroup) lViewPager.getParent());
        //获取自定义图标
        one = (ChangeIcon) findViewById(R.id.id_changeicon_one);
        lTabIndicators.add(one);            //添加到集合当中
        two = (ChangeIcon) findViewById(R.id.id_changeicon_two);
        lTabIndicators.add(two);
        three = (ChangeIcon) findViewById(R.id.id_changeicon_three);
        lTabIndicators.add(three);
        four = (ChangeIcon) findViewById(R.id.id_changeicon_four);
        lTabIndicators.add(four);

        viewList.add(new MainFragment());
        viewList.add(new InformationFragment());
        viewList.add(new TaskFragment());
        viewList.add(new PersonalFragment());

        one.setOnClickListener(onClick);           //指定单击事件
        two.setOnClickListener(onClick);
        three.setOnClickListener(onClick);
        four.setOnClickListener(onClick);
        one.setIconAlpha(1.0f);                     //设置图标不透明默认为绿色

        TypedArray a = this.obtainStyledAttributes(attrs, R.styleable.ChangeIcon);
        int n = a.getIndexCount();   //获取值下标
        //便利属性值
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {   //获取图标属性值
                case R.styleable.ChangeIcon_icon:
                    BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
                    lIconBitmap = drawable.getBitmap();
                    break;
            }

        }
    }

    //初始化数据的方法
    private void initDatas() {

        mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return viewList.get(position);
            }

            @Override
            public int getCount() {
                return viewList.size();
            }
        };

    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetOtherIconColor();                           //调用重置其他图标颜色方法
            switch (v.getId()) {
                case R.id.id_changeicon_one:                     //如果首页图标
                    lTabIndicators.get(0).setIconAlpha(1.0f);   //设置首页图标为不透明显示蓝色
//                    private int[] imageIds = new int[]{
//                            R.mipmap.icon_home_active,
//                            R.mipmap.icon_information_active,
//                            R.mipmap.icon_task_active,
//                            R.mipmap.icon_my_active
//                    };
                    Resources res = MainActivity.this.getResources();
                    lIconBitmap= BitmapFactory.decodeResource(res, R.mipmap.icon_home_active);
//                    lTabIndicators.get(0).drawBitmap
                    lViewPager.setCurrentItem(0, false);        //直接切换首页主视图
                    break;
                case R.id.id_changeicon_two:
                    lTabIndicators.get(1).setIconAlpha(1.0f);
                    lViewPager.setCurrentItem(1, false);
                    break;
                case R.id.id_changeicon_three:
                    lTabIndicators.get(2).setIconAlpha(1.0f);
                    lViewPager.setCurrentItem(2, false);
                    break;
                case R.id.id_changeicon_four:
                    lTabIndicators.get(3).setIconAlpha(1.0f);
                    lViewPager.setCurrentItem(3, false);
                    break;
            }
        }
    };

    /**
     * 重置其他图标颜色
     */
    private void resetOtherIconColor() {
        for (int i = 0; i < lTabIndicators.size(); i++) {
            lTabIndicators.get(i).setIconAlpha(0);      //设置默认为透明
        }
    }

    /**
     * 根据偏移量设置从右向左滑动时起始图标透明，起始图标右侧绘制颜色
     * 相反从左向右滑动时起始图标设置透明，起始图标左侧图标绘制颜色
     */
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        if (positionOffset > 0)     //如果偏移值大于0
        {
            ChangeIcon left = lTabIndicators.get(position);     //获取向左滑下标
            ChangeIcon right = lTabIndicators.get(position + 1);  //获取向右滑下标
            left.setIconAlpha(1 - positionOffset);      //设置左侧图标透明，右侧变色
            right.setIconAlpha(positionOffset);       //设置右侧图标透明，左侧变色
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
