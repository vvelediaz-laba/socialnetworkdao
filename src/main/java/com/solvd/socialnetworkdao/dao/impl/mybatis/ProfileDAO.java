package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.dao.IProfileDAO;

import java.util.List;

public class ProfileDAO extends MyBatisDAO implements IProfileDAO {
    @Override
    public Profile getById(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getById(id);
        });
    }


    @Override
    public List<Profile> getAll() {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getAll();
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            profileMapper.delete(id);
            return null;
        });
    }

    @Override
    public void insert(Profile profile) {
        executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            profileMapper.insert(profile);
            return null;
        });
    }

    @Override
    public void update(Profile profile) {
        executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            profileMapper.update(profile);
            return null;
        });
    }

    @Override
    public Profile getByUserId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getByUserId(id);
        });
    }

    @Override
    public Profile getByCommentId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getByCommentId(id);
        });
    }

    @Override
    public Profile getByFriendshipId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getByFriendshipId(id);
        });
    }

    @Override
    public Profile getRequestedByFriendshipId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getRequestedByFriendshipId(id);
        });
    }

    @Override
    public Profile getByGroupMembershipId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getByGroupMembershipId(id);
        });
    }

    @Override
    public Profile getByLikeId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getByLikeId(id);
        });
    }

    @Override
    public Profile getSenderByMessageId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getSenderByMessageId(id);
        });
    }

    @Override
    public Profile getReceiverByMessageId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getReceiverByMessageId(id);
        });
    }

    @Override
    public Profile getByPhotoAlbumId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getByPhotoAlbumId(id);
        });
    }

    @Override
    public Profile getByPostId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getByPostId(id);
        });
    }

    @Override
    public Profile getByProfileTagId(long id) {
        return executeWithSession(session -> {
            IProfileDAO profileMapper = session.getMapper(IProfileDAO.class);
            return profileMapper.getByProfileTagId(id);
        });
    }
}
