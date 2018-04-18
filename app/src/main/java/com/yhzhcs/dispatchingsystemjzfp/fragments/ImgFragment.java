package com.yhzhcs.dispatchingsystemjzfp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.activitys.SampleCameraActivity;
import com.yhzhcs.dispatchingsystemjzfp.bean.Inglists;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorImageBean;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.ToastUtil;
import com.yhzhcs.dispatchingsystemjzfp.utils.TypeConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2018/1/24.
 */

public class ImgFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private View v;

    private GridView gridView;

    private String entityId;

    private List<Inglists> listBean;

    private TextView Upload, Select, Cancel, Eliminate;
    private ImageView titleImage, titlePng;
    private Button butDeleta;

    public final int TYPE_TAKE_PHOTO = 1;//Uri获取类型判断
    public final int CODE_TAKE_PHOTO = 1;//相机RequestCode
    private static final int RESULT_PICK = 201;
    public Uri photoUri;

    private ArrayList<Boolean> selectItems; //用于存储已选中项目的位置
    private boolean isState;
    private PoorImageAdapter adapter;

    private String annentId, annexPathDown;

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;

    private boolean isGetData = false;

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
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            isGetData = true;
            //   这里可以做网络请求或者需要的数据刷新操作
            getData();
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isGetData) {
            //   这里可以做网络请求或者需要的数据刷新操作
            getData();
            isGetData = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
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
                adapter = new PoorImageAdapter(listBean, getActivity());

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
        titleImage = (ImageView) v.findViewById(R.id.img_title_image);
        Cancel = (TextView) v.findViewById(R.id.img_title_content);
        Upload = (TextView) v.findViewById(R.id.img_title_text);
        titlePng = (ImageView) v.findViewById(R.id.img_title_png_1);
        Select = (TextView) v.findViewById(R.id.img_title_select);
        butDeleta = (Button) v.findViewById(R.id.btn_del);
        Eliminate = (TextView) v.findViewById(R.id.img_title_eliminate);

        Upload.setOnClickListener(this);
        Cancel.setOnClickListener(this);
        Select.setOnClickListener(this);
        butDeleta.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);
        Eliminate.setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public ArrayList<Boolean> getSelectItems() {
        return selectItems;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_title_text:
                showListDialog();
                break;
            case R.id.img_title_select:
                setSelectAll(true);
                break;
            case R.id.img_title_content:
                selectItems.clear();
                adapter.setIsState(false);
                setState(false);
                break;
            case R.id.img_title_eliminate:
                setSelectAll(false);
                break;
            case R.id.btn_del:
                delSelections();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (isState) {
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_box);
            if (checkBox.isChecked()) {
                checkBox.setChecked(false);
                selectItems.set(position, false);
            } else {
                checkBox.setChecked(true);
                selectItems.set(position, true);
            }
            adapter.notifyDataSetChanged();
            setSelectNum();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

        if (!isState) {
            selectItems = new ArrayList<>();
            for (int i = 0; i < listBean.size(); i++) {
                selectItems.add(false);
            }
            CheckBox box = (CheckBox) view.findViewById(R.id.check_box);
            box.setChecked(true);
            selectItems.set(position, true);
            setState(true);
            adapter.setIsState(true);
            setSelectNum();
        }
        return false;
    }

    //设置当前状态 是否在多选模式
    private void setState(boolean b) {
        isState = b;
        if (b) {
            titleImage.setVisibility(View.GONE);
            Cancel.setText("取消");
            Cancel.setOnClickListener(this);
            Select.setVisibility(View.VISIBLE);
            titlePng.setVisibility(View.VISIBLE);
            butDeleta.setVisibility(View.VISIBLE);
        } else {
            titleImage.setVisibility(View.VISIBLE);
            Cancel.setText("图片管理");
            Select.setVisibility(View.GONE);
            titlePng.setVisibility(View.GONE);
            butDeleta.setVisibility(View.GONE);
        }
    }

    //显示已选项数目
    private void setSelectNum() {
        int num = 0;
        for (Boolean b : selectItems) {
            if (b)
                num++;
        }
        LogUtil.v("setselectnum", "已选择" + num + "项");
    }

    //全选
    private void setSelectAll(boolean b) {
        for (int i = 0; i < selectItems.size(); i++) {
            selectItems.set(i, b);
            adapter.notifyDataSetChanged();
            setSelectNum();
        }
        Select.setVisibility(b ? View.GONE : View.VISIBLE);
        Eliminate.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    //删除
    private void delSelections() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!selectItems.contains(true)) {
            builder.setTitle("提示").setMessage("当前未选中项目").setPositiveButton("确认", null).create().show();
        } else {
            builder.setTitle("提示");
            builder.setMessage("确认删除所选项目？");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    for (int i = 0; i < listBean.size(); i++) {
                        if (selectItems.get(i)) {
                            deletePhoto(listBean);
                            listBean.set(i, null);
                        }
                    }
                    while (listBean.contains(null)) {
                        listBean.remove(null);
                    }
                    selectItems = new ArrayList<>();
                    for (int i = 0; i < listBean.size(); i++) {
                        selectItems.add(false);
                    }

                    adapter.setData(listBean);
                    adapter.notifyDataSetChanged();
                    if (listBean.size() == 0) {
                        adapter.setIsState(false);
                        setState(false);
                        return;
                    }
                }
            });
            builder.setNegativeButton("取消", null);
            builder.create().show();
        }

    }

    //删除服务器文件
    private void deletePhoto(List<Inglists> data) {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        for (int i = 0; i < data.size(); i++) {
            annentId = listBean.get(i).getAnnentid();
            annexPathDown = listBean.get(i).getAnnexPathDown();
            params.addBodyParameter("annentId", annentId);
            params.addBodyParameter("annexPathDown", annexPathDown);
        }
        httpUtils.send(HttpMethod.POST, Constant.URL_DELETE_PHOTO, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.v("DELETEPHOTOHTTP", "onSuccess：" + responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("DELETEPHOTOHTTP", "onFailure：" + s);
            }
        });
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
                        accessToCameraRights();
                        break;
                    case 1:
                        accessToAlbumRights();
                        break;
                }
            }
        });
        listDialog.show();
    }

    private void accessToCameraRights() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //权限发生了改变 true  //  false 小米
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(getActivity()).setTitle("title")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 请求授权
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
            }
        } else {
            autoObtainCameraPermission();
        }
    }

    private void accessToAlbumRights(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, RESULT_PICK);

        } else {
            autoObtainAlbumPermission();
        }
    }

    /**
     * @param requestCode
     * @param permissions  请求的权限
     * @param grantResults 请求权限返回的结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            // camear 权限回调
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 表示用户授权
                Toast.makeText(getActivity(), " user Permission", Toast.LENGTH_SHORT).show();
                autoObtainCameraPermission();
            } else {
                //用户拒绝权限
                Toast.makeText(getActivity(), " no Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void autoObtainAlbumPermission(){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getActivity(), SampleCameraActivity.class);
        bundle.putString("entityId", entityId);
        intent.putExtras(bundle);
        startActivity(intent);
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
                ToastUtil.showInfo(getActivity(), "上传成功！");
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.v("SAMPLE_CAMERA_HTTP", "onFailure：" + s);
                ToastUtil.showInfo(getActivity(), "上传失败！");
            }
        });
    }


    public class PoorImageAdapter extends BaseAdapter {
        private Context context;

        private List<Inglists> listBean;
        private BitmapUtils bitmapUtils;

        private boolean isState = false;

        public PoorImageAdapter(List<Inglists> listBean, Context context) {
            super();
            this.listBean = listBean;
            this.context = context;
        }

        public void setData(List<Inglists> listBean) {
            this.listBean = listBean;
        }

        public void setIsState(boolean isState) {
            this.isState = isState;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return listBean != null ? listBean.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return listBean.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            Inglists inglists = listBean.get(position);
            if (convertView == null) {

                viewHolder = new ViewHolder();
                // 获得容器
                convertView = LayoutInflater.from(this.context).inflate(R.layout.grid_view_item, null);

                // 初始化组件
                viewHolder.image = (ImageView) convertView.findViewById(R.id.image_grid_item);
                viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.check_box);
                // 给converHolder附加一个对象
                convertView.setTag(viewHolder);
            } else {
                // 取得converHolder附加的对象
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // 给组件设置资源
            bitmapUtils = new BitmapUtils(context);    //创建BitmapUtils对象，通过xUtils框架获取
            bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);   //设置图片清晰度
            bitmapUtils.display(viewHolder.image, inglists.getAnnexPath());

            viewHolder.checkBox.setVisibility(isState ? View.VISIBLE : View.GONE);

            ArrayList<Boolean> list = getSelectItems();
            if (selectItems != null) {
                LogUtil.v("selectItemssssssssss", "selectItems==2==" + selectItems.size());
                if (selectItems.size() != 0) {
                    LogUtil.v("selectItemssssssssss", "selectItems==3==" + selectItems.get(position).toString());
                    viewHolder.checkBox.setChecked(selectItems.get(position));
                }
            }
            return convertView;
        }

        class ViewHolder {
            public ImageView image;
            public CheckBox checkBox;
        }

    }
}
