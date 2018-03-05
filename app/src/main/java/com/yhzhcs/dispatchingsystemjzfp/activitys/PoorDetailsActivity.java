package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.adapters.MyFragmentPagerAdapter;
import com.yhzhcs.dispatchingsystemjzfp.fragments.DetailsFragment;
import com.yhzhcs.dispatchingsystemjzfp.fragments.FamilyFragment;
import com.yhzhcs.dispatchingsystemjzfp.fragments.ImgFragment;
import com.yhzhcs.dispatchingsystemjzfp.fragments.IncomeFragment;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.util.ArrayList;

public class PoorDetailsActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    private ViewPager myviewpager;

    //选项卡中的按钮
    private TextView btn_first;
    private TextView btn_second;
    private TextView btn_third;
    private TextView btn_four;
    //作为指示标签的按钮
    private ImageView cursor;
    //标志指示标签的横坐标
    float cursorX = 0;
    //所有按钮的宽度的数组
    private int[] widthArgs;
    //所有标题按钮的数组
    private TextView[] btnArgs;
    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;

    private String poorHouseId;
    private String poorName;
    private DetailsFragment detailsFragment;
    private FamilyFragment familyFragment;
    private IncomeFragment incomeFragment;
    private ImgFragment imgFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poor_details);
        getData();
        initView();
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        poorHouseId = bundle.getString("poorHouseId");
        poorName = bundle.getString("poorName");
    }

    //初始化布局
    public void initView() {

        titleImgL = (ImageView) findViewById(R.id.title_left);
        titleName = (TextView) findViewById(R.id.title_name);
        titleImgR = (ImageView) findViewById(R.id.title_right);

        titleImgL.setVisibility(View.VISIBLE);
        titleImgL.setImageResource(R.mipmap.icon_back);
        titleName.setText(poorName);
        titleImgR.setVisibility(View.GONE);
        titleImgL.setOnClickListener(this);

        myviewpager = (ViewPager) this.findViewById(R.id.myviewpager);

        btn_first = (TextView) this.findViewById(R.id.btn_first);
        btn_second = (TextView) this.findViewById(R.id.btn_second);
        btn_third = (TextView) this.findViewById(R.id.btn_third);
        btn_four = (TextView) this.findViewById(R.id.btn_four);
        //初始化按钮数组
        btnArgs = new TextView[]{btn_first, btn_second, btn_third, btn_four};
        //指示标签设置为蓝色
        cursor = (ImageView) this.findViewById(R.id.cursor_btn);
        cursor.setBackgroundColor(Color.parseColor("#0090FF"));

        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        btn_third.setOnClickListener(this);
        btn_four.setOnClickListener(this);

        detailsFragment = new DetailsFragment();
        familyFragment = new FamilyFragment();
        incomeFragment = new IncomeFragment();
        imgFragment = new ImgFragment();
        Bundle bun = new Bundle();
        bun.putString("poorHouseId",poorHouseId);
        detailsFragment.setArguments(bun);
        familyFragment.setArguments(bun);
        incomeFragment.setArguments(bun);
        imgFragment.setArguments(bun);

        fragments = new ArrayList<Fragment>();
        fragments.add(detailsFragment);
        fragments.add(familyFragment);
        fragments.add(incomeFragment);
        fragments.add(imgFragment);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        myviewpager.setAdapter(adapter);
        myviewpager.setOnPageChangeListener(this);

        //先重置所有按钮颜色
        resetButtonColor();
        //再将第一个按钮字体设置为蓝色，表示默认选中第一个
        btn_first.setTextColor(Color.parseColor("#0090FF"));

    }

    //重置所有按钮的颜色
    public void resetButtonColor() {
        btn_first.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btn_second.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btn_third.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btn_four.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btn_first.setTextColor(Color.parseColor("#666666"));
        btn_second.setTextColor(Color.parseColor("#666666"));
        btn_third.setTextColor(Color.parseColor("#666666"));
        btn_four.setTextColor(Color.parseColor("#666666"));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.btn_first:
                myviewpager.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.btn_second:
                myviewpager.setCurrentItem(1);
                cursorAnim(1);
                break;
            case R.id.btn_third:
                myviewpager.setCurrentItem(2);
                cursorAnim(2);
                break;
            case R.id.btn_four:
                myviewpager.setCurrentItem(3);
                cursorAnim(3);
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
            widthArgs = new int[]{btn_first.getWidth(),
                    btn_second.getWidth(),
                    btn_third.getWidth(),
                    btn_four.getWidth()};
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
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
        //减去边距*2，以对齐标题栏文字
        lp.width = widthArgs[curItem] - btnArgs[0].getPaddingLeft() * 2;
        cursor.setLayoutParams(lp);
        //循环获取当前页之前的所有页面的宽度
        for (int i = 0; i < curItem; i++) {
            cursorX = cursorX + btnArgs[i].getWidth();
        }
        //再加上当前页面的左边距，即为指示器当前应处的位置
        cursor.setX(cursorX + btnArgs[curItem].getPaddingLeft());
        btnArgs[curItem].setTextColor(Color.parseColor("#0090FF"));
    }

}
