package com.scripter.model;

import java.sql.*;
import java.util.Objects;

public class DataSource {
    private Connection connection;
    private PreparedStatement insertIntoArtist;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;
    private PreparedStatement queryArtistPrepStatement;
    private PreparedStatement queryAlbumPrepStatement;

    public boolean open() {
        try {
            connection = DriverManager.getConnection(DBConstants.CONNECTION_STRING);
            insertIntoArtist = connection.prepareStatement("INSERT INTO artists(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = connection.prepareStatement("INSERT INTO albums(name,artist) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = connection.prepareStatement("INSERT INTO songs(track,title,album) VALUES(?,?,?)");
            queryArtistPrepStatement = connection.prepareStatement("SELECT _id FROM artists WHERE name = ?");
            queryAlbumPrepStatement = connection.prepareStatement("SELECT _id FROM albums WHERE name = ?");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't Connect to DB ==> " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (Objects.nonNull(connection)) connection.close();
            if (Objects.nonNull(insertIntoArtist)) insertIntoArtist.close();
            if (Objects.nonNull(insertIntoAlbums)) insertIntoAlbums.close();
            if (Objects.nonNull(insertIntoSongs)) insertIntoSongs.close();
            if (Objects.nonNull(queryArtistPrepStatement)) queryArtistPrepStatement.close();
            if (Objects.nonNull(queryAlbumPrepStatement)) queryAlbumPrepStatement.close();

        } catch (SQLException e) {
            System.out.println("Couldn't Close connection ==> "+e.getMessage());
        }
    }

    private int insertArtist(String name) throws SQLException {
        //First we Check to see if the Artist is Already on the Table.
        queryArtistPrepStatement.setString(1, name);
        ResultSet resultSet = queryArtistPrepStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1); //So we got existing artist with the same artist name, we return the ID
        } else {
            insertIntoArtist.setString(1, name);
            int affectedRows = insertIntoArtist.executeUpdate(); //executeUpdate() -> Return the number of Rows effected by the SQL Query.
            if (affectedRows != 1) throw new SQLException("Couldn't insert Artist"); //Since we are executing a single row, we expect only 1 row to be returned.

            //This is how we get the generated Keys used during prepareStatement : Statement.RETURN_GENERATED_KEYS
            ResultSet generatedKeys = insertIntoArtist.getGeneratedKeys(); // getGeneratedKeys() -> Users can call the method Statement.getGeneratedKeys to retrieve the value of such a column
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get '_id' for artist");
            }
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {
        queryAlbumPrepStatement.setString(1, name);
        ResultSet resultSet = queryAlbumPrepStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);
            int affectedRows = insertIntoAlbums.executeUpdate();
            if (affectedRows != 1) throw new SQLException("Couldn't insert Album");
            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get '_id' for Album");
            }
        }
    }

    public void insertSongs(String title, String artist, String album, int track) {
        try {
            connection.setAutoCommit(false);

            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);

            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);

            int affectedRows = insertIntoSongs.executeUpdate();
            if (affectedRows == 1) {
                connection.commit(); //Transaction Succeed
            } else {
                throw new SQLException("The Song Insert Failed");
            }
        } catch (SQLException e) {
            System.out.println("Insert Song Exception : " + e.getMessage());
            try {
                System.out.println("Performing RollBacks");
                connection.rollback();
            } catch (SQLException e2) {
                System.out.println("Connection RollBack Exception : " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting Default Commit Behaviour");
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't Reset" + e.getMessage());
            }
        }
    }
}
