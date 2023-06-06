package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Photo;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PhotoDAO implements IDAO<Photo> {

    @Override
    public void create(Photo photo) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Photo (id, photo_album_id, content, caption, upload_date) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, photo.getId());
            preparedStatement.setLong(2, photo.getPhotoAlbum().getId());
            preparedStatement.setBlob(3, photo.getContent());
            preparedStatement.setString(4, photo.getCaption());
            preparedStatement.setDate(5, new Date(photo.getUploadDate().getTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Photo getById(long id) {
        Photo photo = new Photo();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Photo WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                photo.setId(resultSet.getLong("id"));
                photo.setPhotoAlbum(new PhotoAlbumDAO().getById(resultSet.getLong("photo_album_id")));
                photo.setContent(resultSet.getBlob("content"));
                photo.setCaption(resultSet.getString("caption"));
                photo.setUploadDate(resultSet.getDate("upload_date"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return photo;
    }

    @Override
    public List<Photo> getAll() {
        List<Photo> photos = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Photo");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Photo photo = new Photo();
                photo.setId(resultSet.getLong("id"));
                photo.setPhotoAlbum(new PhotoAlbumDAO().getById(resultSet.getLong("photo_album_id")));
                photo.setContent(resultSet.getBlob("content"));
                photo.setCaption(resultSet.getString("caption"));
                photo.setUploadDate(resultSet.getDate("upload_date"));

                photos.add(photo);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return photos;
    }


    @Override
    public void update(Photo photo) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Photo SET photo_album_id=?, content=?, caption=?, upload_date=? WHERE id=?");
            preparedStatement.setLong(1, photo.getPhotoAlbum().getId());
            preparedStatement.setBlob(2, photo.getContent());
            preparedStatement.setString(3, photo.getCaption());
            preparedStatement.setDate(4, new Date(photo.getUploadDate().getTime()));
            preparedStatement.setLong(5, photo.getId());

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

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Photo WHERE id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
