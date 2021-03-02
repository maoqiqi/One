package com.android.march.one.adapter.holder;

import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.model.bean.MovieBean;
import com.android.march.one.ui.activity.MovieDetailActivity;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListViewHolder extends RecyclerViewAdapter.ViewHolder<MovieBean> {

    private final AppCompatActivity activity;
    private final int type;
    private final Resources resources;

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.ivMovie)
    ImageView ivMovie;
    @BindView(R.id.tvMovieTitle)
    TextView tvMovieTitle;
    @BindView(R.id.tvMovieDirector)
    TextView tvMovieDirector;
    @BindView(R.id.tvMovieCast)
    TextView tvMovieCast;
    @BindView(R.id.tvMovieGenre)
    TextView tvMovieGenre;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tvMovieRating)
    TextView tvMovieRating;
    @BindView(R.id.llRating)
    LinearLayout llRating;

    public MovieListViewHolder(AppCompatActivity activity, int type, View itemView) {
        super(itemView);
        this.activity = activity;
        this.type = type;
        this.resources = this.activity.getResources();
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void bindViewHolder(RecyclerViewAdapter<MovieBean> adapter, int position, MovieBean movieBean) {
        Glide.with(activity).load(movieBean.getImg()).placeholder(R.drawable.ic_movie_placeholder).into(ivMovie);

        tvMovieTitle.setText(movieBean.getTitle());
        tvMovieDirector.setText(resources.getString(R.string.movie_director, movieBean.getDN()));
        tvMovieCast.setText(resources.getString(R.string.movie_star, movieBean.getActors()));
        tvMovieGenre.setText(resources.getString(R.string.movie_classify, movieBean.getMovieType()));

        if (type == 0) {
            llRating.setVisibility(View.VISIBLE);
            ratingBar.setRating(movieBean.getR() / 2);
            tvMovieRating.setText(String.valueOf(movieBean.getR()));
        } else {
            llRating.setVisibility(View.GONE);
        }

        llItem.setOnClickListener(v -> MovieDetailActivity.start(activity, movieBean.getId() + "", movieBean.getTCn(), movieBean.getImg(), ivMovie));
    }
}