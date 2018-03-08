package com.android.march.one.model.bean;

import java.io.Serializable;

public class MusicArtistBean implements Serializable {

    // MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI

    // 歌手id
    private long _id;

    // 歌手名
    private String artist;

    // 歌手专辑数
    private long numberOfAlbums;

    // 歌手歌曲数
    private long numberOfTracks;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getNumberOfAlbums() {
        return numberOfAlbums;
    }

    public void setNumberOfAlbums(long numberOfAlbums) {
        this.numberOfAlbums = numberOfAlbums;
    }

    public long getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(long numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }
}