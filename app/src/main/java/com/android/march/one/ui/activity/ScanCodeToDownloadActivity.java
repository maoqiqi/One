package com.android.march.one.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.march.one.R;
import com.android.march.one.ShareUtils;
import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.ScanCodeToDownloadView;

public class ScanCodeToDownloadActivity extends BaseActivity {

    private ScanCodeToDownloadView scanCodeToDownloadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(scanCodeToDownloadView = new ScanCodeToDownloadView(this));
        StatusBarUtils.setStatusFullscreen(this);
        scanCodeToDownloadView.setData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scan_code_to_download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                ShareUtils.shareText(this, getString(R.string.share_download_content, getString(R.string.download_url)));
                return true;
            case R.id.menu_star:
                WebViewActivity.start(this, R.color.colorNavigation, "", getString(R.string.project_url));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        scanCodeToDownloadView.back();
    }
}