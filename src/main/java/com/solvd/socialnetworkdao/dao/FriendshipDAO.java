package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Friendship;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendshipDAO implements IDAO<Friendship> {

    @Override
    public void create(Friendship friendship) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Friendship (id, status, requested_profile_id, requester_profile_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, friendship.getId());
            preparedStatement.setString(2, friendship.getStatus());
            preparedStatement.setLong(3, friendship.getRequestedProfile().getId());
            preparedStatement.setLong(4, friendship.getRequesterProfile().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Friendship getById(long id) {
        Friendship friendship = new Friendship();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Friendship WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                friendship.setId(resultSet.getLong("id"));
                friendship.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return friendship;
    }

    @Override
    public List<Friendship> getAll() {
        List<Friendship> friendships = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Friendship");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Friendship friendship = new Friendship();
                friendship.setId(resultSet.getLong("id"));
                friendship.setStatus(resultSet.getString("status"));

                friendships.add(friendship);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return friendships;
    }

    @Override
    public void update(Friendship friendship) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Friendship SET status=?, requested_profile_id=?, requester_profile_id=? WHERE id=?");
            preparedStatement.setString(1, friendship.getStatus());
            preparedStatement.setLong(2, friendship.getRequestedProfile().getId());
            preparedStatement.setLong(3, friendship.getRequesterProfile().getId());
            preparedStatement.setLong(4, friendship.getId());

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

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Friendship WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
