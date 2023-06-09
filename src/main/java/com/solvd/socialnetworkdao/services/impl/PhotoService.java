package com.solvd.socialnetworkdao.services.impl;

import com.solvd.socialnetworkdao.Photo;
import com.solvd.socialnetworkdao.PhotoAlbum;
import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.dao.IPhotoAlbumDAO;
import com.solvd.socialnetworkdao.dao.impl.PhotoAlbumDAO;
import com.solvd.socialnetworkdao.dao.IPhotoDAO;
import com.solvd.socialnetworkdao.dao.impl.PhotoDAO;
import com.solvd.socialnetworkdao.services.IPhotoAlbumService;
import com.solvd.socialnetworkdao.services.IPhotoService;
import com.solvd.socialnetworkdao.services.IPostService;

import java.util.List;

public class PhotoService implements IPhotoService {
    private final IPhotoDAO photoDAO = new PhotoDAO();
    private final IPhotoAlbumDAO photoAlbumDAO = new PhotoAlbumDAO();
    private final IPhotoAlbumService photoAlbumService = new PhotoAlbumService();
    private final IPostService postService = new PostService();

    @Override
    public void insert(Photo photo) {
        photoDAO.insert(photo);
    }

    @Override
    public Photo getById(long id) {
        Photo photo = photoDAO.getById(id);
        setValues(photo);
        return photo;
    }

    @Override
    public List<Photo> getAll() {
        List<Photo> photos = photoDAO.getAll();
        for(Photo photo: photos){
            setValues(photo);
        }
        return photos;
    }

    @Override
    public void update(Photo photo) {
        photoDAO.update(photo);
    }

    @Override
    public void delete(long id) {
        photoDAO.delete(id);
    }

    @Override
    public void setValues(Photo photo) {
        PhotoAlbum photoAlbum = photoAlbumDAO.getByPhotoId(photo.getId());
        photoAlbumService.setValues(photoAlbum);

        List<Post> associatedPosts = photoDAO.getAssociatedPosts(photo.getId());
        for(Post post : associatedPosts){
            postService.setValues(post);
        }

        photo.setPhotoAlbum(photoAlbum);
        photo.setPosts(associatedPosts);
    }
}