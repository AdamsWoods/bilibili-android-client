package com.hotbitmapgg.bilibili.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

/**
 * Created by hcc on 16/8/20 12:18
 * 100332338@qq.com
 * <p/>
 * 一个简单的SnackBar工具类
 */
public class SnackbarUtil {
    public static void showMessage(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }
}
