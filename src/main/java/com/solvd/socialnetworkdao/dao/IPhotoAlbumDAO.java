package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.PhotoAlbum;

import java.util.List;

public interface IPhotoAlbumDAO extends IDAO<PhotoAlbum>{
    void insert(PhotoAlbum photoAlbum, long profileId);
    void update(PhotoAlbum photoAlbum, long profileId);
    List<PhotoAlbum> getPhotoAlbumsByProfileId(long id);
    PhotoAlbum getByPhotoId(long id);
}
