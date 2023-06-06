package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.ProfileTag;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IDAO;
import com.solvd.socialnetworkdao.interfaces.IProfileTagDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileTagDAO implements IProfileTagDAO {
    @Override
    public void create(ProfileTag profileTag) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO ProfileTag (tagged_post_id, tagged_profile_id) VALUES (?, ?)");

            preparedStatement.setLong(1, profileTag.getTaggedPost().getId());
            preparedStatement.setLong(2, profileTag.getTaggedProfile().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public ProfileTag getById(long id) {
        ProfileTag profileTag = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ProfileTag WHERE id=?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profileTag = new ProfileTag();
                profileTag.setId(resultSet.getLong("id"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return profileTag;
    }

    @Override
    public List<ProfileTag> getAll() {
        List<ProfileTag> profileTags = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ProfileTag");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProfileTag profileTag = new ProfileTag();
                profileTag.setId(resultSet.getLong("id"));

                profileTags.add(profileTag);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return profileTags;
    }

    @Override
    public void update(ProfileTag profileTag) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE ProfileTag SET tagged_post_id=?, tagged_profile_id=? WHERE id=?");

            preparedStatement.setLong(1, profileTag.getTaggedPost().getId());
            preparedStatement.setLong(2, profileTag.getTaggedProfile().getId());
            preparedStatement.setLong(3, profileTag.getId());

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
                    "DELETE FROM ProfileTag WHERE id=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<ProfileTag> getProfileTagsByProfileId(long id) {
        List<ProfileTag> profileTags = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ProfileTag WHERE tagged_profile_id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProfileTag profileTag = new ProfileTag();
                profileTag.setId(resultSet.getLong("id"));

                profileTags.add(profileTag);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return profileTags;
    }
}
