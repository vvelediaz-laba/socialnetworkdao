package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Like;

import java.util.List;

public interface ILikeDAO extends IDAO<Like>{
    void insert(Like like, long postId, long likerId);
    void update(Like like, long postId, long likerId);
    List<Like> getLikesByProfileId(long id);
    List<Like> getLikesByPostId(long id);
}
