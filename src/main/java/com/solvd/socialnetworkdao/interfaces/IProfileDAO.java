package com.solvd.socialnetworkdao.interfaces;

import com.solvd.socialnetworkdao.Profile;

public interface IProfileDAO extends IDAO<Profile> {
    Profile getByUserId(long id);
    Profile getByCommentId(long id);
    Profile getByFriendshipId(long id);
    Profile getRequestedByFriendshipId(long id);
}
