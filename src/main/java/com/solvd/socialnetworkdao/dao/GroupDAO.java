package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Group;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO implements IDAO<Group> {
    @Override
    public void create(Group group) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Group (id, group_name, description) VALUES (?, ?, ?)");
            preparedStatement.setLong(1, group.getId());
            preparedStatement.setString(2, group.getGroupName());
            preparedStatement.setString(3, group.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }


    @Override
    public Group getById(long id) {
        Group group = new Group();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Group WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                group.setId(resultSet.getLong("id"));
                group.setGroupName(resultSet.getString("group_name"));
                group.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return group;
    }

    @Override
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Group");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getLong("id"));
                group.setGroupName(resultSet.getString("group_name"));
                group.setDescription(resultSet.getString("description"));

                groups.add(group);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return groups;
    }

    @Override
    public void update(Group group) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Group SET group_name=?, description=? WHERE id=?");
            preparedStatement.setString(1, group.getGroupName());
            preparedStatement.setString(2, group.getDescription());
            preparedStatement.setLong(3, group.getId());

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

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Group WHERE id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
