package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Friendship;

import java.util.List;

public interface IFriendshipDAO extends IDAO<Friendship>{
    List<Friendship> getFriendshipsByProfileId(long id);
}
