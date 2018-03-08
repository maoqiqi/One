package com.android.march.one.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.march.one.LazyLoadFragment;
import com.android.march.one.R;
import com.android.march.one.presenter.HomePresenter;
import com.android.march.one.ui.activity.GankActivity;
import com.android.march.one.ui.view.HomeView;

public class HomeFragment extends LazyLoadFragment {

    private HomeView homeView;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (homeView == null) homeView = new HomeView(getActivity());
        return homeView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void loadData() {
        super.loadData();
        new HomePresenter(homeView);
        homeView.setData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_classify:
                GankActivity.start(getContext(), "");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}