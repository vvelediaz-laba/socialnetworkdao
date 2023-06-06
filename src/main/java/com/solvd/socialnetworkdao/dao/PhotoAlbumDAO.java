package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.PhotoAlbum;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IDAO;
import com.solvd.socialnetworkdao.interfaces.IPhotoAlbumDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PhotoAlbumDAO implements IPhotoAlbumDAO {
    @Override
    public void create(PhotoAlbum photoAlbum) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO PhotoAlbum (id, album_profile_id, photo_album_name, date_created) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, photoAlbum.getId());
            preparedStatement.setLong(2, photoAlbum.getAlbumProfile().getId());
            preparedStatement.setString(3, photoAlbum.getPhotoAlbumName());
            preparedStatement.setDate(4, new Date(photoAlbum.getDateCreated().getTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public PhotoAlbum getById(long id) {
        PhotoAlbum photoAlbum = new PhotoAlbum();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PhotoAlbum WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                photoAlbum.setId(resultSet.getLong("id"));
                photoAlbum.setPhotoAlbumName(resultSet.getString("photo_album_name"));
                photoAlbum.setDateCreated(resultSet.getDate("date_created"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return photoAlbum;
    }

    @Override
    public List<PhotoAlbum> getAll() {
        List<PhotoAlbum> photoAlbums = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PhotoAlbum");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PhotoAlbum photoAlbum = new PhotoAlbum();
                photoAlbum.setId(resultSet.getLong("id"));
                photoAlbum.setPhotoAlbumName(resultSet.getString("photo_album_name"));
                photoAlbum.setDateCreated(resultSet.getDate("date_created"));

                photoAlbums.add(photoAlbum);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return photoAlbums;
    }

    @Override
    public void update(PhotoAlbum photoAlbum) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE PhotoAlbum SET album_profile_id=?, photo_album_name=?, date_created=? WHERE id=?");
            preparedStatement.setLong(1, photoAlbum.getAlbumProfile().getId());
            preparedStatement.setString(2, photoAlbum.getPhotoAlbumName());
            preparedStatement.setDate(3, new Date(photoAlbum.getDateCreated().getTime()));
            preparedStatement.setLong(4, photoAlbum.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(long id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PhotoAlbum WHERE id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<PhotoAlbum> getPhotoAlbumsByProfileId(long id) {
        List<PhotoAlbum> photoAlbums = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PhotoAlbum WHERE album_profile_id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PhotoAlbum photoAlbum = new PhotoAlbum();
                photoAlbum.setId(resultSet.getLong("id"));
                photoAlbum.setPhotoAlbumName(resultSet.getString("photo_album_name"));
                photoAlbum.setDateCreated(resultSet.getDate("date_created"));

                photoAlbums.add(photoAlbum);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return photoAlbums;
    }
}
