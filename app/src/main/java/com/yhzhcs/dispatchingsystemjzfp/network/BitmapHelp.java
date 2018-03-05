package com.yhzhcs.dispatchingsystemjzfp.network;

import android.content.Context;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by Administrator on 2018/1/31.
 */

public class BitmapHelp {
    private BitmapHelp() {
    }

    private static BitmapUtils bitmapUtils;

    public static BitmapUtils getBitmapUtils(Context appContext) {
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils(appContext);
        }
        return bitmapUtils;
    }
}
