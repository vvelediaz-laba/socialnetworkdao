package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.Group;
import com.solvd.socialnetworkdao.User;
import com.solvd.socialnetworkdao.dao.*;
import com.solvd.socialnetworkdao.services.impl.*;

public class ServiceFactory {
    public static IService<Group> createGroupService(String type) {
        IGroupDAO groupDAO;
        IGroupMembershipDAO groupMembershipDAO;
        switch (type) {
            case "jdbc":
                groupDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.GroupDAO();
                groupMembershipDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.GroupMembershipDAO();
                return new GroupService(groupDAO, groupMembershipDAO);
            case "mybatis":
                groupDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.GroupDAO();
                groupMembershipDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.GroupMembershipDAO();
                return new GroupService(groupDAO, groupMembershipDAO);
            default:
                return null;
        }
    }

    public static IPhotoAlbumService createPhotoAlbumService(String type) {
        IPhotoAlbumDAO photoAlbumDAO;
        IPhotoDAO photoDAO;

        switch (type) {
            case "jdbc":
                photoAlbumDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.PhotoAlbumDAO();
                photoDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.PhotoDAO();
                return new PhotoAlbumService(photoAlbumDAO, photoDAO);
            case "mybatis":
                photoAlbumDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.PhotoAlbumDAO();
                photoDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.PhotoDAO();
                return new PhotoAlbumService(photoAlbumDAO, photoDAO);
            default:
                return null;
        }
    }

    public static PostPhotoService createPostPhotoService(String type) {
        IPostPhotoDAO postPhotoDAO;
        IPhotoDAO photoDAO;
        IPostDAO postDAO;
        IPostService postService = createPostService(type);

        switch (type) {
            case "jdbc":
                photoDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.PhotoDAO();
                postPhotoDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.PostPhotoDAO();
                postDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.PostDAO();
                return new PostPhotoService(postPhotoDAO, photoDAO, postDAO, postService);
            case "mybatis":
                photoDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.PhotoDAO();
                postPhotoDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.PostPhotoDAO();
                postDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.PostDAO();
                return new PostPhotoService(postPhotoDAO, photoDAO, postDAO, postService);
            default:
                return null;
        }
    }

    public static PostService createPostService(String type){
        IPostDAO postDAO;
        ILikeDAO likeDAO;
        ICommentDAO commentDAO;
        IPhotoDAO photoDAO;
        IProfileTagDAO profileTagDAO;

        switch (type){
            case "jdbc":
                postDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.PostDAO();
                likeDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.LikeDAO();
                commentDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.CommentDAO();
                photoDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.PhotoDAO();
                profileTagDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileTagDAO();
                return new PostService(postDAO, likeDAO, commentDAO, photoDAO, profileTagDAO);
            case "mybatis":
                postDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.PostDAO();
                likeDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.LikeDAO();
                commentDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.CommentDAO();
                photoDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.PhotoDAO();
                profileTagDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.ProfileTagDAO();
                return new PostService(postDAO, likeDAO, commentDAO, photoDAO, profileTagDAO);
            default:
                return null;
        }
    }

    public static IProfileService createProfileService(String type){
        IProfileDAO profileDAO;
        IPhotoAlbumDAO photoAlbumDAO;
        IFriendshipDAO friendshipDAO;
        IPostDAO postDAO;
        IProfileTagDAO profileTagDAO;
        IGroupMembershipDAO groupMembershipDAO;
        IMessageDAO messageDAO;
        ILikeDAO likeDAO;
        IPostService postService = createPostService(type);
        IPhotoAlbumService photoAlbumService = createPhotoAlbumService(type);

        switch (type){
            case "jdbc":
                profileDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileDAO();
                photoAlbumDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.PhotoAlbumDAO();
                friendshipDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.FriendshipDAO();
                postDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.PostDAO();
                profileTagDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileTagDAO();
                groupMembershipDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.GroupMembershipDAO();
                messageDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.MessageDAO();
                likeDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.LikeDAO();
                return new ProfileService(profileDAO, photoAlbumDAO, friendshipDAO, postDAO, profileTagDAO,
                        groupMembershipDAO, messageDAO, likeDAO, photoAlbumService, postService);
            case "mybatis":
                profileDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.ProfileDAO();
                photoAlbumDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.PhotoAlbumDAO();
                friendshipDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.FriendshipDAO();
                postDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.PostDAO();
                profileTagDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.ProfileTagDAO();
                groupMembershipDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.GroupMembershipDAO();
                messageDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.MessageDAO();
                likeDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.LikeDAO();
                return new ProfileService(profileDAO, photoAlbumDAO, friendshipDAO, postDAO, profileTagDAO,
                        groupMembershipDAO, messageDAO, likeDAO, photoAlbumService, postService);
            default:
                return null;
        }
    }

    public static IService<User> createUserService(String type){
        IUserDAO userDAO;
        IProfileDAO profileDAO;
        IProfileService profileService = createProfileService(type);

        switch (type){
            case "jdbc":
                userDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.UserDAO();
                profileDAO = new com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileDAO();
                return new UserService(userDAO, profileDAO, profileService);
            case "mybatis":
                userDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.UserDAO();
                profileDAO = new com.solvd.socialnetworkdao.dao.impl.mybatis.ProfileDAO();
                return new UserService(userDAO, profileDAO, profileService);
            default:
                return null;
        }
    }
}
