package com.solvd.socialnetworkdao.interfaces;

import com.solvd.socialnetworkdao.Like;

import java.util.List;

public interface ILikeDAO extends IDAO<Like>{
    List<Like> getLikesByProfileId(long id);
}
