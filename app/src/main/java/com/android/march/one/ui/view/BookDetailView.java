package com.android.march.one.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.march.one.AppBarStateChangeListener;
import com.android.march.one.OneUtils;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.model.bean.BookListBean;
import com.android.march.one.presenter.contract.BookDetailContract;
import com.android.march.one.ui.activity.WebViewActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;

public class BookDetailView extends RootView<BookDetailContract.Presenter> implements BookDetailContract.View, View.OnClickListener {

    private static final int MAX_LINE = 3; // TextView 默认最大展示行数

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.ivBook)
    ImageView ivBook;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvBookTitle)
    TextView tvBookTitle;
    @BindView(R.id.tvBookAuthor)
    TextView tvBookAuthor;
    @BindView(R.id.tvBookPublisher)
    TextView tvBookPublisher;
    @BindView(R.id.tvBookPublishDate)
    TextView tvBookPublishDate;
    @BindView(R.id.tvBookPrice)
    TextView tvBookPrice;
    @BindView(R.id.tvBookRating)
    TextView tvBookRating;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tvBookNumRaters)
    TextView tvBookNumRaters;
    @BindView(R.id.ivBookSummary)
    ImageView ivBookSummary;
    @BindView(R.id.tvBookSummary)
    TextView tvBookSummary;
    @BindView(R.id.ivBookAuthorIntro)
    ImageView ivBookAuthorIntro;
    @BindView(R.id.tvBookAuthorIntro)
    TextView tvBookAuthorIntro;
    @BindView(R.id.ivBookCatalog)
    ImageView ivBookCatalog;
    @BindView(R.id.tvBookCatalog)
    TextView tvBookCatalog;

    private boolean mBookSummaryExpand = false;
    private boolean mBookAuthorIntroExpand = false;
    private boolean mBookCatalogExpand = false;

    private String url = "";

    public BookDetailView(Context context) {
        super(context);
    }

    public BookDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_book_detail;
    }

    public void setData(String id, String title, String imageUrl) {
        setToolBar(toolbar);

        Glide.with(getContext()).load(imageUrl).asBitmap().placeholder(R.drawable.ic_book_placeholder).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ivBook.setImageBitmap(resource);
                OneUtils.setBackground(appBarLayout, resource);
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) { // 折叠状态
                    tvTitle.setText(title);
                    tvTitle.setVisibility(View.VISIBLE);
                } else if (state == State.EXPANDED) { // 展开状态
                    tvTitle.setText("图书详情");
                    tvTitle.setVisibility(View.VISIBLE);
                } else {// 中间状态
                    tvTitle.setVisibility(View.INVISIBLE);
                }
            }
        });

        presenter.getBookDetail(id);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBookSummary:
                mBookSummaryExpand = !mBookSummaryExpand;
                OneUtils.imageSwitch(getContext(), MAX_LINE, mBookSummaryExpand, tvBookSummary, ivBookSummary);
                break;
            case R.id.ivBookAuthorIntro:
                mBookAuthorIntroExpand = !mBookAuthorIntroExpand;
                OneUtils.imageSwitch(getContext(), MAX_LINE, mBookAuthorIntroExpand, tvBookAuthorIntro, ivBookAuthorIntro);
                break;
            case R.id.ivBookCatalog:
                mBookCatalogExpand = !mBookCatalogExpand;
                OneUtils.imageSwitch(getContext(), MAX_LINE, mBookCatalogExpand, tvBookCatalog, ivBookCatalog);
                break;
        }
    }

    @Override
    public void setBookDetail(BookListBean.BookBean bookBean) {
        url = bookBean.getAlt();

        tvBookTitle.setText(bookBean.getTitle());
        if (bookBean.getAuthorList() != null && bookBean.getAuthorList().size() > 0) {
            tvBookAuthor.setVisibility(View.VISIBLE);
            tvBookAuthor.setText("作者：" + bookBean.getAuthorList().get(0));
        } else {
            tvBookAuthor.setVisibility(View.GONE);
        }
        tvBookPublisher.setText("出版社：" + bookBean.getPublisher());
        tvBookPublishDate.setText("出版时间：" + bookBean.getPubDate());
        if (bookBean.getPrice().equals("")) {
            tvBookPrice.setVisibility(View.GONE);
        } else {
            tvBookPrice.setVisibility(View.VISIBLE);
            tvBookPrice.setText("价格：" + bookBean.getPrice());
        }
        ratingBar.setRating(Float.parseFloat(bookBean.getRatingBean().getAverage()) / 2);
        tvBookRating.setText(bookBean.getRatingBean().getAverage());
        tvBookNumRaters.setText(bookBean.getRatingBean().getNumRaters() + "人");

        OneUtils.setTextImageSwitch(MAX_LINE, bookBean.getSummary(), tvBookSummary, ivBookSummary);
        OneUtils.setTextImageSwitch(MAX_LINE, bookBean.getAuthorintro(), tvBookAuthorIntro, ivBookAuthorIntro);
        OneUtils.setTextImageSwitch(MAX_LINE, bookBean.getCatalog(), tvBookCatalog, ivBookCatalog);

        ivBookSummary.setOnClickListener(this);
        ivBookAuthorIntro.setOnClickListener(this);
        ivBookCatalog.setOnClickListener(this);
    }

    @Override
    public void moreInfo() {
        if (url != "") WebViewActivity.start(getContext(), R.color.colorBook, "", url);
    }
}