package com.solvd.socialnetworkdao.services.impl;

import com.solvd.socialnetworkdao.*;
import com.solvd.socialnetworkdao.dao.*;
import com.solvd.socialnetworkdao.services.*;

import java.util.List;

public class PostService implements IPostService {
    private IPostDAO postDAO;
    private ILikeDAO likeDAO;
    private ICommentDAO commentDAO;
    private IPhotoDAO photoDAO;
    private IProfileTagDAO profileTagDAO;

    public PostService(IPostDAO postDAO, ILikeDAO likeDAO, ICommentDAO commentDAO, IPhotoDAO photoDAO, IProfileTagDAO profileTagDAO) {
        this.postDAO = postDAO;
        this.likeDAO = likeDAO;
        this.commentDAO = commentDAO;
        this.photoDAO = photoDAO;
        this.profileTagDAO = profileTagDAO;
    }

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

    public IPostDAO getPostDAO() {
        return postDAO;
    }

    public void setPostDAO(IPostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public ILikeDAO getLikeDAO() {
        return likeDAO;
    }

    public void setLikeDAO(ILikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    public ICommentDAO getCommentDAO() {
        return commentDAO;
    }

    public void setCommentDAO(ICommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public IPhotoDAO getPhotoDAO() {
        return photoDAO;
    }

    public void setPhotoDAO(IPhotoDAO photoDAO) {
        this.photoDAO = photoDAO;
    }

    public IProfileTagDAO getProfileTagDAO() {
        return profileTagDAO;
    }

    public void setProfileTagDAO(IProfileTagDAO profileTagDAO) {
        this.profileTagDAO = profileTagDAO;
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
