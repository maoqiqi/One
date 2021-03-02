package com.android.march.one.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public final class SectionsPagerAdapter extends FragmentPagerAdapter {

    // 注意,FragmentPagerAdapter是一次性将所有页卡都加载完毕,没有销毁的。
    // 而FragmentStatePagerAdapter并不是一次性将页卡都加载完毕,而是默认每次加载进三个页卡,当前页卡被滑动消失就会被销毁。
    // 这是它们的区别。因此如果页卡较多,建议采用FragmentStatePagerAdapter适配器。

    private List<Fragment> mFragments;

    private List<String> mFragmentTitles;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragmentTitles = new ArrayList<>();
    }

    public void addFragment(String title, Fragment fragment) {
        mFragmentTitles.add(title);
        mFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}