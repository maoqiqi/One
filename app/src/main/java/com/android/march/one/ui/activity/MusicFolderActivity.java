package com.android.march.one.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.MusicFolderView;

public class MusicFolderActivity extends BaseActivity {

    private MusicFolderView folderView;

    public static void start(Context context, String folderPath) {
        Bundle bundle = new Bundle();
        bundle.putString("folderPath", folderPath);
        Intent intent = new Intent(context, MusicFolderActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(folderView = new MusicFolderView(this));
        StatusBarUtils.setStatusFullscreen(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            folderView.setData(bundle.getString("folderPath"));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        folderView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        folderView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        folderView.back();
    }
}