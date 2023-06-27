package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Comment;

import java.util.List;

public interface ICommentDAO extends IDAO<Comment>{
    void insert(Comment comment, long postId, long authorId);
    void update(Comment comment, long postId, long authorId);
    List<Comment> getCommentsByProfileId(long id);
    List<Comment> getCommentsByPostId(long id);
}
