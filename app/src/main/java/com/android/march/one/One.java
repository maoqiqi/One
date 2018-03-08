package com.android.march.one;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;

/**
 * Created by March on 2018/1/22.
 */
public class One {

    private static Context context;

    private One() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void init(Context context) {
        One.context = context;
    }

    public static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    // 检查权限
    public static boolean checkSelfPermission(String permission) {
        if (context == null) {
            throw new RuntimeException("Before checking permissions you need to call One.init(context)");
        }
        return isMarshmallow() && PackageManager.PERMISSION_GRANTED == context.checkSelfPermission(permission);
    }

    // 是否需要显示请求权限的理由
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String permission) {
        return isMarshmallow() && activity.shouldShowRequestPermissionRationale(permission);
    }

    // 请求权限
    public static void requestPermissions(final Activity activity, final String[] permissions, final int requestCode) {
        if (activity == null) {
            throw new RuntimeException("activity mustn't null");
        }
        if (isMarshmallow()) {
            activity.requestPermissions(permissions, requestCode);
        }
    }

    // 请求权限
    public static void requestPermissions(final Fragment fragment, final String[] permissions, final int requestCode) {
        if (fragment == null) {
            throw new RuntimeException("fragment mustn't null");
        }
        if (isMarshmallow()) {
            fragment.requestPermissions(permissions, requestCode);
        }
    }

    // 请求权限
    public static void requestPermissions2(final Fragment fragment, final String[] permissions, final int requestCode) {
        if (fragment.getParentFragment() == null) {
            throw new RuntimeException("fragment.getParentFragment() mustn't null");
        }
        if (isMarshmallow()) {
            fragment.getParentFragment().requestPermissions(permissions, requestCode);
        }
    }
}