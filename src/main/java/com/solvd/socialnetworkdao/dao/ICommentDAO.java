package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Comment;

import java.util.List;

public interface ICommentDAO extends IDAO<Comment>{
    List<Comment> getCommentsByProfileId(long id);
    List<Comment> getCommentsByPostId(long id);
}
