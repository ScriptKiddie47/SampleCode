package com.scripter.model;

public final class DBConstants {

    static final String DB_NAME = "music.db";
    static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\ritam\\Desktop\\SQLite3_DB\\" + DB_NAME;

    static final String TABLE_ALBUMS = "albums";
    static final String COLUMN_ALBUMS_ID = "_id";
    static final String COLUMN_ALBUMS_NAME = "name";
    static final String COLUMN_ALBUMS_ARTIST = "artist";

    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    static final String TABLE_ARTISTS = "artists";
    static final String COLUMN_ARTISTS_ID = "_id";
    static final String COLUMN_ARTISTS_NAME = "name";

    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;


    static final String TABLE_SONGS = "songs";
    static final String COLUMN_SONGS_ID = "_id";
    static final String COLUMN_SONGS_TRACK = "track";
    static final String COLUMN_SONGS_TITLE = "title";
    static final String COLUMN_SONGS_ALBUM = "album";

    public static final int INDEX_SONGS_ID = 1;
    public static final int INDEX_SONGS_TRACK = 2;
    public static final int INDEX_SONGS_TITLE = 3;
    public static final int INDEX_SONGS_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

//    INSERT INTO artists(name) VALUES(?)
//    INSERT INTO albums(name,artist) VALUES(?,?)
//    INSERT INTO songs(track,title,album) VALUE(?,?,?)

//    SELECT _id FROM artists WHERE name = "Pink Floyd"
}
