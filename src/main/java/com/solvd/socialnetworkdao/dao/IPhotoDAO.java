package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Photo;
import com.solvd.socialnetworkdao.Post;

import java.util.List;

public interface IPhotoDAO extends IDAO<Photo> {
    Photo getByPostPhotoId(long id);
    List<Post> getAssociatedPosts(long id);
    List<Photo> getPhotosByPhotoAlbumId(long id);
    List<Photo> getPhotosByPostId(long id);
}
