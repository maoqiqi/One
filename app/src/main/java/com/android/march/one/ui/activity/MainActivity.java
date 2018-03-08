package com.android.march.one.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.march.one.ActivityUtils;
import com.android.march.one.R;
import com.android.march.one.StatusBarUtils;
import com.android.march.one.T;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.MainView;
import com.android.march.one.ui.view.MainView2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Unbinder unbinder;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.mainView)
    MainView mainView;
    @BindView(R.id.mainView2)
    MainView2 mainView2;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.tvSetting)
    TextView tvSetting;
    @BindView(R.id.tvMode)
    TextView tvMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setStatusFullscreen(this);
        unbinder = ButterKnife.bind(this);

        // 支持滑动切换
        mainView.setVisibility(View.VISIBLE);
        mainView2.setVisibility(View.GONE);
        mainView.setData();

        // 不支持滑动切换
        /*mainView.setVisibility(View.GONE);
        mainView2.setVisibility(View.VISIBLE);
        mainView2.setData();*/

        navigationView.setNavigationItemSelectedListener(this);

        View navigationHeader = navigationView.getHeaderView(0);
        navigationHeader.findViewById(R.id.ivScanCodeToDownload).setOnClickListener(this);
        navigationHeader.findViewById(R.id.ivProject).setOnClickListener(this);

        tvSetting.setOnClickListener(this);
        tvMode.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_project_introduction:
                ActivityUtils.startActivity(this, ProjectIntroductionActivity.class);
                return true;
            case R.id.nav_update_description:
                ActivityUtils.startActivity(this, UpdateDescriptionActivity.class);
                return true;
            case R.id.nav_scan_code_to_download:
                ActivityUtils.startActivity(this, ScanCodeToDownloadActivity.class);
                return true;
            case R.id.nav_problem_feedback:
                ActivityUtils.startActivity(this, ProblemFeedbackActivity.class);
                return true;
            case R.id.nav_about:
                ActivityUtils.startActivity(this, AboutActivity.class);
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivScanCodeToDownload:
                ActivityUtils.startActivity(this, ScanCodeToDownloadActivity.class);
                break;
            case R.id.ivProject:
                WebViewActivity.start(this, R.color.colorNavigation, "", getString(R.string.project_url));
                break;
            case R.id.tvSetting:
                ActivityUtils.startActivity(this, SettingActivity.class);
                break;
            case R.id.tvMode:
                T.info(this, "开发中");
                break;
        }
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    // 将Toolbar 与 DrawerLayout 关联
    public void associateDrawerLayout(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}