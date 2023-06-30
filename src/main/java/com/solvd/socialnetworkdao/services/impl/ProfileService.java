package com.solvd.socialnetworkdao.services.impl;

import com.solvd.socialnetworkdao.*;
import com.solvd.socialnetworkdao.dao.*;
import com.solvd.socialnetworkdao.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProfileService implements IProfileService {
    private static final Logger logger = LogManager.getLogger(ProfileService.class);
    private IProfileDAO profileDAO;
    private IPhotoAlbumDAO photoAlbumDAO;
    private IFriendshipDAO friendshipDAO;
    private IPostDAO postDAO;
    private IProfileTagDAO profileTagDAO;
    private IGroupMembershipDAO groupMembershipDAO;
    private IMessageDAO messageDAO;
    private ILikeDAO likeDAO;
    private IPostService postService;
    private IPhotoAlbumService photoAlbumService;

    public ProfileService(IProfileDAO profileDAO, IPhotoAlbumDAO photoAlbumDAO, IFriendshipDAO friendshipDAO,
                          IPostDAO postDAO, IProfileTagDAO profileTagDAO, IGroupMembershipDAO groupMembershipDAO,
                          IMessageDAO messageDAO, ILikeDAO likeDAO, IPhotoAlbumService photoAlbumService, IPostService postService) {
        this.profileDAO = profileDAO;
        this.photoAlbumDAO = photoAlbumDAO;
        this.friendshipDAO = friendshipDAO;
        this.postDAO = postDAO;
        this.profileTagDAO = profileTagDAO;
        this.groupMembershipDAO = groupMembershipDAO;
        this.messageDAO = messageDAO;
        this.likeDAO = likeDAO;
        this.photoAlbumService = photoAlbumService;
        this.postService = postService;
    }

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

    public IProfileDAO getProfileDAO() {
        return profileDAO;
    }

    public void setProfileDAO(IProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    public IPhotoAlbumDAO getPhotoAlbumDAO() {
        return photoAlbumDAO;
    }

    public void setPhotoAlbumDAO(IPhotoAlbumDAO photoAlbumDAO) {
        this.photoAlbumDAO = photoAlbumDAO;
    }

    public IPhotoAlbumService getPhotoAlbumService() {
        return photoAlbumService;
    }

    public void setPhotoAlbumService(IPhotoAlbumService photoAlbumService) {
        this.photoAlbumService = photoAlbumService;
    }

    public IFriendshipDAO getFriendshipDAO() {
        return friendshipDAO;
    }

    public void setFriendshipDAO(IFriendshipDAO friendshipDAO) {
        this.friendshipDAO = friendshipDAO;
    }

    public IPostDAO getPostDAO() {
        return postDAO;
    }

    public void setPostDAO(IPostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public IPostService getPostService() {
        return postService;
    }

    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    public IProfileTagDAO getProfileTagDAO() {
        return profileTagDAO;
    }

    public void setProfileTagDAO(IProfileTagDAO profileTagDAO) {
        this.profileTagDAO = profileTagDAO;
    }

    public IGroupMembershipDAO getGroupMembershipDAO() {
        return groupMembershipDAO;
    }

    public void setGroupMembershipDAO(IGroupMembershipDAO groupMembershipDAO) {
        this.groupMembershipDAO = groupMembershipDAO;
    }

    public IMessageDAO getMessageDAO() {
        return messageDAO;
    }

    public void setMessageDAO(IMessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public ILikeDAO getLikeDAO() {
        return likeDAO;
    }

    public void setLikeDAO(ILikeDAO likeDAO) {
        this.likeDAO = likeDAO;
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
            try {
                post = postService.getById(post.getId());
            } catch (NullPointerException e) {
                logger.error(e);
            }
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
