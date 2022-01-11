package com.scripter;

import com.scripter.model.Artist;
import com.scripter.model.DataSource;
import com.scripter.model.Song;

import java.util.List;
import java.util.Objects;

public class ClientMain {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open DataSource");
        }
        List<Artist> artists = dataSource.queryArtist(2);
        if (!Objects.nonNull(artists)) {
            System.out.println("No Artists");
        }
        artists.forEach(System.out::println);
        System.out.println("===============================");
        List<String> albumList = dataSource.queryAlbumsForArtist("Pink Floyd", 1);
        if (Objects.nonNull(albumList) && !albumList.isEmpty()) {
            albumList.forEach(System.out::println);
        } else {
            System.out.println("No Albums");
        }
        System.out.println("===============================");

        dataSource.querySongsMetaData();

        System.out.println("===============================");
        System.out.println("NUMBER OF SONGS : " + dataSource.getCount("songs"));
        System.out.println("===============================");

        List<Song> songList = dataSource.getSongsBasedOnAlbum(311);
        songList.forEach(System.out::println);
        dataSource.close();
    }
}
