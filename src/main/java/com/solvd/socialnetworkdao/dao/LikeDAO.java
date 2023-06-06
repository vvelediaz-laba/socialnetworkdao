package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Like;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IDAO;
import com.solvd.socialnetworkdao.interfaces.ILikeDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO implements ILikeDAO {

    @Override
    public void create(Like like) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Like (liked_post_id, liker_profile_id) VALUES (?, ?)");

            preparedStatement.setLong(1, like.getLikedPost().getId());
            preparedStatement.setLong(2, like.getLikerProfile().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Like getById(long id) {
        Like like = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Like WHERE id=?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                like = new Like();
                like.setId(resultSet.getLong("id"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return like;
    }

    @Override
    public List<Like> getAll() {
        List<Like> likes = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Like");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Like like = new Like();
                like.setId(resultSet.getLong("id"));

                likes.add(like);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return likes;
    }

    @Override
    public void update(Like like) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Like SET liked_post_id=?, liker_profile_id=? WHERE id=?");

            preparedStatement.setLong(1, like.getLikedPost().getId());
            preparedStatement.setLong(2, like.getLikerProfile().getId());
            preparedStatement.setLong(3, like.getId());

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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Like WHERE id=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Like> getLikesByProfileId(long id) {
        List<Like> likes = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Like WHERE liker_profile_id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Like like = new Like();
                like.setId(resultSet.getLong("id"));

                likes.add(like);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return likes;
    }
}
