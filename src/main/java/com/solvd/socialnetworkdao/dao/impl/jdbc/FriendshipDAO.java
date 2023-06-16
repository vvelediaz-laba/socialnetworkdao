package com.solvd.socialnetworkdao.dao.impl.jdbc;

import com.solvd.socialnetworkdao.Friendship;
import com.solvd.socialnetworkdao.dao.IFriendshipDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendshipDAO extends DAO implements IFriendshipDAO {

    @Override
    public void insert(Friendship friendship, long requesterProfileId, long requestedProfileId) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Friendship (id, status, requested_profile_id, requester_profile_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, friendship.getId());
            preparedStatement.setString(2, friendship.getStatus());
            preparedStatement.setLong(3, requesterProfileId);
            preparedStatement.setLong(4, requesterProfileId);
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
    public void update(Friendship friendship, long requesterProfileId, long requestedProfileId) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Friendship SET status=?, requested_profile_id=?, requester_profile_id=? WHERE id=?");
            preparedStatement.setString(1, friendship.getStatus());
            preparedStatement.setLong(2, requestedProfileId);
            preparedStatement.setLong(3, requesterProfileId);
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
    public List<Friendship> getFriendshipsBySenderProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Friendship WHERE requester_profile_id=?");
            preparedStatement.setLong(1, id);
            return createFriendshipList(preparedStatement);
        });
    }

    @Override
    public List<Friendship> getFriendshipsByReceiverProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Friendship WHERE requested_profile_id=?");
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
