package com.android.march.one.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.march.one.LazyLoadFragment;
import com.android.march.one.R;
import com.android.march.one.presenter.MusicListPresenter;
import com.android.march.one.ui.view.MusicListView;
import com.android.march.one.utils.MusicUtils;
import com.android.march.one.utils.SortOrder;

public class MusicListFragment extends LazyLoadFragment {

    private MusicListView musicListView;
    private int type;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment MusicListFragment.
     */
    public static MusicListFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        MusicListFragment fragment = new MusicListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (musicListView == null) musicListView = new MusicListView(getActivity());
        return musicListView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void loadData() {
        super.loadData();
        new MusicListPresenter(musicListView);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type");
            musicListView.setData(this, type);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (getParentFragment() != null && !getParentFragment().getUserVisibleHint()) return;
        switch (type) {
            case MusicUtils.TYPE_SONG:
                inflater.inflate(R.menu.menu_songs_sort_by, menu);
                break;
            case MusicUtils.TYPE_ARTIST:
                inflater.inflate(R.menu.menu_artist_sort_by, menu);
                break;
            case MusicUtils.TYPE_ALBUM:
                inflater.inflate(R.menu.menu_album_sort_by, menu);
                break;
            case MusicUtils.TYPE_FOLDER:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 歌曲排序
            case R.id.menu_song_sort_order_az:
                musicListView.sortOrder(SortOrder.SongSortOrder.SONG_A_Z);
                return true;
            case R.id.menu_song_sort_order_za:
                musicListView.sortOrder(SortOrder.SongSortOrder.SONG_Z_A);
                return true;
            case R.id.menu_song_sort_order_artist:
                musicListView.sortOrder(SortOrder.SongSortOrder.SONG_ARTIST);
                return true;
            case R.id.menu_song_sort_order_album:
                musicListView.sortOrder(SortOrder.SongSortOrder.SONG_ALBUM);
                return true;
            case R.id.menu_song_sort_order_duration:
                musicListView.sortOrder(SortOrder.SongSortOrder.SONG_DURATION);
                return true;
            // 艺术家排序
            case R.id.menu_artist_sort_order_az:
                musicListView.sortOrder(SortOrder.ArtistSortOrder.ARTIST_A_Z);
                return true;
            case R.id.menu_artist_sort_order_za:
                musicListView.sortOrder(SortOrder.ArtistSortOrder.ARTIST_Z_A);
                return true;
            case R.id.menu_artist_sort_order_number_of_songs:
                musicListView.sortOrder(SortOrder.ArtistSortOrder.ARTIST_NUMBER_OF_SONGS);
                break;
            case R.id.menu_artist_sort_order_number_of_albums:
                musicListView.sortOrder(SortOrder.ArtistSortOrder.ARTIST_NUMBER_OF_ALBUMS);
                break;
            // 专辑排序
            case R.id.menu_album_sort_order_az:
                musicListView.sortOrder(SortOrder.AlbumSortOrder.ALBUM_A_Z);
                return true;
            case R.id.menu_album_sort_order_za:
                musicListView.sortOrder(SortOrder.AlbumSortOrder.ALBUM_Z_A);
                return true;
            case R.id.menu_album_sort_order_artist:
                musicListView.sortOrder(SortOrder.AlbumSortOrder.ALBUM_ARTIST);
                break;
            case R.id.menu_album_sort_order_number_of_songs:
                musicListView.sortOrder(SortOrder.AlbumSortOrder.ALBUM_NUMBER_OF_SONGS);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        musicListView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        musicListView.onActivityResult(requestCode, resultCode, data);
    }
}