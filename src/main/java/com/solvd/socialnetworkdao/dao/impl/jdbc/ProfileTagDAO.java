package com.solvd.socialnetworkdao.dao.impl.jdbc;

import com.solvd.socialnetworkdao.ProfileTag;
import com.solvd.socialnetworkdao.dao.IProfileTagDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileTagDAO extends DAO implements IProfileTagDAO {
    @Override
    public void insert(ProfileTag profileTag) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Profile_Tag (tagged_post_id, tagged_profile_id) VALUES (?, ?)");
            preparedStatement.setLong(1, profileTag.getTaggedPost().getId());
            preparedStatement.setLong(2, profileTag.getTaggedProfile().getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public ProfileTag getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Profile_Tag WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createProfileTag(resultSet);
        });
    }

    @Override
    public List<ProfileTag> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile_Tag");
            return createProfileTagList(preparedStatement);
        });
    }

    @Override
    public void update(ProfileTag profileTag) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Profile_Tag SET tagged_post_id=?, tagged_profile_id=? WHERE id=?");
            preparedStatement.setLong(1, profileTag.getTaggedPost().getId());
            preparedStatement.setLong(2, profileTag.getTaggedProfile().getId());
            preparedStatement.setLong(3, profileTag.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Profile_Tag WHERE id=?");

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public List<ProfileTag> getProfileTagsByTaggedProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile_Tag WHERE tagged_profile_id=?");
            preparedStatement.setLong(1, id);
            return createProfileTagList(preparedStatement);
        });
    }

    @Override
    public List<ProfileTag> getProfileTagsByProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profile_Tag WHERE tagged_profile_id=?");
            preparedStatement.setLong(1, id);
            return createProfileTagList(preparedStatement);
        });
    }

    private ProfileTag createProfileTag(ResultSet resultSet) throws SQLException {
        ProfileTag profileTag = new ProfileTag();
        if(resultSet.next()){
            profileTag.setId(resultSet.getLong("id"));
        }
        return profileTag;
    }

    private List<ProfileTag> createProfileTagList(PreparedStatement preparedStatement) throws SQLException{
        List<ProfileTag> profileTags = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            ProfileTag profileTag = createProfileTag(resultSet);
            profileTags.add(profileTag);
        }
        return profileTags;
    }
}
