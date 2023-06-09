package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Post;

import java.util.List;

public interface IPostDAO extends IDAO<Post>{
    List<Post> getPostsByProfileId(long id);
    Post getByCommentId(long id);
    Post getByLikeId(long id);
    Post getByPostPhotoId(long id);
    Post getByProfileTagId(long id);
}
