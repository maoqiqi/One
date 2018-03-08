package com.android.march.one.model.bean;

import java.io.Serializable;

public class MusicSongBean implements Serializable {

    // MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

    // 单曲编号
    private long _id;

    // 标题
    private String title;

    // 文件的显示名称 (singer + "-" + title)
    private String displayName;

    // 磁盘上的文件的路径
    private String data;

    // 文件的时长
    private long duration;

    // 文件的大小
    private long size;

    // 专辑id
    private long albumId;

    // 专辑名
    private String album;

    // 艺术家id
    private long artistId;

    // 歌曲的演唱者
    private String artist;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    @Override
    public String toString() {
        return "MusicSongBean{" +
                "_id=" + _id +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", displayName='" + displayName + '\'' +
                ", data='" + data + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", albumId=" + albumId +
                ", album='" + album + '\'' +
                ", artistId=" + artistId +
                '}';
    }
}