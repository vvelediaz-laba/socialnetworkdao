package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.PhotoAlbum;

public interface IPhotoAlbumService extends IService<PhotoAlbum> {
    void insert(PhotoAlbum photoAlbum, long profileId);
    void update(PhotoAlbum photoAlbum, long profileId);
}
