package com.android.march.one.ui.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.ui.fragment.BookFragment;
import com.android.march.one.ui.fragment.HomeFragment;
import com.android.march.one.ui.fragment.MovieFragment;
import com.android.march.one.ui.fragment.MusicFragment;
import com.android.march.one.ui.fragment.NewsFragment;

import butterknife.BindView;

public class MainView2 extends RootView<BasePresenter> {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private BookFragment bookFragment;
    private MusicFragment musicFragment;
    private MovieFragment movieFragment;

    private AppCompatActivity activity;
    // 上次切换的Fragment
    private Fragment mFragment;

    public MainView2(Context context) {
        super(context);
    }

    public MainView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_main_2;
    }

    public void setData() {
        activity = (AppCompatActivity) getContext();
        // 设置RadioGroup的监听
        radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        // 设置默认选中
        radioGroup.check(R.id.rbHome);
    }

    private final class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // 得到对应的fragment
            Fragment to = getFragment(checkedId);
            // 替换
            switchFragment(mFragment, to);
        }

        private Fragment getFragment(int checkedId) {
            switch (checkedId) {
                case R.id.rbHome:
                    if (homeFragment == null) homeFragment = HomeFragment.newInstance();
                    return homeFragment;
                case R.id.rbNews:
                    if (newsFragment == null) newsFragment = NewsFragment.newInstance();
                    return newsFragment;
                case R.id.rbBook:
                    if (bookFragment == null) bookFragment = BookFragment.newInstance();
                    return bookFragment;
                case R.id.rbMusic:
                    if (musicFragment == null) musicFragment = MusicFragment.newInstance();
                    return musicFragment;
                case R.id.rbMovie:
                    if (movieFragment == null) movieFragment = MovieFragment.newInstance();
                    return movieFragment;
            }
            return null;
        }

        private void switchFragment(Fragment from, Fragment to) {
            if (to != null && from != to) {//from != to才切换
                mFragment = to;
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();

                // from隐藏
                if (from != null)
                    ft.hide(from);

                if (!to.isAdded()) {
                    // 没有被添加,添加to
                    ft.add(R.id.flContent, to).commit();
                } else {
                    // 已经被添加,显示to
                    ft.show(to).commit();
                }
            }
        }
    }
}