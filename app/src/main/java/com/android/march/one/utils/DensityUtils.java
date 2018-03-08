package com.android.march.one.utils;

import android.content.Context;
import android.util.TypedValue;

// Helper class for Density
public class DensityUtils {

    private static Context context;

    private DensityUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void init(Context context) {
        DensityUtils.context = context;
    }

    public static int dp2px(float dpVal) {
        if (context == null)
            throw new RuntimeException("Before dp to px you need to call DensityUtils.init(context)");
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(float spVal) {
        if (context == null)
            throw new RuntimeException("Before sp to px you need to call DensityUtils.init(context)");
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }

    public static float px2dp(float pxVal) {
        if (context == null)
            throw new RuntimeException("Before px to dp you need to call DensityUtils.init(context)");
        return (pxVal / context.getResources().getDisplayMetrics().density);
    }

    public static float px2sp(float pxVal) {
        if (context == null)
            throw new RuntimeException("Before px to sp you need to call DensityUtils.init(context)");
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}