package com.scripter.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataSource {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public boolean open() {
        try {
            connection = DriverManager.getConnection(DBConstants.CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't Connect to DB" + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (Objects.nonNull(connection) && Objects.nonNull(preparedStatement)) {
                connection.close();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't Close connection");
        }
    }

    public List<Artist> queryArtist(int sortOrder) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(DBConstants.TABLE_ARTISTS);
        if (sortOrder != DBConstants.ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(DBConstants.COLUMN_ARTISTS_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == DBConstants.ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }
        List<Artist> artistList = null;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString());) {
            System.out.println("QUERY -- > " + sb.toString());
            artistList = new ArrayList<>();
            while (resultSet.next()) {
                artistList.add(new Artist(resultSet.getInt(DBConstants.INDEX_ARTIST_ID), resultSet.getString(DBConstants.INDEX_ARTIST_NAME)));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
        }
        return artistList;
    }

    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
        List<String> albumList = null;
        String query = "SELECT albums.name FROM albums INNER JOIN artists\n" +
                "ON albums.artist = artists._id\n" +
                "WHERE artists.name = '" + artistName + "'\n" +
                "ORDER BY albums.name COLLATE NOCASE ASC";
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(query);) {
            albumList = new ArrayList<>();
            while (results.next()) {
                albumList.add(results.getString(DBConstants.INDEX_ALBUM_ID));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception :" + e.getMessage());
        }
        return albumList;
    }

    public void querySongsMetaData() {
        String sql = "SELECT * FROM songs";
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(sql);) {

            ResultSetMetaData meta = results.getMetaData();
            int numColumns = meta.getColumnCount();

            for (int i = 1; i <= numColumns; i++) {
                System.out.format("Column %d in the songs table is names %s \n", i, meta.getColumnName(i));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
        }
    }

    public int getCount(String table) {
        String sql = "SELECT COUNT(*) AS count,MAX(_id) AS min_id FROM " + table;
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(sql);) {
            return results.getInt(1);
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
            return -1;
        }
    }

    /*Prepared Statements*/
    public List<Song> getSongsBasedOnAlbum(int album) {
        List<Song> songList = null;
        String sql = "SELECT * FROM songs WHERE album = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, album);
            ResultSet resultSet = preparedStatement.executeQuery();
            songList = new ArrayList<>();
            while (resultSet.next()) {
                songList.add(new Song(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }
}
