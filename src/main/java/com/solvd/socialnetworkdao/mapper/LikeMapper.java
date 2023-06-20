package com.solvd.socialnetworkdao.mapper;

import com.solvd.socialnetworkdao.Like;

import java.util.List;

public interface LikeMapper {

    Like getLikeById(long id);

    List<Like> getAllLikes();

    void insertLike(Like like, long likedPostId, long likerPostId);

    void updateLike(Like like, long likedPostId, long likerPostId);

    void deleteLike(long id);

}

