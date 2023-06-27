package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.PhotoAlbum;
import com.solvd.socialnetworkdao.dao.IPhotoAlbumDAO;
import java.util.List;

public class PhotoAlbumDAO extends MyBatisDAO implements IPhotoAlbumDAO {

    @Override
    public PhotoAlbum getById(long id) {
        return executeWithSession(session -> {
            IPhotoAlbumDAO photoAlbumMapper = session.getMapper(IPhotoAlbumDAO.class);
            return photoAlbumMapper.getById(id);
        });
    }

    @Override
    public PhotoAlbum getByPhotoId(long photoId) {
        return executeWithSession(session -> {
            IPhotoAlbumDAO photoAlbumMapper = session.getMapper(IPhotoAlbumDAO.class);
            return photoAlbumMapper.getByPhotoId(photoId);
        });
    }

    @Override
    public List<PhotoAlbum> getPhotoAlbumsByProfileId(long profileId) {
        return executeWithSession(session -> {
            IPhotoAlbumDAO photoAlbumMapper = session.getMapper(IPhotoAlbumDAO.class);
            return photoAlbumMapper.getPhotoAlbumsByProfileId(profileId);
        });
    }

    @Override
    public List<PhotoAlbum> getAll() {
        return executeWithSession(session -> {
            IPhotoAlbumDAO photoAlbumMapper = session.getMapper(IPhotoAlbumDAO.class);
            return photoAlbumMapper.getAll();
        });
    }

    @Override
    public void insert(PhotoAlbum photoAlbum, long profileId) {
        executeWithSession(session -> {
            IPhotoAlbumDAO photoAlbumMapper = session.getMapper(IPhotoAlbumDAO.class);
            photoAlbumMapper.insert(photoAlbum, profileId);
            return null;
        });
    }

    @Override
    public void update(PhotoAlbum photoAlbum, long profileId) {
        executeWithSession(session -> {
            IPhotoAlbumDAO photoAlbumMapper = session.getMapper(IPhotoAlbumDAO.class);
            photoAlbumMapper.update(photoAlbum, profileId);
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IPhotoAlbumDAO photoAlbumMapper = session.getMapper(IPhotoAlbumDAO.class);
            photoAlbumMapper.delete(id);
            return null;
        });
    }
}

