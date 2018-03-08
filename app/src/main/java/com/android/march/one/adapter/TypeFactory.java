package com.android.march.one.adapter;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.march.one.R;
import com.android.march.one.adapter.holder.BookListViewHolder;
import com.android.march.one.adapter.holder.GankListViewHolder;
import com.android.march.one.adapter.holder.HomeViewHolder;
import com.android.march.one.adapter.holder.MovieActorViewHolder;
import com.android.march.one.adapter.holder.MovieListViewHolder;
import com.android.march.one.adapter.holder.MusicListViewHolder;
import com.android.march.one.adapter.holder.MusicMoreAdapter;
import com.android.march.one.adapter.holder.NewsListAdapter;
import com.android.march.one.adapter.holder.WelfareViewHolder;

import java.util.List;

public class TypeFactory implements ITypeFactory {

    private int viewType;

    private AppCompatActivity activity;
    private String category;
    private int type;
    private List<Integer> integers;

    public TypeFactory setHomeData(AppCompatActivity activity) {
        this.activity = activity;
        this.viewType = R.layout.item_home;
        return this;
    }

    public TypeFactory setHomeItem() {
        this.viewType = R.layout.item_home_item;
        return this;
    }

    public TypeFactory setGankListData(String category) {
        this.category = category;
        this.viewType = R.layout.item_gank_list;
        return this;
    }

    public TypeFactory setWelfareData(AppCompatActivity activity) {
        this.activity = activity;
        this.viewType = R.layout.item_gank_list_welfare;
        return this;
    }

    public TypeFactory setNewsList() {
        this.viewType = R.layout.item_news_list;
        return this;
    }

    public TypeFactory setBookListData(AppCompatActivity activity) {
        this.activity = activity;
        this.viewType = R.layout.item_book_list;
        return this;
    }

    public TypeFactory setMusicListData(AppCompatActivity activity, int type, List<Integer> integers) {
        this.activity = activity;
        this.type = type;
        this.integers = integers;
        this.viewType = R.layout.item_music_list;
        return this;
    }

    public TypeFactory setMusicMoreData(AppCompatActivity activity, int type) {
        this.activity = activity;
        this.type = type;
        this.viewType = R.layout.item_music_more;
        return this;
    }

    public TypeFactory setMovieListData(AppCompatActivity activity, int type) {
        this.activity = activity;
        this.type = type;
        this.viewType = R.layout.item_movie_list;
        return this;
    }

    public TypeFactory setMovieActorData() {
        this.viewType = R.layout.item_movie_actor_list;
        return this;
    }

    @Override
    public <T> int getViewType(List<T> list, int position) {
        // String name = list.get(position).getClass().getName();
        return viewType;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        switch (viewType) {
            case R.layout.item_home:
                return new HomeViewHolder(activity, itemView);
            case R.layout.item_home_item:
                return new HomeViewHolder.HomeItemViewHolder(itemView);
            case R.layout.item_gank_list:
                return new GankListViewHolder(category, itemView);
            case R.layout.item_gank_list_welfare:
                return new WelfareViewHolder(activity, itemView);
            case R.layout.item_news_list:
                return new NewsListAdapter(itemView);
            case R.layout.item_book_list:
                return new BookListViewHolder(activity, itemView);
            case R.layout.item_music_list:
                return new MusicListViewHolder<>(activity, type, integers, itemView);
            case R.layout.item_music_more:
                return new MusicMoreAdapter(itemView);
            case R.layout.item_movie_list:
                return new MovieListViewHolder(activity, type, itemView);
            case R.layout.item_movie_actor_list:
                return new MovieActorViewHolder(itemView);
        }
        throw new RuntimeException("createViewHolder(ViewGroup parent, int viewType) doesn't match result.");
    }
}