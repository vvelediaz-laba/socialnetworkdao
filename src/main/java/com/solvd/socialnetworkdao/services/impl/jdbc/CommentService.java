package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.Comment;
import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.dao.ICommentDAO;
import com.solvd.socialnetworkdao.dao.IPostDAO;
import com.solvd.socialnetworkdao.dao.IProfileDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.CommentDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.PostDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileDAO;
import com.solvd.socialnetworkdao.services.ICommentService;
import com.solvd.socialnetworkdao.services.IPostService;
import com.solvd.socialnetworkdao.services.IProfileService;

import java.util.List;

public class CommentService implements ICommentService {
    private final ICommentDAO commentDAO = new CommentDAO();
    private final IPostDAO postDAO = new PostDAO();
    private final IPostService postService = new PostService();
    private final IProfileDAO profileDAO = new ProfileDAO();
    private final IProfileService profileService = new ProfileService();

    @Override
    public void insert(Comment comment) {
        commentDAO.insert(comment);
    }

    public Comment getById(long id){
        Comment comment = commentDAO.getById(id);
        setValues(comment);
        return comment;
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> comments = commentDAO.getAll();
        for(Comment comment : comments){
            setValues(comment);
        }
        return comments;
    }

    @Override
    public void update(Comment comment) {
        commentDAO.update(comment);
    }

    @Override
    public void delete(long id) {
        commentDAO.delete(id);
    }

    public void setValues(Comment comment){
        Post commentedPost = postDAO.getByCommentId(comment.getId());
        postService.setValues(commentedPost);

        Profile profile = profileDAO.getByCommentId(comment.getId());
        profileService.setValues(profile);

        comment.setCommentedPost(commentedPost);
        comment.setAuthorProfile(profile);
    }
}
