package com.android.march.one.adapter.holder;

import android.content.res.Resources;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.march.one.Constants;
import com.android.march.one.MediaLoader;
import com.android.march.one.R;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.model.bean.MusicAlbumBean;
import com.android.march.one.model.bean.MusicArtistBean;
import com.android.march.one.model.bean.MusicSongBean;
import com.android.march.one.ui.activity.MusicAlbumActivity;
import com.android.march.one.ui.activity.MusicArtistActivity;
import com.android.march.one.ui.activity.MusicFolderActivity;
import com.android.march.one.ui.activity.MusicMoreActivity;
import com.android.march.one.utils.MusicUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MusicListViewHolder<T> extends RecyclerViewAdapter.ViewHolder<T> {

    private final AppCompatActivity activity;
    private final int type;
    private final List<Integer> integers;
    private final Resources resources;

    private int lastPosition = -1;
    private Animation animation;

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.ivMusic)
    ImageView ivMusic;
    @BindView(R.id.ivFolder)
    ImageView ivFolder;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.ivMore)
    ImageView ivMore;

    public MusicListViewHolder(AppCompatActivity activity, int type, List<Integer> integers, View itemView) {
        super(itemView);
        this.activity = activity;
        this.type = type;
        this.integers = integers;
        this.resources = activity.getResources();
        this.animation = AnimationUtils.loadAnimation(activity, R.anim.slide_in_bottom);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void bindViewHolder(RecyclerViewAdapter<T> adapter, int position, T t) {
        switch (type) {
            case MusicUtils.TYPE_SONG:// 歌曲
                ivMusic.setVisibility(View.VISIBLE);
                ivFolder.setVisibility(View.GONE);

                MusicSongBean songBean = (MusicSongBean) t;

                tvName.setText(songBean.getTitle());
                tvInfo.setText(resources.getString(R.string.music_song_info, MusicUtils.getArtist(songBean.getArtist()), MusicUtils.getAlbum(songBean.getAlbum())));

                // 可以使用 DialogFragment 展示( 以下使用 将 Activity 设置为 Dialog 模式 )
                // FragmentManager manager = activity.getSupportFragmentManager();
                // MusicMoreFragment.newInstance(songBean).show(manager, "song");

                Glide.with(activity).load(MediaLoader.getAlbumArtUri(songBean.getAlbumId())).placeholder(R.drawable.ic_music).into(ivMusic);

                ivMore.setOnClickListener(v -> MusicMoreActivity.start(activity, songBean));
                break;
            case MusicUtils.TYPE_ARTIST:// 艺术家
                ivMusic.setVisibility(View.VISIBLE);
                ivFolder.setVisibility(View.GONE);

                MusicArtistBean artistBean = (MusicArtistBean) t;

                tvName.setText(MusicUtils.getArtist(artistBean.getArtist()));
                tvInfo.setText(resources.getString(R.string.music_artist_info, artistBean.getNumberOfAlbums(), artistBean.getNumberOfTracks()));

                // 从图片数组依次取
                String imageUrl = Constants.THREE_URLS[position % Constants.THREE_URLS.length];
                Glide.with(activity).load(imageUrl).placeholder(R.drawable.ic_music).into(ivMusic);

                ivMore.setOnClickListener(v -> MusicMoreActivity.start(activity, artistBean));
                llItem.setOnClickListener(v -> {
                    Pair<View, String> ivMusicPair = new Pair<>(ivMusic, ViewCompat.getTransitionName(ivMusic));
                    MusicArtistActivity.start(activity, artistBean, imageUrl, ivMusicPair);
                });
                break;
            case MusicUtils.TYPE_ALBUM:// 专辑
                ivMusic.setVisibility(View.VISIBLE);
                ivFolder.setVisibility(View.GONE);

                MusicAlbumBean albumBean = (MusicAlbumBean) t;

                tvName.setText(MusicUtils.getAlbum(albumBean.getAlbum()));
                tvInfo.setText(resources.getString(R.string.music_album_info, MusicUtils.getArtist(albumBean.getArtist()), albumBean.getNumberOfSongs()));

                Glide.with(activity).load(albumBean.getAlbumArt()).placeholder(R.drawable.ic_music).into(ivMusic);

                ivMore.setOnClickListener(v -> MusicMoreActivity.start(activity, albumBean));
                llItem.setOnClickListener(v -> {
                    Pair<View, String> ivMusicPair = new Pair<>(ivMusic, ViewCompat.getTransitionName(ivMusic));
                    MusicAlbumActivity.start(activity, albumBean, albumBean.getAlbumArt(), ivMusicPair);
                });
                break;
            case MusicUtils.TYPE_FOLDER:// 文件夹
                ivMusic.setVisibility(View.GONE);
                ivFolder.setVisibility(View.VISIBLE);

                String folderPath = (String) t;

                tvName.setText(MusicUtils.getFolderName(folderPath));
                tvInfo.setText(resources.getString(R.string.music_folder_info, integers.get(position), MusicUtils.getPath(folderPath)));

                ivMore.setOnClickListener(v -> MusicMoreActivity.start(activity, folderPath));
                llItem.setOnClickListener(v -> MusicFolderActivity.start(activity, folderPath));
                break;
        }

//        if (position > lastPosition) {
//            llItem.startAnimation(animation);
//            lastPosition = position;
//        }
    }
}