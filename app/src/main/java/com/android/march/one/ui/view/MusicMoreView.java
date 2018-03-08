package com.android.march.one.ui.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.adapter.TypeFactory;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.model.bean.MusicAlbumBean;
import com.android.march.one.model.bean.MusicArtistBean;
import com.android.march.one.model.bean.MusicMoreItemBean;
import com.android.march.one.model.bean.MusicSongBean;
import com.android.march.one.utils.MusicUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MusicMoreView extends RootView<BasePresenter> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int type;
    private MusicSongBean songBean;
    private MusicArtistBean artistBean;
    private MusicAlbumBean albumBean;
    private String folderPath;

    private List<MusicMoreItemBean> list = new ArrayList<>();

    public MusicMoreView(Context context) {
        super(context);
    }

    public MusicMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_music_more;
    }

    public void setData(MusicSongBean songBean) {
        this.type = MusicUtils.TYPE_SONG;
        this.songBean = songBean;
        setData();
    }

    public void setData(MusicArtistBean artistBean) {
        this.type = MusicUtils.TYPE_ARTIST;
        this.artistBean = artistBean;
        setData();
    }

    public void setData(MusicAlbumBean albumBean) {
        this.type = MusicUtils.TYPE_ALBUM;
        this.albumBean = albumBean;
        setData();
    }

    public void setData(String folderPath) {
        this.type = MusicUtils.TYPE_FOLDER;
        this.folderPath = folderPath;
        setData();
    }

    private void setData() {
        switch (type) {
            case MusicUtils.TYPE_SONG:
                tvTitle.setText(getString(R.string.song_info, songBean.getTitle()));

                list.add(new MusicMoreItemBean(getString(R.string.play_next), R.drawable.ic_more_play_next));
                list.add(new MusicMoreItemBean(getString(R.string.collect_song_list), R.drawable.ic_more_collect_song_list));
                list.add(new MusicMoreItemBean(getString(R.string.share), R.drawable.ic_more_share));
                list.add(new MusicMoreItemBean(getString(R.string.artist_info, MusicUtils.getArtist(songBean.getArtist())), R.drawable.ic_more_artist));
                list.add(new MusicMoreItemBean(getString(R.string.album_info, MusicUtils.getAlbum(songBean.getAlbum())), R.drawable.ic_more_album));
                list.add(new MusicMoreItemBean(getString(R.string.set_ring), R.drawable.ic_more_set_ring));
                list.add(new MusicMoreItemBean(getString(R.string.song_information), R.drawable.ic_more_song_information));
                list.add(new MusicMoreItemBean(getString(R.string.delete), R.drawable.ic_more_delete));
                break;
            case MusicUtils.TYPE_ARTIST:
                tvTitle.setText(getString(R.string.artist_info, MusicUtils.getArtist(artistBean.getArtist())));

                list.add(new MusicMoreItemBean(getString(R.string.play_next), R.drawable.ic_more_play_next));
                list.add(new MusicMoreItemBean(getString(R.string.collect_song_list), R.drawable.ic_more_collect_song_list));
                list.add(new MusicMoreItemBean(getString(R.string.delete), R.drawable.ic_more_delete));
                break;
            case MusicUtils.TYPE_ALBUM:
                tvTitle.setText(getString(R.string.album_info, MusicUtils.getAlbum(albumBean.getAlbum())));

                list.add(new MusicMoreItemBean(getString(R.string.play_next), R.drawable.ic_more_play_next));
                list.add(new MusicMoreItemBean(getString(R.string.collect_song_list), R.drawable.ic_more_collect_song_list));
                list.add(new MusicMoreItemBean(getString(R.string.delete), R.drawable.ic_more_delete));
                break;
            case MusicUtils.TYPE_FOLDER:
                tvTitle.setText(getString(R.string.folder_info, MusicUtils.getFolderName(folderPath)));

                list.add(new MusicMoreItemBean(getString(R.string.play_next), R.drawable.ic_more_play_next));
                list.add(new MusicMoreItemBean(getString(R.string.collect_song_list), R.drawable.ic_more_collect_song_list));
                list.add(new MusicMoreItemBean(getString(R.string.delete), R.drawable.ic_more_delete));
                break;
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new RecyclerViewAdapter<>(list, new TypeFactory().setMusicMoreData(activity, type)));
    }
}