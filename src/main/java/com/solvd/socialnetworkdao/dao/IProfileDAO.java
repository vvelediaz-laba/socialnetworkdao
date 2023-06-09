package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Profile;

public interface IProfileDAO extends IDAO<Profile> {
    Profile getByUserId(long id);
    Profile getByCommentId(long id);
    Profile getByFriendshipId(long id);
    Profile getRequestedByFriendshipId(long id);
    Profile getByGroupMembershipId(long id);
    Profile getByLikeId(long id);
    Profile getSenderByMessageId(long id);
    Profile getReceiverByMessageId(long id);
    Profile getByPhotoAlbumId(long id);
    Profile getByPostId(long id);
    Profile getByProfileTagId(long id);
}
