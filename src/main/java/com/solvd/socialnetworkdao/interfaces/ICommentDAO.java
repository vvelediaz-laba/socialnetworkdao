package com.solvd.socialnetworkdao.interfaces;

import com.solvd.socialnetworkdao.Comment;

import java.util.List;

public interface ICommentDAO extends IDAO<Comment>{
    List<Comment> getCommentsByProfileId(long id);
}
