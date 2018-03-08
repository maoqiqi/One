package com.android.march.one.ui.view;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;

import butterknife.BindView;

public class SettingView extends RootView<BasePresenter> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rlSystemAnimation)
    RelativeLayout rlSystemAnimation;
    @BindView(R.id.scSystemAnimation)
    SwitchCompat scSystemAnimation;

    @BindView(R.id.rlLauncherPage)
    RelativeLayout rlLauncherPage;
    @BindView(R.id.tvLauncherPageDesc)
    TextView tvLauncherPageDesc;
    @BindView(R.id.scLauncherPage)
    SwitchCompat scLauncherPage;

    @BindView(R.id.rlLauncherPageRandom)
    RelativeLayout rlLauncherPageRandom;
    @BindView(R.id.tvLauncherPageRandomTitle)
    TextView tvLauncherPageRandomTitle;
    @BindView(R.id.tvLauncherPageRandomDesc)
    TextView tvLauncherPageRandomDesc;
    @BindView(R.id.scLauncherPageRandom)
    SwitchCompat scLauncherPageRandom;

    public SettingView(Context context) {
        super(context);
    }

    public SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_setting;
    }

    public void setData() {
        setToolBar(toolbar);
        rlSystemAnimation.setOnClickListener(v -> scSystemAnimation.setChecked(!scSystemAnimation.isChecked()));
        rlLauncherPage.setOnClickListener(v -> {
            scLauncherPage.setChecked(!scLauncherPage.isChecked());
            setLauncherPageRandomEnable();
        });
        rlLauncherPageRandom.setOnClickListener(v -> {
            scLauncherPageRandom.setChecked(!scLauncherPageRandom.isChecked());
            setLauncherPageRandomDesc();
        });

        scLauncherPage.setChecked(false);
        setLauncherPageRandomEnable();
        scLauncherPageRandom.setChecked(false);
        setLauncherPageRandomDesc();
    }

    private void setLauncherPageRandomEnable() {
        if (scLauncherPage.isChecked()) {
            tvLauncherPageDesc.setText("没有妹子太寂寞");
            rlLauncherPageRandom.setClickable(true);
            tvLauncherPageRandomTitle.setTextColor(getResources().getColor(R.color.colorTextEnable));
            tvLauncherPageRandomDesc.setTextColor(getResources().getColor(R.color.colorTextEnableGary));
        } else {
            tvLauncherPageDesc.setText("基佬怎么会需要妹子");
            rlLauncherPageRandom.setClickable(false);
            tvLauncherPageRandomTitle.setTextColor(getResources().getColor(R.color.colorTextUnEnable));
            tvLauncherPageRandomDesc.setTextColor(getResources().getColor(R.color.colorTextUnEnable));
        }
    }

    private void setLauncherPageRandomDesc() {
        if (scLauncherPageRandom.isChecked()) {
            tvLauncherPageRandomDesc.setText("偶尔来个惊喜就行");
        } else {
            tvLauncherPageRandomDesc.setText("我每次都要幸临，没毛病");
        }
    }
}