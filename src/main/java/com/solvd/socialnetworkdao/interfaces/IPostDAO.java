package com.solvd.socialnetworkdao.interfaces;

import com.solvd.socialnetworkdao.Post;

import java.util.List;

public interface IPostDAO extends IDAO<Post>{
    List<Post> getPostsByProfileId(long id);

    Post getByCommentId(long id);
}
