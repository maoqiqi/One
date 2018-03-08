package com.android.march.one.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.march.one.ActivityUtils;
import com.android.march.one.AppBarStateChangeListener;
import com.android.march.one.OneUtils;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.presenter.contract.GankContract;
import com.android.march.one.ui.activity.GankCollectionActivity;
import com.android.march.one.ui.activity.SearchActivity;
import com.android.march.one.ui.adapter.SectionsPagerAdapter;
import com.android.march.one.ui.fragment.GankListFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;

public class GankView extends RootView<GankContract.Presenter> implements GankContract.View {

    private String[] titles = getResources().getStringArray(R.array.gank_classify);

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.ivHeader)
    ImageView ivHeader;
    @BindView(R.id.tvSearchGank)
    TextView tvSearchGank;
    @BindView(R.id.ivCollection)
    ImageView ivCollection;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fabRandom)
    FloatingActionButton fabRandom;

    private Animation animation;

    private SectionsPagerAdapter adapter;

    public GankView(Context context) {
        super(context);
    }

    public GankView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_gank;
    }

    public void setData(String type) {
        setToolBar(toolbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    fabRandom.hide();
                } else if (state == State.EXPANDED) {
                    fabRandom.show();
                } else {
                    fabRandom.show();
                }
            }
        });

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        animation.setInterpolator(new LinearInterpolator());
        getRandomHeaderImage();

        adapter = new SectionsPagerAdapter(activity.getSupportFragmentManager());

        int position = 0;
        for (int i = 0; i < titles.length; i++) {
            if (type.equals(titles[i])) {
                position = i;
                break;
            }
        }

        for (String title : titles) {
            adapter.addFragment(title, getFragment(title));
        }

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(position);

        tabLayout.setupWithViewPager(viewPager);

        tvSearchGank.setOnClickListener(v -> SearchActivity.start(getContext(), R.color.colorHome));
        ivCollection.setOnClickListener(v -> ActivityUtils.startActivity(getContext(), GankCollectionActivity.class));
        fabRandom.setOnClickListener(v -> getRandomHeaderImage());
    }

    private Fragment getFragment(String title) {
        String category = title;
        if (title.equals(titles[0])) category = "all";
        return GankListFragment.newInstance(category);
    }

    private void getRandomHeaderImage() {
        fabRandom.setEnabled(false);
        fabRandom.setImageResource(R.drawable.ic_loading);
        fabRandom.startAnimation(animation);

        presenter.getRandomHeaderImage(true);
    }

    @Override
    public void setHeaderImage(String url) {
        fabRandom.clearAnimation();
        fabRandom.setEnabled(true);
        fabRandom.setImageResource(R.drawable.ic_beauty);

        Glide.with(getContext()).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ivHeader.setImageBitmap(resource);
                int color = OneUtils.getLightColor(resource);
                collapsingToolbarLayout.setContentScrimColor(color);
                collapsingToolbarLayout.setStatusBarScrimColor(color);
                OneUtils.setTint(fabRandom, color);
            }
        });
    }
}