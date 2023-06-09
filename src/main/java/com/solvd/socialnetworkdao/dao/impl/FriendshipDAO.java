package com.solvd.socialnetworkdao.dao.impl;

import com.solvd.socialnetworkdao.Friendship;
import com.solvd.socialnetworkdao.dao.IDAO;
import com.solvd.socialnetworkdao.dao.IFriendshipDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendshipDAO extends DAO implements IFriendshipDAO {

    @Override
    public void insert(Friendship friendship) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Friendship (id, status, requested_profile_id, requester_profile_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, friendship.getId());
            preparedStatement.setString(2, friendship.getStatus());
            preparedStatement.setLong(3, friendship.getRequestedProfile().getId());
            preparedStatement.setLong(4, friendship.getRequesterProfile().getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Friendship getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Friendship WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createFriendship(resultSet);
        });
    }

    @Override
    public List<Friendship> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Friendship");
            return createFriendshipList(preparedStatement);
        });
    }

    @Override
    public void update(Friendship friendship) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Friendship SET status=?, requested_profile_id=?, requester_profile_id=? WHERE id=?");
            preparedStatement.setString(1, friendship.getStatus());
            preparedStatement.setLong(2, friendship.getRequestedProfile().getId());
            preparedStatement.setLong(3, friendship.getRequesterProfile().getId());
            preparedStatement.setLong(4, friendship.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Friendship WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public List<Friendship> getFriendshipsByProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Friendship WHERE requester_profile_id=?");
            preparedStatement.setLong(1, id);
            return createFriendshipList(preparedStatement);
        });
    }

    private Friendship createFriendship(ResultSet resultSet) throws SQLException{
        Friendship friendship = new Friendship();
        if(resultSet.next()) {
            friendship.setId(resultSet.getLong("id"));
            friendship.setStatus(resultSet.getString("status"));
        }
        return friendship;
    }

    private List<Friendship> createFriendshipList(PreparedStatement preparedStatement) throws SQLException {
        List<Friendship> friendships = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Friendship friendship = createFriendship(resultSet);
            friendships.add(friendship);
        }
        return friendships;
    }
}
