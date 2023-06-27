package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.dao.IPostDAO;
import com.solvd.socialnetworkdao.dao.impl.mybatis.MyBatisDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PostDAO extends MyBatisDAO implements IPostDAO {
    @Override
    public Post getById(long id) {
        return executeWithSession(session -> {
            IPostDAO postMapper = session.getMapper(IPostDAO.class);
            return postMapper.getById(id);
        });
    }

    @Override
    public List<Post> getAll() {
        return executeWithSession(session -> {
            IPostDAO postMapper = session.getMapper(IPostDAO.class);
            return postMapper.getAll();
        });
    }

    @Override
    public List<Post> getPostsByProfileId(long profileId) {
        return executeWithSession(session -> {
            IPostDAO postMapper = session.getMapper(IPostDAO.class);
            return postMapper.getPostsByProfileId(profileId);
        });
    }

    @Override
    public Post getByCommentId(long commentId) {
        return executeWithSession(session -> {
            IPostDAO postMapper = session.getMapper(IPostDAO.class);
            return postMapper.getByCommentId(commentId);
        });
    }

    @Override
    public Post getByLikeId(long likeId) {
        return executeWithSession(session -> {
            IPostDAO postMapper = session.getMapper(IPostDAO.class);
            return postMapper.getByLikeId(likeId);
        });
    }

    @Override
    public Post getByPostPhotoId(long postPhotoId) {
        return executeWithSession(session -> {
            IPostDAO postMapper = session.getMapper(IPostDAO.class);
            return postMapper.getByPostPhotoId(postPhotoId);
        });
    }

    @Override
    public Post getByProfileTagId(long profileTagId) {
        return executeWithSession(session -> {
            IPostDAO postMapper = session.getMapper(IPostDAO.class);
            return postMapper.getByProfileTagId(profileTagId);
        });
    }

    @Override
    public void insert(Post post, long profileId) {
        executeWithSession(session -> {
            IPostDAO postMapper = session.getMapper(IPostDAO.class);
            postMapper.insert(post, profileId);
            return null;
        });
    }

    @Override
    public void update(Post post, long profileId) {
        executeWithSession(session -> {
            IPostDAO postMapper = session.getMapper(IPostDAO.class);
            postMapper.update(post, profileId);
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IPostDAO postMapper = session.getMapper(IPostDAO.class);
            postMapper.delete(id);
            return null;
        });
    }
}