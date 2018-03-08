package com.android.march.one;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

public class StatusBarUtils {

    // 设置状态栏颜色
    public static void setStatusColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.0及以上,不设置透明状态栏,会有半透明阴影
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置statusBar的背景色
            activity.getWindow().setStatusBarColor(color);
        }
    }

    // 将状态栏清空
    public static void setStatusFullscreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN:Activity全屏显示,但是状态栏不会被覆盖掉,而是正常显示,只是Activity顶端布局会被覆盖住
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            // 5.0及以上,不设置透明状态栏,会有半透明阴影
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置透明状态栏后,会有颜色,再次设置颜色
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}