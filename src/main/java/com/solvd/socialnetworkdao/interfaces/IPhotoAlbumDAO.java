package com.solvd.socialnetworkdao.interfaces;

import com.solvd.socialnetworkdao.PhotoAlbum;

import java.util.List;

public interface IPhotoAlbumDAO extends IDAO<PhotoAlbum>{
    List<PhotoAlbum> getPhotoAlbumsByProfileId(long id);
}
