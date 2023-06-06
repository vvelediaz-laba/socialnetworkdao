package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.Friendship;
import com.solvd.socialnetworkdao.dao.FriendshipDAO;
import com.solvd.socialnetworkdao.dao.ProfileDAO;

public class FriendshipService {
    public Friendship getFriendshipById(long id){
        Friendship friendship = new FriendshipDAO().getById(id);
        friendship.setRequesterProfile(new ProfileDAO().getByFriendshipId(id));
        friendship.setRequestedProfile(new ProfileDAO().getRequestedByFriendshipId(id));
        return friendship;
    }
}
