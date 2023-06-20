package com.solvd.socialnetworkdao.mapper;

import com.solvd.socialnetworkdao.Friendship;

import java.util.List;

public interface FriendshipMapper {

    Friendship getFriendshipById(long id);

    List<Friendship> getAllFriendships();

    void insertFriendship(Friendship friendship, long requesterProfileId, long requestedProfileId);

    void updateFriendship(Friendship friendship, long requesterProfileId, long requestedProfileId);

    void deleteFriendship(long id);

}
