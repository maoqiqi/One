package com.android.march.one.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.march.one.LazyLoadFragment;
import com.android.march.one.presenter.MovieListPresenter;
import com.android.march.one.ui.view.MovieListView;

public class MovieListFragment extends LazyLoadFragment {

    private MovieListView movieListView;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment MovieListFragment.
     */
    public static MovieListFragment newInstance(int type, String city, String keyword, String movieTag) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("city", city);
        bundle.putString("keyword", keyword);
        bundle.putString("movieTag", movieTag);
        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (movieListView == null) movieListView = new MovieListView(getActivity());
        return movieListView;
    }

    @Override
    public void loadData() {
        super.loadData();
        new MovieListPresenter(movieListView);
        Bundle bundle = getArguments();
        if (bundle != null) {
            int type = bundle.getInt("type");
            String city = bundle.getString("city");
            String keyword = bundle.getString("keyword");
            String movieTag = bundle.getString("movieTag");
            movieListView.setData(type, city, keyword, movieTag);
        }
    }
}