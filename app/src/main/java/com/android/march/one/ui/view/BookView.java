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
import com.android.march.one.ui.fragment.BookListFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import butterknife.BindView;

public class BookView extends RootView<BasePresenter> {

    private String[] titles = {"文学", "生活", "名著"};

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private SectionsPagerAdapter adapter;

    public BookView(Context context) {
        super(context);
    }

    public BookView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_book;
    }

    public void setData(Fragment fragment) {
        MainActivity mainActivity = (MainActivity) getContext();
        mainActivity.associateDrawerLayout(toolbar);

        if (fragment == null) {
            adapter = new SectionsPagerAdapter(activity.getSupportFragmentManager());
        } else {
            adapter = new SectionsPagerAdapter(fragment.getChildFragmentManager());
        }

        for (String title : titles) {
            adapter.addFragment(title, getFragment(title));
        }

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new MyPageChangeListener());

        tabLayout.setTabData(titles);
        tabLayout.setOnTabSelectListener(new MyTabSelectListener());
    }

    private Fragment getFragment(String title) {
        return BookListFragment.newInstance("", title);
    }

    public void search() {
        SearchActivity.start(getContext(), R.color.colorBook);
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