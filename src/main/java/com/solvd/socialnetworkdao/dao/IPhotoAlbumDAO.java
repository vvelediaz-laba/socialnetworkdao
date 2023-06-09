package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Photo;
import com.solvd.socialnetworkdao.PhotoAlbum;

import java.util.List;

public interface IPhotoAlbumDAO extends IDAO<PhotoAlbum>{
    List<PhotoAlbum> getPhotoAlbumsByProfileId(long id);
    PhotoAlbum getByPhotoId(long id);
}
