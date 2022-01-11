package com.scripter.model;

public class Song {

    private int id;
    private int track;
    private String title;
    private int albumId;

    public Song(int id, int track, String title, int albumId) {
        this.id = id;
        this.track = track;
        this.title = title;
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", track=" + track +
                ", title='" + title + '\'' +
                ", albumId=" + albumId +
                '}';
    }
}