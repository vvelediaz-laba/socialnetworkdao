package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Like;

import java.util.List;

public interface ILikeDAO extends IDAO<Like>{
    List<Like> getLikesByProfileId(long id);
    List<Like> getLikesByPostId(long id);
}
