package com.android.march.one.adapter.holder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.model.bean.NewsListBean;
import com.android.march.one.ui.activity.NewsDetailActivity;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerViewAdapter.ViewHolder<NewsListBean.NewsBean> {

    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.ivNews)
    ImageView ivNews;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public NewsListAdapter(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void bindViewHolder(RecyclerViewAdapter<NewsListBean.NewsBean> adapter, int position, NewsListBean.NewsBean newsBean) {
        Glide.with(ivNews.getContext()).load(newsBean.getImages().get(0)).placeholder(R.drawable.ic_news_placeholder).into(ivNews);
        tvTitle.setText(newsBean.getTitle());
        cardView.setOnClickListener(v -> NewsDetailActivity.start(v.getContext(), newsBean.getId(), newsBean.getTitle()));
    }
}