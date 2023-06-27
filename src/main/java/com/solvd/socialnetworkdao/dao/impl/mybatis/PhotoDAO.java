package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.Photo;
import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.dao.IPhotoDAO;
import java.util.List;

public class PhotoDAO extends MyBatisDAO implements IPhotoDAO {

    @Override
    public Photo getById(long id) {
        return executeWithSession(session -> {
            IPhotoDAO photoMapper = session.getMapper(IPhotoDAO.class);
            return photoMapper.getById(id);
        });
    }

    @Override
    public List<Photo> getAll() {
        return executeWithSession(session -> {
            IPhotoDAO photoMapper = session.getMapper(IPhotoDAO.class);
            return photoMapper.getAll();
        });
    }

    @Override
    public Photo getByPostPhotoId(long postPhotoId) {
        return executeWithSession(session -> {
            IPhotoDAO photoMapper = session.getMapper(IPhotoDAO.class);
            return photoMapper.getByPostPhotoId(postPhotoId);
        });
    }

    @Override
    public List<Post> getAssociatedPosts(long photoId) {
        return executeWithSession(session -> {
            IPhotoDAO photoMapper = session.getMapper(IPhotoDAO.class);
            return photoMapper.getAssociatedPosts(photoId);
        });
    }

    @Override
    public List<Photo> getPhotosByPhotoAlbumId(long photoAlbumId) {
        return executeWithSession(session -> {
            IPhotoDAO photoMapper = session.getMapper(IPhotoDAO.class);
            return photoMapper.getPhotosByPhotoAlbumId(photoAlbumId);
        });
    }

    @Override
    public List<Photo> getPhotosByPostId(long postId) {
        return executeWithSession(session -> {
            IPhotoDAO photoMapper = session.getMapper(IPhotoDAO.class);
            return photoMapper.getPhotosByPostId(postId);
        });
    }

    @Override
    public void insert(Photo photo, long albumId) {
        executeWithSession(session -> {
            IPhotoDAO photoMapper = session.getMapper(IPhotoDAO.class);
            photoMapper.insert(photo, albumId);
            return null;
        });
    }

    @Override
    public void update(Photo photo, long albumId) {
        executeWithSession(session -> {
            IPhotoDAO photoMapper = session.getMapper(IPhotoDAO.class);
            photoMapper.update(photo, albumId);
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IPhotoDAO photoMapper = session.getMapper(IPhotoDAO.class);
            photoMapper.delete(id);
            return null;
        });
    }
}
