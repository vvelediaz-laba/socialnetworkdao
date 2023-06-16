package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.PostPhoto;

public interface IPostPhotoService extends IService<PostPhoto>{
    void insert(PostPhoto postPhoto);
    void update(PostPhoto postPhoto);
}
