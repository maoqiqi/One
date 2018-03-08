package com.android.march.one.ui.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.ui.activity.MainActivity;
import com.android.march.one.ui.activity.SearchActivity;
import com.android.march.one.ui.adapter.SectionsPagerAdapter;
import com.android.march.one.ui.fragment.NewsListFragment;

import butterknife.BindView;

public class NewsView extends RootView<BasePresenter> {

    private String[] titles = {"推荐", "热点", "社会", "娱乐", "情感", "故事", "小说", "星座", "科技", "财经", "体育", "军事", "教育", "历史", "搞笑", "奇闻", "游戏", "时尚", "养生", "美食", "旅行"};

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.ivNewsCategory)
    ImageView ivNewsCategory;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private SectionsPagerAdapter adapter;

    public NewsView(Context context) {
        super(context);
    }

    public NewsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_news;
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

        // 设置ViewPager
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(0);

        tabLayout.setupWithViewPager(viewPager);
    }

    public void search() {
        SearchActivity.start(getContext(), R.color.colorNews);
    }

    private Fragment getFragment(String title) {
        return NewsListFragment.newInstance(title);
    }
}