package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.Photo;
import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.PostPhoto;
import com.solvd.socialnetworkdao.dao.IPhotoDAO;
import com.solvd.socialnetworkdao.dao.IPostDAO;
import com.solvd.socialnetworkdao.dao.IPostPhotoDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.PhotoDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.PostDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.PostPhotoDAO;
import com.solvd.socialnetworkdao.services.IPhotoService;
import com.solvd.socialnetworkdao.services.IPostPhotoService;
import com.solvd.socialnetworkdao.services.IPostService;

import java.util.List;

public class PostPhotoService implements IPostPhotoService {
    private final IPostPhotoDAO postPhotoDAO = new PostPhotoDAO();
    private final IPhotoDAO photoDAO = new PhotoDAO();
    private final IPhotoService photoService = new PhotoService();
    private final IPostDAO postDAO = new PostDAO();
    private final IPostService postService = new PostService();

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

    @Override
    public void setValues(PostPhoto postPhoto) {
        Post post = postDAO.getByPostPhotoId(postPhoto.getId());
        postService.setValues(post);

        Photo photo = photoDAO.getByPostPhotoId(postPhoto.getId());
        photoService.setValues(photo);

        postPhoto.setPost(post);
        postPhoto.setPhoto(photo);
    }
}
