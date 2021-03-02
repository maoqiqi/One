package com.android.march.one;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.march.one.interfaces.ILoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 根布局
 */
public abstract class RootView<P> extends LinearLayout implements ILoading {

    private final String TAG = getClass().getSimpleName();

    protected final AppCompatActivity activity;

    private boolean isActive = false;
    protected P presenter;

    protected Unbinder unbinder;

    public RootView(Context context) {
        this(context, null);
    }

    public RootView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.activity = (AppCompatActivity) getContext();
        Log.d(TAG, TAG + "-->RootView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)");
        if (getLayout() == 0) return;
        inflate(context, getLayout(), this);
        unbinder = ButterKnife.bind(this);
    }

    // 得到布局
    protected abstract int getLayout();

    protected void setToolBar(Toolbar toolbar) {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> back());
    }

    // 返回
    public void back() {
        com.codearms.maoqiqi.android.ActivityUtils.getActivity(this).finish();
        // 不退出程序,进入后台
        // ((AppCompatActivity) getContext()).moveTaskToBack(true);
    }

    // onAttachedToWindow运行在onResume之后
    @Override
    protected void onAttachedToWindow() {
        Log.d(TAG, TAG + "-->onAttachedToWindow()");
        super.onAttachedToWindow();
        isActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.d(TAG, TAG + "-->onDetachedFromWindow()");
        super.onDetachedFromWindow();
        isActive = false;
    }

    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    public boolean isActive() {
        return isActive;
    }

    // 清除
    public void clean() {
        if (unbinder != null) unbinder.unbind();
    }

    protected String getString(int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }
}