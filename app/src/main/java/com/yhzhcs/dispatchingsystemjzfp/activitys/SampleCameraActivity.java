package com.yhzhcs.dispatchingsystemjzfp.activitys;

/**
 * Created by shiqi on 2017/6/6.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.adapters.CommonAdapter;
import com.yhzhcs.dispatchingsystemjzfp.adapters.ViewHolder;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.ImageFloder;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.ToastUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.TypeConverter;
import com.yhzhcs.dispatchingsystemjzfp.view.ListImageDirPopupWindow;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SampleCameraActivity extends Activity implements ListImageDirPopupWindow.OnImageDirSelected {
    int totalCount = 0;
    private ProgressDialog mProgressDialog;
    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;
    /**
     * 所有的图片
     */
    private List<String> mImgs;
    private GridView mGirdView;
    private MyImageAdapter mAdapter;
    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();
    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();
    private RelativeLayout mBottomLy;
    private TextView mChooseDir;
    private TextView mImageCount;
    private int mScreenHeight;
    private ListImageDirPopupWindow mListImageDirPopupWindow;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mProgressDialog.dismiss();
            // 为View绑定数据
            data2View();
            // 初始化展示文件夹的popupWindw
            initListDirPopupWindw();
        }
    };
    private TextView tv_samplecamera_confirm;
    private TextView tv_return;

    private String entityId;

    /**
     * 用户选择的图片，存储为图片的完整路径
     */
    private ArrayList<String> mSelectedImage = new ArrayList<String>();
    private ArrayList<ImageView> SelectedImageViews = new ArrayList<ImageView>();
    private String poorCardNumber;

    /**
     * 为View绑定数据
     */
    private void data2View() {
        if (mImgDir == null) {
            Toast.makeText(getApplicationContext(), "图片没扫描到",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mImgs = Arrays.asList(mImgDir.list());
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        mAdapter = new MyImageAdapter(getApplicationContext(), mImgs,
                R.layout.view_cameragrid_item, mImgDir.getAbsolutePath());
        mAdapter.setIamgeNull();
        mGirdView.setAdapter(mAdapter);
        mImageCount.setText(totalCount + "张");
    }

    /**
     * 初始化展示文件夹的popupWindw
     */
    private void initListDirPopupWindw() {
        mListImageDirPopupWindow = new ListImageDirPopupWindow(
                (int) LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
                mImageFloders, LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.view_cameralist_dir, null));
        mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        // 设置选择文件夹的回调
        mListImageDirPopupWindow.setOnImageDirSelected(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_camera);
        Bundle bundle = getIntent().getExtras();
        entityId = bundle.getString("entityId");
        poorCardNumber = bundle.getString("poorCardNumber");
        LogUtil.i("entityId", entityId);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        initView();
        getImages();
        initEvent();
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        // 显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String firstImage = null;
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = SampleCameraActivity.this
                        .getContentResolver();
                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);
                Log.e("TAG", mCursor.getCount() + "");
                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    Log.e("TAG", path);
                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFloder imageFloder = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        // 初始化imageFloder
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }
                    int picSize = 0;
                    try {
                        picSize = parentFile.list(new FilenameFilter() {
                            @Override
                            public boolean accept(File dir, String filename) {
                                if (filename.endsWith(".jpg")
                                        || filename.endsWith(".png")
                                        || filename.endsWith(".jpeg"))
                                    return true;
                                return false;
                            }
                        }).length;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    totalCount += picSize;
                    imageFloder.setCount(picSize);
                    mImageFloders.add(imageFloder);
                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();
                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;
                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(0x110);

            }
        }).start();
    }

    /**
     * 初始化View
     */
    private void initView() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mGirdView = (GridView) findViewById(R.id.id_gridView);
        tv_samplecamera_confirm = (TextView) findViewById(R.id.tv_samplecamera_confirm);
        mChooseDir = (TextView) findViewById(R.id.id_choose_dir);
        tv_return = (TextView) findViewById(R.id.tv_return);
        mImageCount = (TextView) findViewById(R.id.id_total_count);
        mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);
    }

    private void initEvent() {
        tv_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SampleCameraActivity.this, PoorDetailsActivity.class);
                intent.putExtra("FRAGMENT_ID", 3);
                intent.putExtra("poorHouseId", entityId);
                intent.putExtra("poorCardNumber", poorCardNumber);
                startActivity(intent);
                finish();
            }
        });
        /**
         * 为底部的布局设置点击事件，弹出popupWindow
         */
        mBottomLy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListImageDirPopupWindow
                        .setAnimationStyle(R.style.anim_popup_dir);
                mListImageDirPopupWindow.showAsDropDown(mBottomLy, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = .3f;
                getWindow().setAttributes(lp);
            }
        });
        tv_samplecamera_confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                serImage();
                Intent intent = new Intent(SampleCameraActivity.this, PoorDetailsActivity.class);
                intent.putExtra("FRAGMENT_ID", 3);
                intent.putExtra("poorHouseId", entityId);
                intent.putExtra("poorCardNumber", poorCardNumber);
                startActivity(intent);
                finish();
            }
        });
    }

    public void selected(ImageFloder floder) {
        mImgDir = new File(floder.getDir());
        mImgs = Arrays.asList(mImgDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg") || filename.endsWith(".png")
                        || filename.endsWith(".jpeg"))
                    return true;
                return false;
            }
        }));
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        mAdapter = new MyImageAdapter(getApplicationContext(), mImgs,
                R.layout.view_cameragrid_item, mImgDir.getAbsolutePath());
        mGirdView.setAdapter(mAdapter);
        // mAdapter.notifyDataSetChanged();
        mImageCount.setText(floder.getCount() + "张");
        mChooseDir.setText(floder.getName());
        mListImageDirPopupWindow.dismiss();
    }

    private String path = null;
    private String imagePath = null;

    private void serImage() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        for (int i = 0; i < mSelectedImage.size(); i++) {
            params.addBodyParameter("file", new File(mSelectedImage.get(i)));
            params.addBodyParameter("entityId ", entityId);
            params.addBodyParameter("entityType", "ing");
            params.addBodyParameter("content", "");

            httpUtils.send(HttpMethod.POST, Constant.URL_SAVE_PHOTOS, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    LogUtil.v("SAMPLE_CAMERA_HTTP", "onSuccess：" + responseInfo.result);
//                    if (!"".equals(responseInfo.result) && !"error".equals(responseInfo.result)){
//                        ToastUtil.showInfo(SampleCameraActivity.this, "上传成功！");
//                    }else{
//                        ToastUtil.showInfo(SampleCameraActivity.this, "失败！");
//                    }

                }

                @Override
                public void onFailure(HttpException e, String s) {
                    LogUtil.v("SAMPLE_CAMERA_HTTP", "onFailure：" + s);
//                    ToastUtil.showInfo(SampleCameraActivity.this, "上传失败！");
                }
            });
        }
    }

    private void upImage() {

        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        LogUtil.i("ttttttttttt", "LIST:" + mSelectedImage.toString());
        for (int i = 0; i < mSelectedImage.size(); i++) {
            String image = mSelectedImage.get(i);
            imagePath = image;
            LogUtil.i("iiiiiiiiiii", imagePath);
            String paths = TypeConverter.imageToBase64(imagePath);
            path = paths;
            params.addBodyParameter("file", path);
            params.addBodyParameter("id", entityId);
            params.addBodyParameter("entityType", "ing");
            LogUtil.v("mmmmmmmmm", "past:" + path);
            path = "";
            imagePath = "";
            httpUtils.send(HttpMethod.POST, Constant.URL_SAVE_PHOTO, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    LogUtil.v("SAMPLE_CAMERA_HTTP", "onSuccess：" + responseInfo.result.toString());
                    ToastUtil.showInfo(SampleCameraActivity.this, "上传成功！");
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    LogUtil.v("SAMPLE_CAMERA_HTTP", "onFailure：" + s);
                    ToastUtil.showInfo(SampleCameraActivity.this, "上传失败！");
                }
            });
        }
    }

    public class MyImageAdapter extends CommonAdapter<String> {

        /**
         * 文件夹路径
         */
        private String mDirPath;

        public MyImageAdapter(Context context, List<String> mDatas, int itemLayoutId,
                              String dirPath) {
            super(context, mDatas, itemLayoutId);
            this.mDirPath = dirPath;
        }

        public ArrayList<String> getImageList() {
            return mSelectedImage;
        }

        public void setIamgeNull() {
            for (ImageView imageView : SelectedImageViews) {
                imageView.setImageResource(R.mipmap.picture_unselected);
            }
            mSelectedImage.removeAll(mSelectedImage);
        }

        @Override
        public void convert(ViewHolder helper, final String item) {
            // 设置no_pic
            helper.setImageResource(R.id.id_item_image, R.mipmap.pictures_no);
            // 设置no_selected
            helper.setImageResource(R.id.id_item_select, R.mipmap.picture_unselected);
            // 设置图片
            helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

            final ImageView mImageView = helper.getView(R.id.id_item_image);
            final ImageView mSelect = helper.getView(R.id.id_item_select);
            mImageView.setColorFilter(null);
            // 设置ImageView的点击事件
            mImageView.setOnClickListener(new OnClickListener() {
                // 选择，则将图片变暗，反之则反之
                @Override
                public void onClick(View v) {

                    // 已经选择过该图片
                    if (mSelectedImage.contains(mDirPath + "/" + item)) {
                        mSelectedImage.remove(mDirPath + "/" + item);
                        mSelect.setImageResource(R.mipmap.picture_unselected);
                        SelectedImageViews.remove(mSelect);
                        mImageView.setColorFilter(null);
                    } else
                    // 未选择该图片
                    {
                        SelectedImageViews.add(mSelect);
                        mSelectedImage.add(mDirPath + "/" + item);
                        mSelect.setImageResource(R.mipmap.pictures_selected);
                        mImageView.setColorFilter(Color.parseColor("#77000000"));
                    }
                }
            });

            /**
             * 已经选择过的图片，显示出选择过的效果
             */
            if (mSelectedImage.contains(mDirPath + "/" + item)) {
                mSelect.setImageResource(R.mipmap.pictures_selected);
                mImageView.setColorFilter(Color.parseColor("#77000000"));
            }
        }
    }

}
