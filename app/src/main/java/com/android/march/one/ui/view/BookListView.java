package com.android.march.one.ui.view;

import android.content.Context;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

import com.android.march.one.R;
import com.android.march.one.RecyclerViewScrollListener;
import com.android.march.one.RootView;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.adapter.TypeFactory;
import com.android.march.one.model.bean.BookListBean;
import com.android.march.one.presenter.contract.BookListContract;
import com.android.march.one.ui.DividerDecoration;

import butterknife.BindView;

public class BookListView extends RootView<BookListContract.Presenter> implements BookListContract.View {

    private int pageIndex = 1;
    private int pageCount = 10;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public BookListView(Context context) {
        super(context);
    }

    public BookListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_book_list;
    }

    public void setData(String keyword, String bookTag) {
        presenter.getBook(keyword, bookTag, pageIndex, pageCount);
    }

    @Override
    public void setBook(BookListBean bookListBean) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerDecoration());
        recyclerView.setAdapter(new RecyclerViewAdapter<>(bookListBean.getBookBeanList(), new TypeFactory().setBookListData(activity)));
        recyclerView.addOnScrollListener(new RecyclerViewScrollListener(getContext()));
    }
}