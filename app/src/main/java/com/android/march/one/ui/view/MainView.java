package com.android.march.one.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.ui.adapter.SectionsPagerAdapter;
import com.android.march.one.ui.fragment.BookFragment;
import com.android.march.one.ui.fragment.HomeFragment;
import com.android.march.one.ui.fragment.MovieFragment;
import com.android.march.one.ui.fragment.MusicFragment;
import com.android.march.one.ui.fragment.NewsFragment;

import butterknife.BindView;

public class MainView extends RootView<BasePresenter> {

    private int[] rbIds = {R.id.rbHome, R.id.rbNews, R.id.rbBook, R.id.rbMusic, R.id.rbMovie};

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    // 选中Fragment对应的位置
    private int position = 0;
    private SectionsPagerAdapter adapter;

    public MainView(Context context) {
        super(context);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_main;
    }

    public void setData() {
        adapter = new SectionsPagerAdapter(activity.getSupportFragmentManager());
        adapter.addFragment("HomeFragment", HomeFragment.newInstance());
        adapter.addFragment("NewsFragment", NewsFragment.newInstance());
        adapter.addFragment("BookFragment", BookFragment.newInstance());
        adapter.addFragment("MusicFragment", MusicFragment.newInstance());
        adapter.addFragment("MovieFragment", MovieFragment.newInstance());

        // 设置ViewPager
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());

        // 设置默认选中
        radioGroup.check(rbIds[position]);
        // 设置RadioGroup的监听
        radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
    }

    private final class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            radioGroup.check(rbIds[position]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private final class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rbHome:
                    position = 0;
                    break;
                case R.id.rbNews:
                    position = 1;
                    break;
                case R.id.rbBook:
                    position = 2;
                    break;
                case R.id.rbMusic:
                    position = 3;
                    break;
                case R.id.rbMovie:
                    position = 4;
                    break;
            }
            viewPager.setCurrentItem(position);
        }
    }
}