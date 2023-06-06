package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.Comment;
import com.solvd.socialnetworkdao.dao.CommentDAO;
import com.solvd.socialnetworkdao.dao.PostDAO;
import com.solvd.socialnetworkdao.dao.ProfileDAO;

public class CommentService {
    public Comment getComment(long id){
        Comment comment = new CommentDAO().getById(id);
        comment.setCommentedPost(new PostDAO().getByCommentId(id));
        comment.setAuthorProfile(new ProfileDAO().getByCommentId(id));

        return comment;
    }
}
