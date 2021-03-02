package com.android.march.one.ui.view;

import android.content.Context;
import com.google.android.material.appbar.AppBarLayout;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.march.one.AppBarStateChangeListener;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.model.bean.NewsDetailBean;
import com.android.march.one.presenter.contract.NewsDetailContract;
import com.bumptech.glide.Glide;

import butterknife.BindView;

public class NewsDetailView extends RootView<NewsDetailContract.Presenter> implements NewsDetailContract.View {

    private final String MIME_TYPE = "text/html; charset=utf-8";
    private final String ENCODING = "utf-8";

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.ivHeader)
    ImageView ivHeader;
    @BindView(R.id.tvNewsTitle)
    TextView tvNewsTitle;
    @BindView(R.id.tvSource)
    TextView tvSource;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.webView)
    WebView webView;

    public NewsDetailView(Context context) {
        super(context);
    }

    public NewsDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_news_detail;
    }

    public void setData(int id, String title) {
        setToolBar(toolbar);
        tvTitle.setText(title);
        tvNewsTitle.setText(title);

        // 使用CollapsingToolbarLayout的时候要注意,在完成CollapsingToolbarLayout设置之后再调用Toolbar的setTitle()等方法将没有效果,
        // 我们需要改为调用CollapsingToolbarLayout的setTitle()等方法来对工具栏进行修改。
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) { // 折叠状态
                    tvTitle.setVisibility(View.VISIBLE);
                } else if (state == State.EXPANDED) { // 展开状态
                    tvTitle.setVisibility(View.INVISIBLE);
                } else {// 中间状态
                    tvTitle.setVisibility(View.INVISIBLE);
                }
            }
        });

        presenter.getNewsDetail(id);
    }

    @Override
    public void setNewsDetail(NewsDetailBean newsDetailBean) {
        Glide.with(getContext()).load(newsDetailBean.getImage()).into(ivHeader);
        tvSource.setText(newsDetailBean.getImageSource());

        StringBuilder buffer = new StringBuilder();
        buffer.append("<html lang=\"zh-CN\">");
        buffer.append("<head>");
        buffer.append("<meta name=\"viewport\" content=\"user-scalable=no, width=device-width\">");
        for (int i = 0; i < newsDetailBean.getCss().size(); i++) {
            buffer.append("<link rel=\"stylesheet\" href=\"" + newsDetailBean.getCss().get(i) + "\">");
        }
        buffer.append("<style>");
        buffer.append(".headline { border-bottom: 0px solid #f6f6f6;}");
        buffer.append(".headline .img-place-holder { height: 0px;}");
        buffer.append("</style>");
        buffer.append("</head>");
        buffer.append("<body>");
        buffer.append(newsDetailBean.getBody());
        buffer.append("</body>");
        buffer.append("</html>");

        webView.loadData(buffer.toString(), MIME_TYPE, ENCODING);
    }
}