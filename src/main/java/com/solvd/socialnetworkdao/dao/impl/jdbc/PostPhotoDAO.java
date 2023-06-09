package com.solvd.socialnetworkdao.dao.impl.jdbc;

import com.solvd.socialnetworkdao.PostPhoto;
import com.solvd.socialnetworkdao.dao.IPostPhotoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostPhotoDAO extends DAO implements IPostPhotoDAO {
    @Override
    public void insert(PostPhoto postPhoto) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Post_Photo (id, post_id, photo_id) VALUES (?, ?, ?)");
            preparedStatement.setLong(1, postPhoto.getId());
            preparedStatement.setLong(2, postPhoto.getPost().getId());
            preparedStatement.setLong(3, postPhoto.getPhoto().getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public PostPhoto getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Post_Photo WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPostPhoto(resultSet);
        })
    }

    @Override
    public List<PostPhoto> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Post_Photo");
            return createPostPhotoList(preparedStatement);
        });
    }

    @Override
    public void update(PostPhoto postPhoto) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Post_Photo SET post_id=?, photo_id=? WHERE id=?");
            preparedStatement.setLong(1, postPhoto.getPost().getId());
            preparedStatement.setLong(2, postPhoto.getPhoto().getId());
            preparedStatement.setLong(3, postPhoto.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Post_Photo WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    private PostPhoto createPostPhoto(ResultSet resultSet) throws SQLException {
        PostPhoto postPhoto = new PostPhoto();
        if(resultSet.next()) {
            postPhoto.setId(resultSet.getLong("id"));
        }
        return postPhoto;
    }

    private List<PostPhoto> createPostPhotoList(PreparedStatement preparedStatement) throws SQLException {
        List<PostPhoto> postPhotos = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            PostPhoto postPhoto = createPostPhoto(resultSet);
            postPhotos.add(postPhoto);
        }
        return postPhotos;
    }
}
