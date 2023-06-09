package com.solvd.socialnetworkdao.dao.impl;

import com.solvd.socialnetworkdao.Group;
import com.solvd.socialnetworkdao.GroupMembership;
import com.solvd.socialnetworkdao.dao.IDAO;
import com.solvd.socialnetworkdao.dao.IGroupDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends DAO implements IGroupDAO {
    @Override
    public void insert(Group group) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO `Group` (id, group_name, description) VALUES (?, ?, ?)");
            preparedStatement.setLong(1, group.getId());
            preparedStatement.setString(2, group.getGroupName());
            preparedStatement.setString(3, group.getDescription());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Group getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Group` WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createGroup(resultSet);
        });
    }

    @Override
    public List<Group> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Group`");
            return createGroupList(preparedStatement);
        });
    }

    @Override
    public void update(Group group) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE `Group` SET group_name=?, description=? WHERE id=?");
            preparedStatement.setString(1, group.getGroupName());
            preparedStatement.setString(2, group.getDescription());
            preparedStatement.setLong(3, group.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `Group` WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Group getByGroupMembershipId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT `Group`.* FROM `Group` JOIN Group_Membership ON `Group`.id = Group_Membership.group_id WHERE Group_Membership.id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createGroup(resultSet);
        });
    }

    private Group createGroup(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        if(resultSet.next()) {
            group.setId(resultSet.getLong("id"));
            group.setGroupName(resultSet.getString("group_name"));
            group.setDescription(resultSet.getString("description"));
        }
        return group;
    }

    private List<Group> createGroupList(PreparedStatement preparedStatement) throws SQLException {
        List<Group> groups = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Group group = createGroup(resultSet);
            groups.add(group);
        }
        return groups;
    }
}
