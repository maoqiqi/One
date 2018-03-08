package com.android.march.one.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.model.bean.MovieActorBean;
import com.android.march.one.ui.activity.WebViewActivity;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActorViewHolder extends RecyclerViewAdapter.ViewHolder<MovieActorBean> {

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.ivMovieActor)
    ImageView ivMovieActor;
    @BindView(R.id.tvMovieActorName)
    TextView tvMovieActorName;
    @BindView(R.id.tvMovieActorTypeName)
    TextView tvMovieActorTypeName;

    public MovieActorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void bindViewHolder(RecyclerViewAdapter<MovieActorBean> adapter, int position, MovieActorBean movieActorBean) {
        Glide.with(ivMovieActor.getContext()).load(movieActorBean.getAvatarBean().getLarge()).placeholder(R.drawable.ic_picture_placeholder).into(ivMovieActor);
        tvMovieActorName.setText(movieActorBean.getName());
        tvMovieActorTypeName.setText(movieActorBean.getTypeName());

        llItem.setOnClickListener(v -> WebViewActivity.start(v.getContext(), R.color.colorMovie, "", movieActorBean.getAlt()));
    }
}