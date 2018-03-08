package com.android.march.one.utils;

import java.io.File;

/**
 * Created by March on 2018/1/23.
 */
public class MusicUtils {

    public static final int TYPE_SONG = 0;
    public static final int TYPE_ARTIST = 1;
    public static final int TYPE_ALBUM = 2;
    public static final int TYPE_FOLDER = 3;

    private MusicUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String getArtist(String artist) {
        if ("<unknown>".equals(artist)) return "未知艺术家";
        return artist;
    }

    public static String getAlbum(String album) {
        if ("<unknown>".equals(album)) return "未知专辑";
        return album;
    }

    public static String getFolderName(String folderPath) {
        return folderPath.substring(folderPath.lastIndexOf(File.separator) + 1);
    }

    public static String getPath(String folderPath) {
        return folderPath.substring(0, folderPath.lastIndexOf(File.separator) + 1);
    }
}