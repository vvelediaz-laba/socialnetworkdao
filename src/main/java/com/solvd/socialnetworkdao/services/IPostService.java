package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.PostPhoto;

public interface IPostService extends IService<Post>{
    void insert(Post post, long profileId);
    void update(Post post, long profileId);
}
