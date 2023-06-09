package com.solvd.socialnetworkdao.dao.impl.jdbc;

import com.solvd.socialnetworkdao.PhotoAlbum;
import com.solvd.socialnetworkdao.dao.IPhotoAlbumDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PhotoAlbumDAO extends DAO implements IPhotoAlbumDAO {
    @Override
    public void insert(PhotoAlbum photoAlbum) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO PhotoAlbum (id, album_profile_id, photo_album_name, date_created) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, photoAlbum.getId());
            preparedStatement.setLong(2, photoAlbum.getAlbumProfile().getId());
            preparedStatement.setString(3, photoAlbum.getPhotoAlbumName());
            preparedStatement.setDate(4, new Date(photoAlbum.getDateCreated().getTime()));
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public PhotoAlbum getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PhotoAlbum WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPhotoAlbum(resultSet);
        });
    }

    @Override
    public List<PhotoAlbum> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PhotoAlbum");
            return createPhotoAlbumList(preparedStatement);
        });
    }

    @Override
    public void update(PhotoAlbum photoAlbum) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Photo_Album SET album_profile_id=?, photo_album_name=?, date_created=? WHERE id=?");
            preparedStatement.setLong(1, photoAlbum.getAlbumProfile().getId());
            preparedStatement.setString(2, photoAlbum.getPhotoAlbumName());
            preparedStatement.setDate(3, new Date(photoAlbum.getDateCreated().getTime()));
            preparedStatement.setLong(4, photoAlbum.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Photo_Album WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public PhotoAlbum getByPhotoId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Photo_Album.* FROM Photo_Album JOIN Photo ON Photo_Album.id = Photo.photo_album.id WHERE Photo.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPhotoAlbum(resultSet);
        });
    }

    @Override
    public List<PhotoAlbum> getPhotoAlbumsByProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Photo_Album WHERE album_profile_id=?");
            preparedStatement.setLong(1, id);
            return createPhotoAlbumList(preparedStatement);
        });
    }
    
    private PhotoAlbum createPhotoAlbum(ResultSet resultSet) throws SQLException {
        PhotoAlbum photoAlbum = new PhotoAlbum();
        if(resultSet.next()){
            photoAlbum.setId(resultSet.getLong("id"));
            photoAlbum.setPhotoAlbumName(resultSet.getString("photo_album_name"));
            photoAlbum.setDateCreated(resultSet.getDate("date_created"));
        }
        return photoAlbum;
    }

    private List<PhotoAlbum> createPhotoAlbumList(PreparedStatement preparedStatement) throws SQLException{
        List<PhotoAlbum> photoAlbums = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            PhotoAlbum photoAlbum = createPhotoAlbum(resultSet);
            photoAlbums.add(photoAlbum);
        }
        return photoAlbums;
    }
}
