package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.PostPhoto;
import com.solvd.socialnetworkdao.dao.IPostPhotoDAO;

import java.util.List;

public class PostPhotoDAO extends MyBatisDAO implements IPostPhotoDAO {

    @Override
    public PostPhoto getById(long id) {
        return executeWithSession(session -> {
            IPostPhotoDAO postPhotoMapper = session.getMapper(IPostPhotoDAO.class);
            return postPhotoMapper.getById(id);
        });
    }

    @Override
    public List<PostPhoto> getAll() {
        return executeWithSession(session -> {
            IPostPhotoDAO postPhotoMapper = session.getMapper(IPostPhotoDAO.class);
            return postPhotoMapper.getAll();
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IPostPhotoDAO postPhotoMapper = session.getMapper(IPostPhotoDAO.class);
            postPhotoMapper.delete(id);
            return null;
        });
    }

    @Override
    public void insert(PostPhoto postPhoto) {
        executeWithSession(session -> {
            IPostPhotoDAO postPhotoMapper = session.getMapper(IPostPhotoDAO.class);
            postPhotoMapper.insert(postPhoto);
            return null;
        });
    }

    @Override
    public void update(PostPhoto postPhoto) {
        executeWithSession(session -> {
            IPostPhotoDAO postPhotoMapper = session.getMapper(IPostPhotoDAO.class);
            postPhotoMapper.update(postPhoto);
            return null;
        });
    }
}
