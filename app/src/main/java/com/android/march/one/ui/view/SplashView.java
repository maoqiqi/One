package com.android.march.one.ui.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.march.one.Constants;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.ui.activity.MainActivity;
import com.bumptech.glide.Glide;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class SplashView extends RootView<BasePresenter> {

    @BindView(R.id.ivSplash)
    ImageView ivSplash;
    @BindView(R.id.tvJump)
    TextView tvJump;

    private final int COUNT = 3;
    private volatile boolean isFirstStartMain = false;

    private CompositeSubscription compositeSubscription;

    public SplashView(Context context) {
        super(context);
    }

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_splash;
    }

    @Override
    public void back() {
        // super.back();
        // 禁止回退
    }

    public void setData() {
        // 随机加载图片
        int i = new Random().nextInt(Constants.URLS.length);
        Glide.with(getContext()).load(Constants.URLS[i]).placeholder(R.drawable.ic_splash_placeholder).into(ivSplash);

        // ---------------------------------------------------------------------------------------------------------------
        // timer：创建型操作符,用于延时执行任务。
        // interval：创建型操作符,用于周期执行任务。
        // delay：辅助型操作,用于延时传递数据。

        // 1s 之后执行任务
        /*tvJump.setVisibility(View.GONE);
        Observable.timer(1000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            Log.e("info", aLong + "");
            tvJump.setVisibility(View.VISIBLE);
        });*/

        // 第一次任务执行前有 1s 的间隔,每隔 1s 执行一次任务,只执行三次
        /*tvJump.setText("跳过 " + count);
        Observable.interval(1000, TimeUnit.MILLISECONDS).take(3).subscribe(aLong -> {
            Log.e("info", aLong + "");
            tvJump.setText("跳过 " + (2 - aLong));
        });*/

        // 立即执行第一次任务,每隔 1s 执行一次任务,只执行三次
        /*tvJump.setText("跳过 " + count);
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS).take(3).subscribe(aLong -> {
            Log.e("info", aLong + "");
            tvJump.setText("跳过 " + (2 - aLong));
        });*/
        // ---------------------------------------------------------------------------------------------------------------

        compositeSubscription = new CompositeSubscription();

        tvJump.setVisibility(View.GONE);
        // 1s 之后显示按钮
        Observable.timer(1000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> showView());
    }

    // 显示跳过按钮
    private void showView() {
        tvJump.setVisibility(View.VISIBLE);
        tvJump.setText("跳过 " + COUNT);
        tvJump.setOnClickListener(v -> startMainActivity());
        // 间隔 1s 执行任务,每隔 1s 执行一次任务
        compositeSubscription.add(Observable.interval(1000, TimeUnit.MILLISECONDS).take(COUNT).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            tvJump.setText("跳过 " + (COUNT - 1 - aLong));
            if (COUNT - 1 - aLong == 0) {
                tvJump.setVisibility(View.GONE);
                startMainActivity();
            }
        }));
    }

    // 跳转到主页面,并且把当前页面关闭掉
    private void startMainActivity() {
        if (!isFirstStartMain) {
            isFirstStartMain = true;
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
            activity.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        compositeSubscription.unsubscribe();
    }
}