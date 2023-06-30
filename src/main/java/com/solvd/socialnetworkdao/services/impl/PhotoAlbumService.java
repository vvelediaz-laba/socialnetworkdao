package com.solvd.socialnetworkdao.services.impl;

import com.solvd.socialnetworkdao.Photo;
import com.solvd.socialnetworkdao.PhotoAlbum;
import com.solvd.socialnetworkdao.dao.IPhotoAlbumDAO;
import com.solvd.socialnetworkdao.dao.IPhotoDAO;
import com.solvd.socialnetworkdao.services.IPhotoAlbumService;

import java.util.List;

public class PhotoAlbumService implements IPhotoAlbumService {
    private IPhotoAlbumDAO photoAlbumDAO;
    private IPhotoDAO photoDAO;

    public PhotoAlbumService(IPhotoAlbumDAO photoAlbumDAO, IPhotoDAO photoDAO){
        this.photoAlbumDAO = photoAlbumDAO;
        this.photoDAO = photoDAO;
    }

    @Override
    public void insert(PhotoAlbum photoAlbum, long profileId) {
        photoAlbumDAO.insert(photoAlbum, profileId);
    }

    @Override
    public PhotoAlbum getById(long id) {
        PhotoAlbum photoAlbum = photoAlbumDAO.getById(id);
        setValues(photoAlbum);
        return photoAlbum;
    }

    @Override
    public List<PhotoAlbum> getAll() {
        List<PhotoAlbum> photoAlbums = photoAlbumDAO.getAll();
        for(PhotoAlbum photoAlbum : photoAlbums){
            setValues(photoAlbum);
        }
        return photoAlbums;
    }

    @Override
    public void update(PhotoAlbum photoAlbum, long profileId) {
        photoAlbumDAO.update(photoAlbum, profileId);
    }

    @Override
    public void delete(long id) {
        photoAlbumDAO.delete(id);
    }

    public IPhotoAlbumDAO getPhotoAlbumDAO() {
        return photoAlbumDAO;
    }

    public void setPhotoAlbumDAO(IPhotoAlbumDAO photoAlbumDAO) {
        this.photoAlbumDAO = photoAlbumDAO;
    }

    public IPhotoDAO getPhotoDAO() {
        return photoDAO;
    }

    public void setPhotoDAO(IPhotoDAO photoDAO) {
        this.photoDAO = photoDAO;
    }

    private void setValues(PhotoAlbum photoAlbum) {
        List<Photo> photos;

        try {
            photos = photoDAO.getPhotosByPhotoAlbumId(photoAlbum.getId());
        }catch (NullPointerException e){
            return;
        }

        photoAlbum.setPhotos(photos);
    }
}
