package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.*;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IDAO;
import com.solvd.socialnetworkdao.interfaces.IProfileDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO implements IProfileDAO {

    @Override
    public void create(Profile profile) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Profile (id, full_name, date_of_birth, gender, bio) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, profile.getId());
            preparedStatement.setString(2, profile.getFullName());
            preparedStatement.setDate(3, new Date(profile.getDateOfBirth().getTime()));
            preparedStatement.setString(4, profile.getGender());
            preparedStatement.setString(5, profile.getBio());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Profile getById(long id){
        Profile profile = new Profile();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profile.setId(resultSet.getLong("id"));
                profile.setFullName(resultSet.getString("full_name"));
                profile.setDateOfBirth(resultSet.getDate("date_of_birth"));
                profile.setGender(resultSet.getString("gender"));
                profile.setBio(resultSet.getString("bio"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return profile;
    }

    @Override
    public List<Profile> getAll() {
        List<Profile> profiles = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getLong("id"));
                profile.setFullName(resultSet.getString("full_name"));
                profile.setDateOfBirth(resultSet.getDate("date_of_birth"));
                profile.setGender(resultSet.getString("gender"));
                profile.setBio(resultSet.getString("bio"));

                profiles.add(profile);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return profiles;
    }

    @Override
    public void update(Profile profile) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Profile SET full_name=?, date_of_birth=?, gender=?, bio=? WHERE id=?");
            preparedStatement.setString(1, profile.getFullName());
            preparedStatement.setDate(2, new Date(profile.getDateOfBirth().getTime()));
            preparedStatement.setString(3, profile.getGender());
            preparedStatement.setString(4, profile.getBio());
            preparedStatement.setLong(5, profile.getId());

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

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Profile WHERE id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Profile getByUserId(long id) {
        Profile profile = new Profile();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, User  WHERE Profile.id = User.user_profile_id AND User.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profile.setId(resultSet.getLong("id"));
                profile.setFullName(resultSet.getString("full_name"));
                profile.setDateOfBirth(resultSet.getDate("date_of_birth"));
                profile.setGender(resultSet.getString("gender"));
                profile.setBio(resultSet.getString("bio"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return profile;
    }


    @Override
    public Profile getByCommentId(long id) {
        Profile profile = new Profile();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Comment WHERE Profile.id = Comment.author_profile_id AND Comment.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profile.setId(resultSet.getLong("id"));
                profile.setFullName(resultSet.getString("full_name"));
                profile.setDateOfBirth(resultSet.getDate("date_of_birth"));
                profile.setGender(resultSet.getString("gender"));
                profile.setBio(resultSet.getString("bio"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return profile;
    }

    @Override
    public Profile getByFriendshipId(long id) {
        Profile profile = new Profile();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Friendship WHERE Profile.id = Friendship.requester_profile_id AND Friendship.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profile.setId(resultSet.getLong("id"));
                profile.setFullName(resultSet.getString("full_name"));
                profile.setDateOfBirth(resultSet.getDate("date_of_birth"));
                profile.setGender(resultSet.getString("gender"));
                profile.setBio(resultSet.getString("bio"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return profile;
    }

    @Override
    public Profile getRequestedByFriendshipId(long id) {
        Profile profile = new Profile();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Friendship WHERE Profile.id = Friendship.requested_profile_id AND Friendship.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profile.setId(resultSet.getLong("id"));
                profile.setFullName(resultSet.getString("full_name"));
                profile.setDateOfBirth(resultSet.getDate("date_of_birth"));
                profile.setGender(resultSet.getString("gender"));
                profile.setBio(resultSet.getString("bio"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return profile;
    }
}
