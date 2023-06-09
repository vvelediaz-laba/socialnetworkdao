package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.Photo;
import com.solvd.socialnetworkdao.PhotoAlbum;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.dao.IPhotoAlbumDAO;
import com.solvd.socialnetworkdao.dao.IProfileDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.PhotoAlbumDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.PhotoDAO;
import com.solvd.socialnetworkdao.services.IPhotoAlbumService;
import com.solvd.socialnetworkdao.services.IProfileService;

import java.util.List;

public class PhotoAlbumService implements IPhotoAlbumService {
    private final IPhotoAlbumDAO photoAlbumDAO = new PhotoAlbumDAO();
    private final IProfileDAO profileDAO = new ProfileDAO();
    private final IProfileService profileService = new ProfileService();
    private final PhotoDAO photoDAO = new PhotoDAO();
    private final PhotoService photoService = new PhotoService();

    @Override
    public void insert(PhotoAlbum photoAlbum) {
        photoAlbumDAO.insert(photoAlbum);
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
    public void update(PhotoAlbum photoAlbum) {
        photoAlbumDAO.update(photoAlbum);
    }

    @Override
    public void delete(long id) {
        photoAlbumDAO.delete(id);
    }

    @Override
    public void setValues(PhotoAlbum photoAlbum) {
        Profile ownerProfile = profileDAO.getByPhotoAlbumId(photoAlbum.getId());
        profileService.setValues(ownerProfile);
        photoAlbum.setAlbumProfile(ownerProfile);

        List<Photo> photos = photoDAO.getPhotosByPhotoAlbumId(photoAlbum.getId());
        for(Photo photo : photos){
            photoService.setValues(photo);
        }
        photoAlbum.setPhotos(photos);
    }
}
