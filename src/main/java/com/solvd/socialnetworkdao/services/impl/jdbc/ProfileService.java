package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.*;
import com.solvd.socialnetworkdao.dao.*;
import com.solvd.socialnetworkdao.dao.impl.jdbc.*;
import com.solvd.socialnetworkdao.services.*;

import java.util.List;

public class ProfileService implements IProfileService {
    private final IProfileDAO profileDAO = new ProfileDAO();
    private final IPhotoAlbumDAO photoAlbumDAO = new PhotoAlbumDAO();
    private final IPhotoAlbumService photoAlbumService = new PhotoAlbumService();
    private final IFriendshipDAO friendshipDAO = new FriendshipDAO();
    private final IPostDAO postDAO = new PostDAO();
    private final IPostService postService = new PostService();
    private final IProfileTagDAO profileTagDAO = new ProfileTagDAO();
    private final IGroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();
    private final IMessageDAO messageDAO = new MessageDAO();
    private final ILikeDAO likeDAO = new LikeDAO();

    @Override
    public void insert(Profile profile) {
        profileDAO.insert(profile);
    }

    @Override
    public Profile getById(long id){
        Profile profile = profileDAO.getById(id);
        setValues(profile);
        return profile;
    }

    @Override
    public List<Profile> getAll() {
        List<Profile> profiles = profileDAO.getAll();
        for(Profile profile : profiles){
            setValues(profile);
        }
        return profiles;
    }

    @Override
    public void update(Profile profile) {
        profileDAO.update(profile);
    }

    @Override
    public void delete(long id) {
        profileDAO.delete(id);
    }

    private void setValues(Profile profile) {
        List<PhotoAlbum> photoAlbums = photoAlbumDAO.getPhotoAlbumsByProfileId(profile.getId());
        for(PhotoAlbum photoAlbum : photoAlbums){
            photoAlbum = photoAlbumService.getById(photoAlbum.getId());
        }

        List<Friendship> outgoingFriendships = friendshipDAO.getFriendshipsBySenderProfileId(profile.getId());

        List<Friendship> incomingFriendships = friendshipDAO.getFriendshipsByReceiverProfileId(profile.getId());

        List<Post> posts = postDAO.getPostsByProfileId(profile.getId());
        for(Post post : posts){
            post = postService.getById(post.getId());
        }

        List<ProfileTag> profileTags = profileTagDAO.getProfileTagsByProfileId(profile.getId());

        List<GroupMembership> groupMemberships = groupMembershipDAO.getGroupMembershipsByProfileId(profile.getId());

        List<Message> outgoingMessages = messageDAO.getMessagesBySenderProfileId(profile.getId());

        List<Message> incomingMessage = messageDAO.getMessagesByReceiverProfileId(profile.getId());

        List<Like> likes = likeDAO.getLikesByProfileId(profile.getId());

        profile.setPhotoAlbums(photoAlbums);
        profile.setOutgoingFriendships(outgoingFriendships);
        profile.setIncomingFriendships(incomingFriendships);
        profile.setPosts(posts);
        profile.setProfileTags(profileTags);
        profile.setGroupMemberships(groupMemberships);
        profile.setOutgoingMessages(outgoingMessages);
        profile.setIncomingMessages(incomingMessage);
        profile.setLikes(likes);
    }
}
