package com.android.march.one.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.march.one.LazyLoadFragment;
import com.android.march.one.presenter.BookListPresenter;
import com.android.march.one.ui.view.BookListView;

public class BookListFragment extends LazyLoadFragment {

    private BookListView bookListView;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment BookListFragment.
     */
    public static BookListFragment newInstance(String keyword, String bookTag) {
        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        bundle.putString("bookTag", bookTag);
        BookListFragment fragment = new BookListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (bookListView == null) bookListView = new BookListView(getActivity());
        return bookListView;
    }

    @Override
    public void loadData() {
        super.loadData();
        new BookListPresenter(bookListView);
        Bundle bundle = getArguments();
        if (bundle != null) {
            bookListView.setData(bundle.getString("keyword"), bundle.getString("bookTag"));
        }
    }
}