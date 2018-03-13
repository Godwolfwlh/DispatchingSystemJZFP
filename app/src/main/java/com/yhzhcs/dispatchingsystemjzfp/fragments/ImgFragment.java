package com.yhzhcs.dispatchingsystemjzfp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.activitys.SampleCameraActivity;
import com.yhzhcs.dispatchingsystemjzfp.adapters.PoorImageAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.Inglists;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorImageBean;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.ToastUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.TypeConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2018/1/24.
 */

public class ImgFragment extends Fragment implements View.OnClickListener {

    private View v;

    private GridView gridView;

    private String entityId;

    private List<Inglists> listBean;

    private TextView Upload, Delete;

    public final int TYPE_TAKE_PHOTO = 1;//Uri获取类型判断
    public final int CODE_TAKE_PHOTO = 1;//相机RequestCode
    public Uri photoUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_poor_img, container, false);
        Bundle bundle = getArguments();
        entityId = bundle.getString("poorHouseId");
        LogUtil.i("entityId", entityId);
        iniView();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("entityId", entityId);
        params.addBodyParameter("entityType", "ing");
        httpUtils.send(HttpMethod.POST, Constant.URL_POOR_IMG, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("IMG_HTTP", "onSuccess====>>" + responseInfo.result.toString());
                String body = responseInfo.result;
                Gson gson = new Gson();
                PoorImageBean poorImageBean = gson.fromJson(body, PoorImageBean.class);
                listBean = poorImageBean.getIngLists();
                PoorImageAdapter adapter = new PoorImageAdapter(listBean, getActivity());

                gridView.setAdapter(adapter);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("IMG_HTTP", "onFailure====>>>" + s);
            }
        });
    }

    private void iniView() {
        gridView = (GridView) v.findViewById(R.id.img_grid_view);
        Upload = (TextView) v.findViewById(R.id.img_title_text);
        Delete = (TextView) v.findViewById(R.id.img_title_delete);

        Upload.setOnClickListener(this);
        Delete.setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_title_text:
                showListDialog();
                break;
            case R.id.img_title_delete:
                break;
        }

    }


    private void showListDialog() {
        final String[] items = {"拍照", "相册"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(getActivity());
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        autoObtainCameraPermission();
                        break;
                    case 1:
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(getActivity(), SampleCameraActivity.class);
                        bundle.putString("entityId", entityId);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                }
            }
        });
        listDialog.show();
    }

    /**
     * 多版本适配调用系统相机
     */
    private void autoObtainCameraPermission() {
        if (Build.VERSION.SDK_INT >= 24) {
            Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            photoUri = get24MediaFileUri(TYPE_TAKE_PHOTO);
            takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(takeIntent, CODE_TAKE_PHOTO);
        } else {
            Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            photoUri = getMediaFileUri(TYPE_TAKE_PHOTO);
            takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(takeIntent, CODE_TAKE_PHOTO);
        }
    }

    /**
     * 版本24以下
     */
    public Uri getMediaFileUri(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "fpb_image");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //创建Media File
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == TYPE_TAKE_PHOTO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return Uri.fromFile(mediaFile);
    }

    /**
     * 版本24以上
     */
    public Uri get24MediaFileUri(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "fpb_image");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //创建Media File
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == TYPE_TAKE_PHOTO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return FileProvider.getUriForFile(getActivity(), "com.yhzhcs.dispatchingsystemjzfp.fileprovider", mediaFile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        if (data.hasExtra("data")) {
                            Bitmap bitmap = data.getParcelableExtra("data");
                            upImage(bitmap);
                        }
                    } else {
                        if (Build.VERSION.SDK_INT >= 24) {
                            Bitmap bitmap = null;
                            try {
                                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(photoUri));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            upImage(bitmap);
                        } else {
                            Bitmap bitmap = BitmapFactory.decodeFile(photoUri.getPath());
                            upImage(bitmap);
                        }
                    }
                }
                break;
        }
    }

    private void upImage(Bitmap bitmap) {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        String basePath = TypeConverter.bitmapToBase64(bitmap);
        params.addBodyParameter("file", basePath);
        params.addBodyParameter("id", entityId);//贫困户id
        params.addBodyParameter("entityType", "ing");
        httpUtils.send(HttpMethod.POST, Constant.URL_SAVE_PHOTO, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("SAMPLE_CAMERA_HTTP", "onSuccess：" + responseInfo.result.toString());
                ToastUtil.showInfo(getActivity(),"上传成功！");
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("SAMPLE_CAMERA_HTTP", "onFailure：" + s);
                ToastUtil.showInfo(getActivity(),"上传失败！");
            }
        });
    }

}
