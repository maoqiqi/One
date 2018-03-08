package com.android.march.one.ui.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.android.march.one.ActivityUtils;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.ui.activity.ProjectIntroductionActivity;
import com.android.march.one.ui.activity.UpdateDescriptionActivity;
import com.android.march.one.ui.activity.WebViewActivity;

import butterknife.BindView;

public class AboutView extends RootView<BasePresenter> implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvVersionName)
    TextView tvVersionName;
    @BindView(R.id.tvProjectIntroduction)
    TextView tvProjectIntroduction;
    @BindView(R.id.tvCheckUpdate)
    TextView tvCheckUpdate;
    @BindView(R.id.tvUpdateDescription)
    TextView tvUpdateDescription;
    @BindView(R.id.tvStar)
    TextView tvStar;
    @BindView(R.id.tvGank)
    TextView tvGank;
    @BindView(R.id.tvDouBan)
    TextView tvDouBan;

    public AboutView(Context context) {
        super(context);
    }

    public AboutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_about;
    }

    public void setData() {
        setToolBar(toolbar);
        try {
            String versionName = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName;
            tvVersionName.setText(getString(R.string.current_version, versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String gankText = "<font color='#191919'>《<u>代码家 · 干货集中营</u>》</font>";
        tvGank.setText(Html.fromHtml(gankText));

        String douBanText = "<font color='#191919'>《<u>豆瓣开发者服务使用条款</u>》</font>";
        tvDouBan.setText(Html.fromHtml(douBanText));

        tvProjectIntroduction.setOnClickListener(this);
        tvCheckUpdate.setOnClickListener(this);
        tvUpdateDescription.setOnClickListener(this);
        tvStar.setOnClickListener(this);
        tvGank.setOnClickListener(this);
        tvDouBan.setOnClickListener(this);
    }

    private void setUnderLine(TextView textView) {
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView.getPaint().setAntiAlias(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvProjectIntroduction:
                ActivityUtils.startActivity(activity, ProjectIntroductionActivity.class);
                break;
            case R.id.tvCheckUpdate:
                WebViewActivity.start(activity, R.color.colorNavigation, "", getString(R.string.project_url));
                break;
            case R.id.tvUpdateDescription:
                ActivityUtils.startActivity(activity, UpdateDescriptionActivity.class);
                break;
            case R.id.tvStar:
                WebViewActivity.start(activity, R.color.colorNavigation, "", getString(R.string.project_url));
                break;
            case R.id.tvGank:
                WebViewActivity.start(activity, R.color.colorNavigation, "", getString(R.string.gank_api));
                break;
            case R.id.tvDouBan:
                WebViewActivity.start(activity, R.color.colorNavigation, "", getString(R.string.dou_ban_terms));
                break;
                // 谢谢，您没有安装支付宝客户端
        }
    }
}