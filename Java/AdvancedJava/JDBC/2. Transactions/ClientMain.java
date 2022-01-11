package com.scripter;

import com.scripter.model.Artist;
import com.scripter.model.DataSource;
import com.scripter.model.Song;

import java.util.List;
import java.util.Objects;

public class ClientMain {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        dataSource.open();
        dataSource.insertSongs("Touch of Grey","GrateFul Dead","In The Dark",1);
        dataSource.close();
    }
}
