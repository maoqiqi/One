package com.android.march.one.ui.view;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import com.android.march.one.OneUtils;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.adapter.TypeFactory;
import com.android.march.one.model.bean.NewsListBean;
import com.android.march.one.presenter.contract.NewsListContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListView extends RootView<NewsListContract.Presenter> implements NewsListContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public NewsListView(Context context) {
        super(context);
        inflate(context, R.layout.layout_news_list, this);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_news_list;
    }

    public void setData() {
        presenter.getLatestNews();
    }

    @Override
    public void setLatestNews(NewsListBean newsListBean) {
        OneUtils.setRecyclerView(getContext(), recyclerView);
        recyclerView.setAdapter(new RecyclerViewAdapter<>(newsListBean.getNewsBeans(), new TypeFactory().setNewsList()));
    }
}