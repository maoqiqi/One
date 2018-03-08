package com.android.march.one.ui.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.ui.activity.MainActivity;
import com.android.march.one.ui.activity.SearchActivity;
import com.android.march.one.ui.adapter.SectionsPagerAdapter;
import com.android.march.one.ui.fragment.MusicListFragment;

import butterknife.BindView;

public class MusicView extends RootView<BasePresenter> {

    private final String[] titles = getResources().getStringArray(R.array.music_array);

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private SectionsPagerAdapter adapter;

    public MusicView(Context context) {
        super(context);
    }

    public MusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_music;
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

        tabLayout.setupWithViewPager(viewPager);
    }

    private Fragment getFragment(int type) {
        return MusicListFragment.newInstance(type);
    }

    public void search() {
        SearchActivity.start(getContext(), R.color.colorMusic);
    }
}