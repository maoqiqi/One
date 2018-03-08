package com.android.march.one;

import android.content.Context;

import es.dmoral.toasty.Toasty;

public class T {

    public static void info(Context context, CharSequence message) {
        // Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        Toasty.info(context, message).show();
    }

    public static void warning(Context context, CharSequence message) {
        Toasty.warning(context, message).show();
    }

    public static void error(Context context, CharSequence message) {
        Toasty.error(context, message).show();
    }

    public static void normal(Context context, CharSequence message) {
        Toasty.normal(context, message).show();
    }

    public static void success(Context context, CharSequence message) {
        Toasty.success(context, message).show();
    }
}