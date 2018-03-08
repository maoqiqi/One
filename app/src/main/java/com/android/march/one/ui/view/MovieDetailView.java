package com.android.march.one.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.march.one.OneUtils;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.adapter.TypeFactory;
import com.android.march.one.model.bean.MovieActorBean;
import com.android.march.one.model.bean.MovieDetailBean;
import com.android.march.one.model.bean.MovieListBean;
import com.android.march.one.presenter.contract.MovieDetailContract;
import com.android.march.one.ui.SpaceItemDecoration;
import com.android.march.one.ui.activity.WebViewActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MovieDetailView extends RootView<MovieDetailContract.Presenter> implements MovieDetailContract.View {

    private static final int MAX_LINE = 3; // TextView 默认最大展示行数

    @BindView(R.id.ivTitleBg)
    ImageView ivTitleBg;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.ivBg)
    ImageView ivBg;
    @BindView(R.id.ivMovie)
    ImageView ivMovie;
    @BindView(R.id.tvMovieTitle)
    TextView tvMovieTitle;
    @BindView(R.id.tvMovieGenre)
    TextView tvMovieGenre;
    @BindView(R.id.tvMovieYear)
    TextView tvMovieYear;
    @BindView(R.id.tvCountry)
    TextView tvCountry;
    @BindView(R.id.tvMovieRating)
    TextView tvMovieRating;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tvMovieNumRaters)
    TextView tvMovieNumRaters;
    @BindView(R.id.ivMovieSummary)
    ImageView ivMovieSummary;
    @BindView(R.id.tvMovieSummary)
    TextView tvMovieSummary;
    @BindView(R.id.recyclerViewMovieActor)
    RecyclerView recyclerViewMovieActor;

    private String title;
    private int distance;

    private boolean mMovieSummaryExpand = false;

    private String url = "";

    public MovieDetailView(Context context) {
        super(context);
    }

    public MovieDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_movie_detail;
    }

    public void setData(String id, String title, String imageUrl) {
        this.title = title;
        setToolBar(toolbar);

        Glide.with(getContext()).load(imageUrl).asBitmap().placeholder(R.drawable.ic_movie_placeholder).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ivMovie.setImageBitmap(resource);
                OneUtils.setBackground(ivTitleBg, resource);
                OneUtils.setBackground(ivBg, resource);
            }
        });

        int toolbarHeight = toolbar.getLayoutParams().height;
        int headerHeight = toolbarHeight + getResources().getDimensionPixelSize(R.dimen.status_bar_height);

        // 使背景图向上移动到图片的最低端,保留(toolbar+statusBar)的高度
        RelativeLayout.LayoutParams ivTitleBgParams = (RelativeLayout.LayoutParams) ivTitleBg.getLayoutParams();
        ivTitleBgParams.setMargins(0, -(ivTitleBgParams.height - headerHeight), 0, 0);
        // 设置透明度
        ivTitleBg.setAlpha(0f);

        // 获得移动距离(图片的高度-(toolbar+statusBar)的高度)
        distance = ivBg.getLayoutParams().height - headerHeight;
        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> scrollChangeHeader(scrollY));

        presenter.getMovieDetail(id);
    }

    // 根据页面滑动距离改变Header方法
    private void scrollChangeHeader(int scrolledY) {
        if (scrolledY < 0) scrolledY = 0;

        float alpha = Math.abs(scrolledY) * 1.0f / distance;

        if (scrolledY <= distance) {
            toolbar.setTitle("电影详情");
            ivTitleBg.setAlpha(alpha);
        } else {
            toolbar.setTitle(title);
            ivTitleBg.setAlpha(1f);
        }
    }

    @Override
    public void setMovieDetail(MovieDetailBean movieDetailBean) {
        url = movieDetailBean.getShareUrl();

        tvMovieTitle.setText(movieDetailBean.getTitle());
        tvMovieGenre.setText("类型：" + OneUtils.formatGenre(movieDetailBean.getGenreList()));
        tvMovieYear.setText("上映日期：" + movieDetailBean.getYear());
        tvCountry.setText("制片国家/地区：" + OneUtils.formatCountry(movieDetailBean.getCountryList()));
        ratingBar.setRating(movieDetailBean.getRatingBean().getAverage() / 2);
        tvMovieRating.setText(movieDetailBean.getRatingBean().getAverage().toString());
        tvMovieNumRaters.setText(movieDetailBean.getCollectCount() + "人");

        OneUtils.setTextImageSwitch(MAX_LINE, movieDetailBean.getSummary(), tvMovieSummary, ivMovieSummary);
        ivMovieSummary.setOnClickListener(v -> {
            mMovieSummaryExpand = !mMovieSummaryExpand;
            OneUtils.imageSwitch(getContext(), MAX_LINE, mMovieSummaryExpand, tvMovieSummary, ivMovieSummary);
        });

        List<MovieActorBean> movieActorList = new ArrayList();
        MovieActorBean bean;
        MovieListBean.DirectorBean directorBean;
        MovieListBean.CastBean castBean;
        for (int i = 0; i < movieDetailBean.getDirectorBeanList().size(); i++) {
            bean = new MovieActorBean();
            directorBean = movieDetailBean.getDirectorBeanList().get(i);
            bean.setId(directorBean.getId());
            bean.setAlt(directorBean.getAlt());
            bean.setName(directorBean.getName());
            bean.setAvatarBean(directorBean.getAvatarBean());
            bean.setTypeName("导演");
            movieActorList.add(bean);
        }
        for (int i = 0; i < movieDetailBean.getCastBeanList().size(); i++) {
            bean = new MovieActorBean();
            castBean = movieDetailBean.getCastBeanList().get(i);
            bean.setId(castBean.getId());
            bean.setAlt(castBean.getAlt());
            bean.setName(castBean.getName());
            bean.setAvatarBean(castBean.getAvatarBean());
            bean.setTypeName("主演");
            movieActorList.add(bean);
        }

        recyclerViewMovieActor.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerViewMovieActor.setHasFixedSize(false);
        recyclerViewMovieActor.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMovieActor.setNestedScrollingEnabled(false);
        recyclerViewMovieActor.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.sixteen)));
        recyclerViewMovieActor.setAdapter(new RecyclerViewAdapter<>(movieActorList, new TypeFactory().setMovieActorData()));
    }

    @Override
    public void moreInfo() {
        if (url != "") WebViewActivity.start(activity, R.color.colorMovie, "", url);
    }
}