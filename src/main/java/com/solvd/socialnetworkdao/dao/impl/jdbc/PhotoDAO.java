package com.solvd.socialnetworkdao.dao.impl.jdbc;

import com.solvd.socialnetworkdao.Photo;
import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.dao.IPhotoDAO;
import com.solvd.socialnetworkdao.utils.ByteBlobConverter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotoDAO extends DAO implements IPhotoDAO {

    @Override
    public void insert(Photo photo, long albumId) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Photo (id, photo_album_id, content, caption, upload_date) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, photo.getId());
            preparedStatement.setLong(2, albumId);
            preparedStatement.setBlob(3, ByteBlobConverter.convertToBlob(photo.getContent()));
            preparedStatement.setString(4, photo.getCaption());
            preparedStatement.setDate(5, new Date(photo.getUploadDate().getTime()));
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Photo getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Photo WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPhoto(resultSet);
        });
    }

    @Override
    public List<Photo> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Photo");
            return createPhotoList(preparedStatement);
        });
    }

    @Override
    public List<Post> getAssociatedPosts(long id) {
        List<Post> posts = new ArrayList<>();
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Post.* FROM POST JOIN Post_Photo ON Post.id = Post_Photo.post_id WHERE Post_Photo.photo_id = ?");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setDateCreated(resultSet.getDate("date_created"));
                post.setContent(resultSet.getString("content"));
                posts.add(post);
            }
            return posts;
        });
    }

    @Override
    public void update(Photo photo, long albumId) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Photo SET photo_album_id=?, content=?, caption=?, upload_date=? WHERE id=?");
            preparedStatement.setLong(1, albumId);
            preparedStatement.setBlob(2, ByteBlobConverter.convertToBlob(photo.getContent()));
            preparedStatement.setString(3, photo.getCaption());
            preparedStatement.setDate(4, new Date(photo.getUploadDate().getTime()));
            preparedStatement.setLong(5, photo.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Photo WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Photo getByPostPhotoId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Photo.* FROM Photo JOIN Post_Photo ON Photo.id = Post_Photo.post_id WHERE Post_Photo.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPhoto(resultSet);
        });
    }

    @Override
    public List<Photo> getPhotosByPhotoAlbumId(long id){
        return executeWithConnection(connection ->{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Photo WHERE Photo.photo_album_id=?");
            preparedStatement.setLong(1, id);
            return createPhotoList(preparedStatement);
        });
    }

    @Override
    public List<Photo> getPhotosByPostId(long id) {
        return executeWithConnection(connection ->{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Photo.* FROM Photo JOIN Post_Photo ON Photo.id = Post_Photo.photo_id JOIN Post ON Post.id = Post_Photo.post_id WHERE Post.id = ?");
            preparedStatement.setLong(1, id);
            return createPhotoList(preparedStatement);
        });
    }

    private Photo createPhoto(ResultSet resultSet) throws SQLException {
        Photo photo = new Photo();
        if(resultSet.next()){
            photo.setId(resultSet.getLong("id"));
            photo.setContent(ByteBlobConverter.convertToBytes(resultSet.getBlob("content")));
            photo.setCaption(resultSet.getString("caption"));
            photo.setUploadDate(resultSet.getDate("upload_date"));
        }
        return photo;
    }

    private List<Photo> createPhotoList(PreparedStatement preparedStatement) throws SQLException{
        List<Photo> photos = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Photo photo = createPhoto(resultSet);
            photos.add(photo);
        }
        return photos;
    }
}
