package com.solvd.socialnetworkdao.dao.impl;

import com.solvd.socialnetworkdao.User;
import com.solvd.socialnetworkdao.dao.IDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO implements IDAO<User> {
    @Override
    public void insert(User user) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO User (id, email, password, registration_date, user_profile_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, new Date(user.getRegistrationDate().getTime()));
            preparedStatement.setLong(5, user.getProfile().getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public User getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createUser(resultSet);
        });
    }

    @Override
    public List<User> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User");
            return createUserList(preparedStatement);
        });
    }

    @Override
    public void update(User user){
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE User SET email=?, password=?, registration_date=?, user_profile_id=? WHERE id=?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, new Date(user.getRegistrationDate().getTime()));
            preparedStatement.setLong(4, user.getProfile().getId());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id){
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        if(resultSet.next()){
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRegistrationDate(resultSet.getDate("registration_date"));
        }
        return user;
    }

    private List<User> createUserList(PreparedStatement preparedStatement) throws SQLException{
        List<User> users = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            User user = createUser(resultSet);
            users.add(user);
        }
        return users;
    }
}
