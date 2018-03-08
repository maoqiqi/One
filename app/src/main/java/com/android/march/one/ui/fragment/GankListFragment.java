package com.android.march.one.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.march.one.LazyLoadFragment;
import com.android.march.one.presenter.GankListPresenter;
import com.android.march.one.ui.view.GankListView;

public class GankListFragment extends LazyLoadFragment {

    private GankListView gankListView;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment GankListFragment.
     */
    public static GankListFragment newInstance(String category) {
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        GankListFragment fragment = new GankListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (gankListView == null) gankListView = new GankListView(getActivity());
        return gankListView;
    }

    @Override
    public void loadData() {
        super.loadData();
        new GankListPresenter(gankListView);
        Bundle bundle = getArguments();
        if (bundle != null) {
            gankListView.setData(bundle.getString("category"));
        }
    }
}