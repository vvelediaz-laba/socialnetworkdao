package com.solvd.socialnetworkdao.dao.impl;

import com.solvd.socialnetworkdao.Like;
import com.solvd.socialnetworkdao.dao.ILikeDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO extends DAO implements ILikeDAO {

    @Override
    public void insert(Like like) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO `Like` (liked_post_id, liker_profile_id) VALUES (?, ?)");
            preparedStatement.setLong(1, like.getLikedPost().getId());
            preparedStatement.setLong(2, like.getLikerProfile().getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Like getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM `Like` WHERE id=?");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createLike(resultSet);
        });
    }

    @Override
    public List<Like> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Like`");
            return createLikeList(preparedStatement);
        });
    }

    @Override
    public void update(Like like) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE `Like` SET liked_post_id=?, liker_profile_id=? WHERE id=?");
            preparedStatement.setLong(1, like.getLikedPost().getId());
            preparedStatement.setLong(2, like.getLikerProfile().getId());
            preparedStatement.setLong(3, like.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM `Like` WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public List<Like> getLikesByProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Like` WHERE liker_profile_id=?");
            preparedStatement.setLong(1, id);
            return createLikeList(preparedStatement);
        });
    }

    @Override
    public List<Like> getLikesByPostId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Like` WHERE liked_post_id=?");
            preparedStatement.setLong(1, id);
            return createLikeList(preparedStatement);
        });
    }

    private Like createLike(ResultSet resultSet) throws SQLException {
        Like like = new Like();
        if(resultSet.next()){
            like.setId(resultSet.getLong("id"));
        }
        return like;
    }

    private List<Like> createLikeList(PreparedStatement preparedStatement) throws SQLException{
        List<Like> likes = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Like like = createLike(resultSet);
            likes.add(like);
        }
        return likes;
    }
}
