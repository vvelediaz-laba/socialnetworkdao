package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.Comment;
import com.solvd.socialnetworkdao.dao.ICommentDAO;
import java.util.List;

public class CommentDAO extends MyBatisDAO implements ICommentDAO {

    @Override
    public Comment getById(long id) {
        return executeWithSession(session -> {
            ICommentDAO commentMapper = session.getMapper(ICommentDAO.class);
            return commentMapper.getById(id);
        });
    }

    @Override
    public List<Comment> getAll() {
        return executeWithSession(session -> {
            ICommentDAO commentMapper = session.getMapper(ICommentDAO.class);
            return commentMapper.getAll();
        });    }

    @Override
    public List<Comment> getCommentsByPostId(long postId) {
        return executeWithSession(session -> {
            ICommentDAO commentMapper = session.getMapper(ICommentDAO.class);
            return commentMapper.getCommentsByPostId(postId);
        });
    }

    @Override
    public List<Comment> getCommentsByProfileId(long profileId) {
        return executeWithSession(session -> {
            ICommentDAO commentMapper = session.getMapper(ICommentDAO.class);
            return commentMapper.getCommentsByPostId(profileId);
        });
    }

    @Override
    public void insert(Comment comment, long postId, long authorId) {
        executeWithSession(session -> {
            ICommentDAO commentMapper = session.getMapper(ICommentDAO.class);
            commentMapper.insert(comment, postId, authorId);
            return null;
        });
    }

    @Override
    public void update(Comment comment, long postId, long authorId) {
        executeWithSession(session -> {
            ICommentDAO commentMapper = session.getMapper(ICommentDAO.class);
            commentMapper.update(comment, postId, authorId);
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            ICommentDAO commentMapper = session.getMapper(ICommentDAO.class);
            commentMapper.delete(id);
            return null;
        });
    }
}

