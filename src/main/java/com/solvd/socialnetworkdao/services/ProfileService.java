package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.*;
import com.solvd.socialnetworkdao.dao.*;

public class ProfileService {
    public Profile getProfile(long id){
        Profile profile = new ProfileDAO().getById(id);
        profile.setPhotoAlbums(new PhotoAlbumDAO().getPhotoAlbumsByProfileId(id));
        profile.setComments(new CommentDAO().getCommentsByProfileId(id));
        profile.setPosts(new PostDAO().getPostsByProfileId(id));
        profile.setTags(new ProfileTagDAO().getProfileTagsByProfileId(id));
        profile.setLikes(new LikeDAO().getLikesByProfileId(id));
        profile.setGroupMemberships(new GroupMembershipDAO().getGroupMembershipsByProfileId(id));
        profile.setMessages(new MessageDAO().getMessagesById(id));
        return profile;
    }
}
