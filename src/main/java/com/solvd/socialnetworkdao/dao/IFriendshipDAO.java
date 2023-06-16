package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Friendship;

import java.util.List;

public interface IFriendshipDAO extends IDAO<Friendship>{
    void insert(Friendship friendship, long requesterProfileId, long requestedProfileId);
    void update(Friendship friendship, long requesterProfileId, long requestedProfileId);
    List<Friendship> getFriendshipsBySenderProfileId(long id);
    List<Friendship> getFriendshipsByReceiverProfileId(long id);
}
