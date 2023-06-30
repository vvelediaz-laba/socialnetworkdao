package com.solvd.socialnetworkdao.services.impl;

import com.solvd.socialnetworkdao.Photo;
import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.PostPhoto;
import com.solvd.socialnetworkdao.dao.IPhotoDAO;
import com.solvd.socialnetworkdao.dao.IPostDAO;
import com.solvd.socialnetworkdao.dao.IPostPhotoDAO;
import com.solvd.socialnetworkdao.services.IPostPhotoService;
import com.solvd.socialnetworkdao.services.IPostService;

import java.util.List;

public class PostPhotoService implements IPostPhotoService {
    private IPostPhotoDAO postPhotoDAO;
    private IPhotoDAO photoDAO;
    private IPostDAO postDAO;
    private IPostService postService;

    public PostPhotoService(IPostPhotoDAO postPhotoDAO, IPhotoDAO photoDAO, IPostDAO postDAO, IPostService postService) {
        this.postPhotoDAO = postPhotoDAO;
        this.photoDAO = photoDAO;
        this.postDAO = postDAO;
        this.postService = postService;
    }

    @Override
    public void insert(PostPhoto postPhoto) {
        postPhotoDAO.insert(postPhoto);
    }

    @Override
    public PostPhoto getById(long id) {
        PostPhoto postPhoto = postPhotoDAO.getById(id);
        setValues(postPhoto);
        return postPhoto;
    }

    @Override
    public List<PostPhoto> getAll() {
        List<PostPhoto> postPhotoList = postPhotoDAO.getAll();
        for(PostPhoto postPhoto : postPhotoList){
            setValues(postPhoto);
        }
        return postPhotoList;
    }

    @Override
    public void update(PostPhoto postPhoto) {
        postPhotoDAO.update(postPhoto);
    }

    @Override
    public void delete(long id) {
        postPhotoDAO.delete(id);
    }

    public IPostPhotoDAO getPostPhotoDAO() {
        return postPhotoDAO;
    }

    public void setPostPhotoDAO(IPostPhotoDAO postPhotoDAO) {
        this.postPhotoDAO = postPhotoDAO;
    }

    public IPhotoDAO getPhotoDAO() {
        return photoDAO;
    }

    public void setPhotoDAO(IPhotoDAO photoDAO) {
        this.photoDAO = photoDAO;
    }

    public IPostDAO getPostDAO() {
        return postDAO;
    }

    public void setPostDAO(IPostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public IPostService getPostService() {
        return postService;
    }

    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    private void setValues(PostPhoto postPhoto) {
        Post post = postDAO.getByPostPhotoId(postPhoto.getId());
        post = postService.getById(post.getId());

        Photo photo = photoDAO.getByPostPhotoId(postPhoto.getId());

        postPhoto.setPost(post);
        postPhoto.setPhoto(photo);
    }
}
