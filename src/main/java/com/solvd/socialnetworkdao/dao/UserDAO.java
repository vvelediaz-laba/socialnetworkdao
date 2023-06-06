package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.User;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IDAO<User> {
    @Override
    public void create(User user) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO User (id, email, password, registration_date, user_profile_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, new Date(user.getRegistrationDate().getTime()));
            preparedStatement.setLong(5, user.getProfile().getId());

            preparedStatement.executeUpdate();
        } catch (InterruptedException | IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(long id) {
        User user = new User();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRegistrationDate(resultSet.getDate("registration_date"));
            }
        } catch (InterruptedException | IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
            connectionPool.releaseConnection(connection);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRegistrationDate(resultSet.getDate("registration_date"));
                userList.add(user);
            }
        } catch (InterruptedException | IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userList;
    }

    @Override
    public void update(User user){
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE User SET email=?, password=?, registration_date=?, user_profile_id=? WHERE id=?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, new Date(user.getRegistrationDate().getTime()));
            preparedStatement.setLong(4, user.getProfile().getId());
            preparedStatement.setLong(5, user.getId());

            preparedStatement.executeUpdate();
        } catch (InterruptedException | IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(long id){
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (InterruptedException | IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
