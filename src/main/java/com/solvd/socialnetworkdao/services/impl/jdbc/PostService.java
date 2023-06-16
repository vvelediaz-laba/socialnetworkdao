package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.*;
import com.solvd.socialnetworkdao.dao.*;
import com.solvd.socialnetworkdao.dao.impl.jdbc.*;
import com.solvd.socialnetworkdao.services.*;

import java.util.List;

public class PostService implements IPostService {
    private final IPostDAO postDAO = new PostDAO();
    private final ILikeDAO likeDAO =  new LikeDAO();
    private final ICommentDAO commentDAO = new CommentDAO();
    private final IPhotoDAO photoDAO = new PhotoDAO();
    private final IProfileTagDAO profileTagDAO = new ProfileTagDAO();

    @Override
    public void insert(Post post, long profileId) {
        postDAO.insert(post, profileId);
    }

    @Override
    public Post getById(long id) {
        Post post = postDAO.getById(id);
        setValues(post);
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = postDAO.getAll();
        for(Post post : posts){
            setValues(post);
        }
        return posts;
    }

    @Override
    public void update(Post post, long profileId) {
        postDAO.update(post, profileId);
    }

    @Override
    public void delete(long id) {
        postDAO.delete(id);
    }

    public void setValues(Post post) {
        List<Like> likes = likeDAO.getLikesByPostId(post.getId());

        List<Comment> comments = commentDAO.getCommentsByPostId(post.getId());

        List<Photo> photos = photoDAO.getPhotosByPostId(post.getId());

        List<ProfileTag> profileTags = profileTagDAO.getProfileTagsByPostId(post.getId());

        post.setLikes(likes);
        post.setComments(comments);
        post.setPhotos(photos);
        post.setTags(profileTags);
    }
}
