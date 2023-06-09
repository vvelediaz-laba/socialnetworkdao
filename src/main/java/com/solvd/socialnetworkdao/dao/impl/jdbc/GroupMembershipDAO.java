package com.solvd.socialnetworkdao.dao.impl.jdbc;

import com.solvd.socialnetworkdao.GroupMembership;
import com.solvd.socialnetworkdao.dao.IGroupMembershipDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupMembershipDAO extends DAO implements IGroupMembershipDAO {
    @Override
    public void insert(GroupMembership groupMembership) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Group_Membership (id, member_profile_id, role, group_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, groupMembership.getId());
            preparedStatement.setLong(2, groupMembership.getMemberProfile().getId());
            preparedStatement.setString(3, groupMembership.getRole());
            preparedStatement.setLong(4, groupMembership.getGroup().getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public GroupMembership getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Group_Membership WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createGroupMembership(resultSet);
        });
    }

    @Override
    public List<GroupMembership> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Group_Membership");
            return createGroupMembershipList(preparedStatement);
        });
    }

    @Override
    public void update(GroupMembership groupMembership) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Group_Membership SET member_profile_id=?, role=?, group_id=? WHERE id=?");
            preparedStatement.setLong(1, groupMembership.getMemberProfile().getId());
            preparedStatement.setString(2, groupMembership.getRole());
            preparedStatement.setLong(3, groupMembership.getGroup().getId());
            preparedStatement.setLong(4, groupMembership.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Group_Membership WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public List<GroupMembership> getGroupMembershipsByProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Group_Membership WHERE member_profile_id=?");
            preparedStatement.setLong(1, id);
            return createGroupMembershipList(preparedStatement);
        });
    }

    @Override
    public List<GroupMembership> getMembershipsByGroupId(long id) {
        return executeWithConnection(connection -> {
            List<GroupMembership> groupMemberships = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Group_Membership WHERE group_id=?");
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                GroupMembership groupMembership = new GroupMembership();
                groupMembership.setId(resultSet.getLong("id"));
                groupMembership.setRole(resultSet.getString("role"));
                groupMemberships.add(groupMembership);
            }
            return groupMemberships;
        });
    }

    private GroupMembership createGroupMembership(ResultSet resultSet) throws SQLException {
        GroupMembership groupMembership = new GroupMembership();
        if(resultSet.next()) {
            groupMembership.setId(resultSet.getLong("id"));
            groupMembership.setRole(resultSet.getString("role"));
        }
        return groupMembership;
    }

    private List<GroupMembership> createGroupMembershipList(PreparedStatement preparedStatement) throws SQLException{
        List<GroupMembership> groupMemberships = new ArrayList<>();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()){
            GroupMembership groupMembership = createGroupMembership(resultSet);
            groupMemberships.add(groupMembership);
        }
        return groupMemberships;
    }
}
