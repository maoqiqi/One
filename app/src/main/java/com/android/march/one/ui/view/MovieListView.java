package com.android.march.one.ui.view;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

import com.android.march.one.OneUtils;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.adapter.TypeFactory;
import com.android.march.one.model.bean.MovieListBean;
import com.android.march.one.presenter.contract.MovieListContract;
import com.android.march.one.ui.DividerDecoration;

import butterknife.BindView;

public class MovieListView extends RootView<MovieListContract.Presenter> implements MovieListContract.View {

    private int type;
    private int pageIndex = 1;
    private int pageCount = 10;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AppCompatActivity activity;

    public MovieListView(Context context) {
        super(context);
    }

    public MovieListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_movie_list;
    }

    public void setData(int type, String city, String keyword, String movieTag) {
        activity = (AppCompatActivity) getContext();
        this.type = type;
        if (type == 0) {
            presenter.getInTheatersMovies(city, pageIndex, pageCount);
        } else if (type == 1) {
            presenter.getComingSoonMovies(pageIndex, pageCount);
        } else if (type == 2) {
            presenter.searchMovie(keyword, movieTag, pageIndex, pageCount);
        }
    }

    @Override
    public void setData(MovieListBean movieListBean) {
        OneUtils.setRecyclerView(getContext(), recyclerView);
        recyclerView.addItemDecoration(new DividerDecoration());
        recyclerView.setAdapter(new RecyclerViewAdapter<>(movieListBean.getMovieBeanList(), new TypeFactory().setMovieListData(activity, type)));
    }
}