package com.android.march.one.ui.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.adapter.TypeFactory;
import com.android.march.one.model.bean.MusicAlbumBean;
import com.android.march.one.model.bean.MusicArtistBean;
import com.android.march.one.model.bean.MusicMoreItemBean;
import com.android.march.one.model.bean.MusicSongBean;
import com.android.march.one.utils.MusicUtils;
import com.codearms.maoqiqi.android.ScreenUtilsKt;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicMoreFragment extends DialogFragment {

    private View view;

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

    public static MusicMoreFragment newInstance(MusicSongBean songBean) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", MusicUtils.TYPE_SONG);
        bundle.putSerializable("songBean", songBean);
        MusicMoreFragment fragment = new MusicMoreFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MusicMoreFragment newInstance(MusicArtistBean artistBean) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", MusicUtils.TYPE_ARTIST);
        bundle.putSerializable("artistBean", artistBean);
        MusicMoreFragment fragment = new MusicMoreFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MusicMoreFragment newInstance(MusicAlbumBean albumBean) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", MusicUtils.TYPE_ALBUM);
        bundle.putSerializable("albumBean", albumBean);
        MusicMoreFragment fragment = new MusicMoreFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MusicMoreFragment newInstance(String folderPath) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", MusicUtils.TYPE_FOLDER);
        bundle.putSerializable("folderPath", folderPath);
        MusicMoreFragment fragment = new MusicMoreFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.layout_music_more, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.post(() -> {
            Window window = getDialog().getWindow();
            window.setWindowAnimations(R.style.DialogSheetAnimation);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            int halfScreenHeight = ScreenUtilsKt.getScreenHeight(getActivity()) / 2;
            if (view.getHeight() > halfScreenHeight)
                params.height = halfScreenHeight;
            else
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM;
            window.setAttributes(params);
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type");
            switch (type) {
                case MusicUtils.TYPE_SONG:
                    songBean = (MusicSongBean) bundle.getSerializable("songBean");
                    break;
                case MusicUtils.TYPE_ARTIST:
                    artistBean = (MusicArtistBean) bundle.getSerializable("artistBean");
                    break;
                case MusicUtils.TYPE_ALBUM:
                    albumBean = (MusicAlbumBean) bundle.getSerializable("albumBean");
                    break;
                case MusicUtils.TYPE_FOLDER:
                    folderPath = bundle.getString("folderPath");
                    break;
            }
            setData();
        }
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

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new RecyclerViewAdapter<>(list, new TypeFactory().setMusicMoreData((AppCompatActivity) getContext(), type)));
    }
}