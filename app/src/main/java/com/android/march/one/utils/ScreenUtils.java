package com.android.march.one.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

// Helper class for screen
public class ScreenUtils {

    private static Context context;

    private ScreenUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void init(Context context) {
        ScreenUtils.context = context;
    }

    @Deprecated
    public static int getScreenWidth(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    @Deprecated
    public static int getScreenHeight(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }

    // Getting screen width
    public static int getScreenWidth() {
        if (context == null)
            throw new RuntimeException("Before getting screen width you need to call ScreenUtils.init(context)");
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    // Getting screen height
    public static int getScreenHeight() {
        if (context == null)
            throw new RuntimeException("Before getting screen height you need to call ScreenUtils.init(context)");
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    // Getting status bar height
    public static int getStatusBarHeight() {
        if (context == null)
            throw new RuntimeException("Before getting status bar height you need to call ScreenUtils.init(context)");
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}