package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.Like;
import com.solvd.socialnetworkdao.dao.ILikeDAO;
import java.util.List;

public class LikeDAO extends MyBatisDAO implements ILikeDAO {

    @Override
    public Like getById(long id) {
        return executeWithSession(session -> {
            ILikeDAO likeMapper = session.getMapper(ILikeDAO.class);
            return likeMapper.getById(id);
        });
    }

    @Override
    public List<Like> getAll() {
        return executeWithSession(session -> {
            ILikeDAO likeMapper = session.getMapper(ILikeDAO.class);
            return likeMapper.getAll();
        });
    }

    @Override
    public List<Like> getLikesByProfileId(long profileId) {
        return executeWithSession(session -> {
            ILikeDAO likeMapper = session.getMapper(ILikeDAO.class);
            return likeMapper.getLikesByProfileId(profileId);
        });
    }

    @Override
    public List<Like> getLikesByPostId(long postId) {
        return executeWithSession(session -> {
            ILikeDAO likeMapper = session.getMapper(ILikeDAO.class);
            return likeMapper.getLikesByPostId(postId);
        });
    }

    @Override
    public void insert(Like like, long postId, long likerId) {
        executeWithSession(session -> {
            ILikeDAO likeMapper = session.getMapper(ILikeDAO.class);
            likeMapper.insert(like, postId, likerId);
            return null;
        });
    }

    @Override
    public void update(Like like, long postId, long likerId) {
        executeWithSession(session -> {
            ILikeDAO likeMapper = session.getMapper(ILikeDAO.class);
            likeMapper.update(like, postId, likerId);
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            ILikeDAO likeMapper = session.getMapper(ILikeDAO.class);
            likeMapper.delete(id);
            return null;
        });
    }
}

