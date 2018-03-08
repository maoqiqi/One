package com.android.march.one;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ShareUtils {

    public static void shareText(Context context, int stringRes) {
        shareText(context, context.getString(stringRes));
    }

    /**
     * 使用系统发送分享数据
     *
     * @param context
     * @param extraText
     */
    public static void shareText(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(intent.EXTRA_TEXT, extraText);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent.createChooser(intent, "分享"));
    }

    public static void shareImage(Context context, Uri uri, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(intent.EXTRA_STREAM, uri);
        intent.setType("image/jpeg");
        context.startActivity(intent.createChooser(intent, title));
    }
}