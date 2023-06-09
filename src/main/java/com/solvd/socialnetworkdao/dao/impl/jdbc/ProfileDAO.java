package com.solvd.socialnetworkdao.dao.impl.jdbc;

import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.dao.IProfileDAO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO extends DAO implements IProfileDAO {

    @Override
    public void insert(Profile profile) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Profile (id, full_name, date_of_birth, gender, bio) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, profile.getId());
            preparedStatement.setString(2, profile.getFullName());
            preparedStatement.setDate(3, new Date(profile.getDateOfBirth().getTime()));
            preparedStatement.setString(4, profile.getGender());
            preparedStatement.setString(5, profile.getBio());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Profile getById(long id){
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    @Override
    public List<Profile> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile");
            return createProfileList(preparedStatement);
        });
    }

    @Override
    public void update(Profile profile) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Profile SET full_name=?, date_of_birth=?, gender=?, bio=? WHERE id=?");
            preparedStatement.setString(1, profile.getFullName());
            preparedStatement.setDate(2, new Date(profile.getDateOfBirth().getTime()));
            preparedStatement.setString(3, profile.getGender());
            preparedStatement.setString(4, profile.getBio());
            preparedStatement.setLong(5, profile.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Profile WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Profile getByUserId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, User  WHERE Profile.id = User.user_profile_id AND User.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }


    @Override
    public Profile getByCommentId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Comment WHERE Profile.id = Comment.author_profile_id AND Comment.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    @Override
    public Profile getByFriendshipId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Friendship WHERE Profile.id = Friendship.requester_profile_id AND Friendship.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    @Override
    public Profile getRequestedByFriendshipId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Friendship WHERE Profile.id = Friendship.requested_profile_id AND Friendship.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    @Override
    public Profile getByGroupMembershipId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Group_Membership WHERE Profile.id = Group_Membership.member_profile_id AND Group_Membership.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    @Override
    public Profile getSenderByMessageId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Message WHERE Profile.id = Message.sender_profile_id AND Message.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    @Override
    public Profile getReceiverByMessageId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Message WHERE Profile.id = Message.receiver_profile_id AND Message.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    @Override
    public Profile getByLikeId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, `Like` WHERE Profile.id = `Like`.liker_profile_id AND `Like`.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    @Override
    public Profile getByPhotoAlbumId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Photo_Album WHERE Profile.id = Photo_Album.album_profile_id AND Photo_Album.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    @Override
    public Profile getByPostId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Post WHERE Profile.id = Post.poster_profile_id AND Post.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    @Override
    public Profile getByProfileTagId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Profile.* FROM Profile, Profile_Tag WHERE Profile.id = Profile_Tag.tagged_profile_id AND Profile_Tag.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfile(resultSet);
        });
    }

    private Profile createProfile(ResultSet resultSet) throws SQLException {
        Profile profile = new Profile();
        if(resultSet.next()){
            profile.setId(resultSet.getLong("id"));
            profile.setFullName(resultSet.getString("full_name"));
            profile.setDateOfBirth(resultSet.getDate("date_of_birth"));
            profile.setGender(resultSet.getString("gender"));
            profile.setBio(resultSet.getString("bio"));
        }
        return profile;
    }

    private List<Profile> createProfileList(PreparedStatement preparedStatement) throws SQLException{
        List<Profile> profiles = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Profile profile = createProfile(resultSet);
            profiles.add(profile);
        }
        return profiles;
    }
}
