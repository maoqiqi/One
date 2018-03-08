package com.android.march.one.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.march.one.LazyLoadFragment;
import com.android.march.one.presenter.NewsListPresenter;
import com.android.march.one.ui.view.NewsListView;

public class NewsListFragment extends LazyLoadFragment {

    private NewsListView newsListView;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewsListFragment.
     */
    public static NewsListFragment newInstance(String category) {
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (newsListView == null) newsListView = new NewsListView(getActivity());
        return newsListView;
    }

    @Override
    public void loadData() {
        super.loadData();
        new NewsListPresenter(newsListView);
        newsListView.setData();
    }
}