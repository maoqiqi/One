package com.android.march.one.adapter.holder;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.model.bean.BookListBean;
import com.android.march.one.ui.activity.BookDetailActivity;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListViewHolder extends RecyclerViewAdapter.ViewHolder<BookListBean.BookBean> {

    private final AppCompatActivity activity;

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.ivBook)
    ImageView ivBook;
    @BindView(R.id.tvBookTitle)
    TextView tvBookTitle;
    @BindView(R.id.tvBookAuthor)
    TextView tvBookAuthor;
    @BindView(R.id.tvBookPublish)
    TextView tvBookPublish;
    @BindView(R.id.tvBookPrice)
    TextView tvBookPrice;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tvBookRating)
    TextView tvBookRating;

    public BookListViewHolder(AppCompatActivity activity, View itemView) {
        super(itemView);
        this.activity = activity;
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void bindViewHolder(RecyclerViewAdapter<BookListBean.BookBean> adapter, int position, BookListBean.BookBean bookBean) {
        Glide.with(ivBook.getContext()).load(bookBean.getImageBean().getLarge()).placeholder(R.drawable.ic_book_placeholder).into(ivBook);

        tvBookTitle.setText(bookBean.getTitle());
        if (bookBean.getAuthorList() != null && bookBean.getAuthorList().size() > 0) {
            tvBookAuthor.setVisibility(View.VISIBLE);
            tvBookAuthor.setText(bookBean.getAuthorList().get(0));
        } else {
            tvBookAuthor.setVisibility(View.GONE);
        }
        tvBookPublish.setText(bookBean.getPublisher() + " / " + bookBean.getPubDate());
        if (bookBean.getPrice().equals("")) {
            tvBookPrice.setVisibility(View.GONE);
        } else {
            tvBookPrice.setVisibility(View.VISIBLE);
            tvBookPrice.setText(bookBean.getPrice());
        }
        ratingBar.setRating(Float.parseFloat(bookBean.getRatingBean().getAverage()) / 2);
        tvBookRating.setText(bookBean.getRatingBean().getAverage());

        llItem.setOnClickListener(v -> BookDetailActivity.start(activity, bookBean.getId(), bookBean.getTitle(), bookBean.getImageBean().getLarge(), ivBook));
    }
}