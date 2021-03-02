package com.android.march.one.ui.view;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.ui.activity.MainActivity;
import com.android.march.one.ui.activity.SearchActivity;
import com.android.march.one.ui.adapter.SectionsPagerAdapter;
import com.android.march.one.ui.fragment.MovieListFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import butterknife.BindView;

public class MovieView extends RootView<BasePresenter> {

    private String[] titles = {"正在热映", "即将上映"};

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private SectionsPagerAdapter adapter;

    public MovieView(Context context) {
        super(context);
    }

    public MovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_movie;
    }

    public void setData(Fragment fragment) {
        MainActivity mainActivity = (MainActivity) getContext();
        mainActivity.associateDrawerLayout(toolbar);

        if (fragment == null) {
            adapter = new SectionsPagerAdapter(activity.getSupportFragmentManager());
        } else {
            adapter = new SectionsPagerAdapter(fragment.getChildFragmentManager());
        }

        for (int i = 0; i < titles.length; i++) {
            adapter.addFragment(titles[i], getFragment(i));
        }

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new MyPageChangeListener());

        tabLayout.setTabData(titles);
        tabLayout.setOnTabSelectListener(new MyTabSelectListener());
    }

    private Fragment getFragment(int type) {
        return MovieListFragment.newInstance(type, "561", "", "");
    }

    public void search() {
        SearchActivity.start(getContext(), R.color.colorMovie);
    }

    private final class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tabLayout.setCurrentTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private final class MyTabSelectListener implements OnTabSelectListener {

        @Override
        public void onTabSelect(int position) {
            viewPager.setCurrentItem(position);
        }

        @Override
        public void onTabReselect(int position) {

        }
    }
}