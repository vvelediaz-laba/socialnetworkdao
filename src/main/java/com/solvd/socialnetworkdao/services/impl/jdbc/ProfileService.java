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
    private final ICommentDAO commentDAO = new CommentDAO();
    private final ICommentService commentService = new CommentService();
    private final IFriendshipDAO friendshipDAO = new FriendshipDAO();
    private final IFriendshipService friendshipService = new FriendshipService();
    private final IPostDAO postDAO = new PostDAO();
    private final IPostService postService = new PostService();
    private final IProfileTagDAO profileTagDAO = new ProfileTagDAO();
    private final IProfileTagService profileTagService = new ProfileTagService();
    private final ILikeDAO likeDAO = new LikeDAO();
    private final ILikeService likeService = new LikeService();
    private final IGroupMembershipDAO groupMembershipDAO = new GroupMembershipDAO();
    private final IGroupMembershipService groupMembershipService = new GroupMembershipService();
    private final IMessageDAO messageDAO = new MessageDAO();
    private final IMessageService messageService = new MessageService();

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

        List<Comment> comments = commentDAO.getCommentsByProfileId(profile.getId());
        for(Comment comment : comments){
            comment = commentService.getById(comment.getId());
        }

        List<Friendship> friendships = friendshipDAO.getFriendshipsByProfileId(profile.getId());
        for(Friendship friendship : friendships){
            friendship = friendshipService.getById(friendship.getId());
        }

        List<Post> posts = postDAO.getPostsByProfileId(profile.getId());
        for(Post post : posts){
            post = postService.getById(post.getId());
        }

        List<ProfileTag> profileTags = profileTagDAO.getProfileTagsByProfileId(profile.getId());
        for(ProfileTag profileTag : profileTags){
            profileTag = profileTagService.getById(profileTag.getId());
        }

        List<Like> likes = likeDAO.getLikesByProfileId(profile.getId());
        for(Like like : likes){
            like = likeService.getById(like.getId());
        }

        List<GroupMembership> groupMemberships = groupMembershipDAO.getGroupMembershipsByProfileId(profile.getId());
        for(GroupMembership groupMembership : groupMemberships){
            groupMembership = groupMembershipService.getById(groupMembership.getId());
        }

        List<Message> messages = messageDAO.getMessagesByProfileId(profile.getId());
        for(Message message : messages){
            message = messageService.getById(message.getId());
        }

        profile.setPhotoAlbums(photoAlbums);
        profile.setComments(comments);
        profile.setFriendships(friendships);
        profile.setPosts(posts);
        profile.setProfileTags(profileTags);
        profile.setLikes(likes);
        profile.setGroupMemberships(groupMemberships);
        profile.setMessages(messages);
    }
}
