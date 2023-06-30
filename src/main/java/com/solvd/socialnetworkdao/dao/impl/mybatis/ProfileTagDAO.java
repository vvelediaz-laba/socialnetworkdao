package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.ProfileTag;
import com.solvd.socialnetworkdao.dao.IProfileTagDAO;

import java.util.List;

public class ProfileTagDAO extends MyBatisDAO implements IProfileTagDAO {
    @Override
    public ProfileTag getById(long id) {
        return executeWithSession(session -> {
            IProfileTagDAO profileTagMapper = session.getMapper(IProfileTagDAO.class);
            return profileTagMapper.getById(id);
        });
    }

    @Override
    public List<ProfileTag> getAll() {
        return executeWithSession(session -> {
            IProfileTagDAO profileTagMapper = session.getMapper(IProfileTagDAO.class);
            return profileTagMapper.getAll();
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IProfileTagDAO profileTagMapper = session.getMapper(IProfileTagDAO.class);
            profileTagMapper.delete(id);
            return null;
        });
    }

    @Override
    public void insert(ProfileTag profileTag, long taggedPost, long taggedProfileId) {
        executeWithSession(session -> {
            IProfileTagDAO profileTagMapper = session.getMapper(IProfileTagDAO.class);
            profileTagMapper.insert(profileTag, taggedPost, taggedProfileId);
            return null;
        });
    }

    @Override
    public void update(ProfileTag profileTag, long taggedPost, long taggedProfileId) {
        executeWithSession(session -> {
            IProfileTagDAO profileTagMapper = session.getMapper(IProfileTagDAO.class);
            profileTagMapper.update(profileTag, taggedPost, taggedProfileId);
            return null;
        });
    }

    @Override
    public List<ProfileTag> getProfileTagsByPostId(long id) {
        return executeWithSession(session -> {
            IProfileTagDAO profileTagMapper = session.getMapper(IProfileTagDAO.class);
            return profileTagMapper.getProfileTagsByPostId(id);
        });
    }

    @Override
    public List<ProfileTag> getProfileTagsByProfileId(long id) {
        return executeWithSession(session -> {
            IProfileTagDAO profileTagMapper = session.getMapper(IProfileTagDAO.class);
            return profileTagMapper.getProfileTagsByProfileId(id);
        });
    }
}
