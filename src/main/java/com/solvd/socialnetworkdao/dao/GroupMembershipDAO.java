package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.GroupMembership;
import com.solvd.socialnetworkdao.interfaces.IDAO;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IGroupMembershipDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupMembershipDAO implements IGroupMembershipDAO {
    @Override
    public void create(GroupMembership groupMembership) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO GroupMembership (id, member_profile_id, role, group_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, groupMembership.getId());
            preparedStatement.setLong(2, groupMembership.getMemberProfileId().getId());
            preparedStatement.setString(3, groupMembership.getRole());
            preparedStatement.setLong(4, groupMembership.getGroup().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public GroupMembership getById(long id) {
        GroupMembership groupMembership = new GroupMembership();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GroupMembership WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                groupMembership.setId(resultSet.getLong("id"));
                groupMembership.setRole(resultSet.getString("role"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return groupMembership;
    }

    @Override
    public List<GroupMembership> getAll() {
        List<GroupMembership> groupMemberships = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GroupMembership");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GroupMembership groupMembership = new GroupMembership();
                groupMembership.setId(resultSet.getLong("id"));
                groupMembership.setRole(resultSet.getString("role"));

                groupMemberships.add(groupMembership);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return groupMemberships;
    }

    @Override
    public void update(GroupMembership groupMembership) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE GroupMembership SET member_profile_id=?, role=?, group_id=? WHERE id=?");
            preparedStatement.setLong(1, groupMembership.getMemberProfileId().getId());
            preparedStatement.setString(2, groupMembership.getRole());
            preparedStatement.setLong(3, groupMembership.getGroup().getId());
            preparedStatement.setLong(4, groupMembership.getId());

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

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM GroupMembership WHERE id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<GroupMembership> getGroupMembershipsByProfileId(long id) {
        List<GroupMembership> groupMemberships = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GroupMembership WHERE member_profile_id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GroupMembership groupMembership = new GroupMembership();
                groupMembership.setId(resultSet.getLong("id"));
                groupMembership.setRole(resultSet.getString("role"));

                groupMemberships.add(groupMembership);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return groupMemberships;
    }
}
