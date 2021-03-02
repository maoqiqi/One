package com.android.march.one.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
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
import com.android.march.one.model.bean.ActorBean;
import com.android.march.one.model.bean.DirectorBean;
import com.android.march.one.model.bean.MovieDetailBean;
import com.android.march.one.presenter.contract.MovieDetailContract;
import com.android.march.one.ui.SpaceItemDecoration;
import com.android.march.one.ui.activity.WebViewActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

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
        presenter.getMovieDetailPersonList(id);
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
//        url = movieDetailBean.getShareUrl();
//
//        tvMovieTitle.setText(movieDetailBean.getTitle());
//        tvMovieGenre.setText("类型：" + OneUtils.formatGenre(movieDetailBean.getGenreList()));
//        tvMovieYear.setText("上映日期：" + movieDetailBean.getYear());
//        tvCountry.setText("制片国家/地区：" + OneUtils.formatCountry(movieDetailBean.getCountryList()));
//        ratingBar.setRating(movieDetailBean.getRatingBean().getAverage() / 2);
//        tvMovieRating.setText(movieDetailBean.getRatingBean().getAverage().toString());
//        tvMovieNumRaters.setText(movieDetailBean.getCollectCount() + "人");
//
        OneUtils.setTextImageSwitch(MAX_LINE, movieDetailBean.getData().getBasic().getStory(), tvMovieSummary, ivMovieSummary);
        ivMovieSummary.setOnClickListener(v -> {
            mMovieSummaryExpand = !mMovieSummaryExpand;
            OneUtils.imageSwitch(getContext(), MAX_LINE, mMovieSummaryExpand, tvMovieSummary, ivMovieSummary);
        });

        DirectorBean directorBean = movieDetailBean.getData().getBasic().getDirector();
        List<ActorBean> list = movieDetailBean.getData().getBasic().getActors();
        list.add(0, new ActorBean(directorBean.getDirectorId(), directorBean.getImg(), directorBean.getName(), directorBean.getNameEn()));

        recyclerViewMovieActor.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerViewMovieActor.setHasFixedSize(false);
        recyclerViewMovieActor.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMovieActor.setNestedScrollingEnabled(false);
        recyclerViewMovieActor.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.sixteen)));
        recyclerViewMovieActor.setAdapter(new RecyclerViewAdapter<>(list, new TypeFactory().setMovieActorData()));
    }

    @Override
    public void moreInfo() {
        if (url != "") WebViewActivity.start(activity, R.color.colorMovie, "", url);
    }
}