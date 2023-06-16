package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.PostPhoto;

public interface IPostPhotoDAO extends IDAO<PostPhoto> {
    void insert(PostPhoto postPhoto);
    void update(PostPhoto postPhoto);
}
