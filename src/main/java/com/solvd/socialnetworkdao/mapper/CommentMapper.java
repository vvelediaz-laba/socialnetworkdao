package com.solvd.socialnetworkdao.mapper;

import com.solvd.socialnetworkdao.Comment;

import java.util.List;

public interface CommentMapper {

    Comment getCommentById(long id);

    List<Comment> getAllComments();

    List<Comment> getCommentsByPostId(long postId);

    void insertComment(Comment comment, long commentedPostId, long authorProfileId);

    void updateComment(Comment comment, long commentedPostId, long authorProfileId);

    void deleteComment(long id);

}
