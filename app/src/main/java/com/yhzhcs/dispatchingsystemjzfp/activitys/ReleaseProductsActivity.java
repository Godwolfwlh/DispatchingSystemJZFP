package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.adapters.ReleaseProductsAdapter;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.PictureSelectorConfig;
import com.yhzhcs.dispatchingsystemjzfp.view.BottomScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class ReleaseProductsActivity extends AppCompatActivity implements BottomScrollView.OnScrollToBottomListener {
    private Context mContext;
    private GridView gridView;
    private ArrayList<String> mPicList = new ArrayList<>(); //上传的图片凭证的数据源
    private ReleaseProductsAdapter releaseProductsAdapter; //展示上传的图片的适配器
    private BottomScrollView scrollView;
    private boolean isSvToBottom = false;

    private float mLastY;

    /**
     * gridView竖向滑动的阈值
     */
    private static final int THRESHOLD_Y_LIST_VIEW = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_primary_products);

        mContext = this;
        scrollView = (BottomScrollView) findViewById(R.id.release_scroll);
        scrollView.smoothScrollTo(0, 0);
        scrollView.setScrollToBottomListener(this);
        gridView = (GridView) findViewById(R.id.release_grid_view);
        initGridView();
    }


    //初始化展示上传图片的GridView
    private void initGridView() {
        releaseProductsAdapter = new ReleaseProductsAdapter(mContext, mPicList);
        gridView.setAdapter(releaseProductsAdapter);
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                if(action == MotionEvent.ACTION_DOWN) {
                    mLastY = motionEvent.getY();
                }
                if(action == MotionEvent.ACTION_MOVE) {
                    int top = gridView.getChildAt(0).getTop();
                    float nowY = motionEvent.getY();
                    if(!isSvToBottom) {
                        // 允许scrollview拦截点击事件, scrollView滑动
                        scrollView.requestDisallowInterceptTouchEvent(false);
                    } else if(top == 0 && nowY - mLastY > THRESHOLD_Y_LIST_VIEW) {
                        // 允许scrollview拦截点击事件, scrollView滑动
                        scrollView.requestDisallowInterceptTouchEvent(false);
                    } else {
                        // 不允许scrollview拦截点击事件， listView滑动
                        scrollView.requestDisallowInterceptTouchEvent(true);
                    }
                }
                return false;
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过300张，才能点击
                    if (mPicList.size() == Constant.MAX_SELECT_PIC_NUM) {
                        //最多添加300张图片
                        viewPluImg(position);
                    } else {
                        //添加凭证图片
                        selectPic(Constant.MAX_SELECT_PIC_NUM - mPicList.size());
                    }
                } else {
                    viewPluImg(position);
                }
            }
        });
    }

    //查看大图
    private void viewPluImg(int position) {
        Intent intent = new Intent(mContext, PlusImageActivity.class);
        intent.putStringArrayListExtra(Constant.IMG_LIST, mPicList);
        intent.putExtra(Constant.POSITION, position);
        startActivityForResult(intent, Constant.REQUEST_CODE_MAIN);
    }

    /**
     * 打开相册或者照相机选择凭证图片，最多300张
     *
     * @param maxTotal 最多选择的图片的数量
     */
    private void selectPic(int maxTotal) {
        PictureSelectorConfig.initMultiConfig(this, maxTotal);
    }

    // 处理选择的照片的地址
    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                mPicList.add(compressPath); //把图片添加到将要上传的图片数组中
                releaseProductsAdapter.notifyDataSetChanged();
                LogUtil.v("MPICLIST",mPicList.toString());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    refreshAdapter(PictureSelector.obtainMultipleResult(data));
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
        if (requestCode == Constant.REQUEST_CODE_MAIN && resultCode == Constant.RESULT_CODE_VIEW_IMG) {
            //查看大图页面删除了图片
            ArrayList<String> toDeletePicList = data.getStringArrayListExtra(Constant.IMG_LIST); //要删除的图片的集合
            mPicList.clear();
            mPicList.addAll(toDeletePicList);
            releaseProductsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onScrollToBottom() {
        isSvToBottom = true;
    }

    @Override
    public void onNotScrollToBottom() {
        isSvToBottom = false;
    }
}
