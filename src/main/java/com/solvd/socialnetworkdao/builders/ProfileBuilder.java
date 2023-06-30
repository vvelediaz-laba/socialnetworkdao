package com.solvd.socialnetworkdao.builders;

import com.solvd.socialnetworkdao.*;

import java.sql.Date;
import java.util.List;

public class ProfileBuilder {
        private Long id;
        private String fullName;
        private Date dateOfBirth;
        private String gender;
        private String bio;
        private List<PhotoAlbum> photoAlbums;
        private List<Friendship> outgoingFriendships;
        private List<Friendship> incomingFriendships;
        private List<Post> posts;
        private List<ProfileTag> profileTags;
        private List<GroupMembership> groupMemberships;
        private List<Message> outgoingMessages;
        private List<Message> incomingMessages;
        private List<Like> likes;

        public ProfileBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ProfileBuilder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public ProfileBuilder setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public ProfileBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public ProfileBuilder setBio(String bio) {
            this.bio = bio;
            return this;
        }

        public ProfileBuilder setPhotoAlbums(List<PhotoAlbum> photoAlbums) {
            this.photoAlbums = photoAlbums;
            return this;
        }

        public ProfileBuilder setOutgoingFriendships(List<Friendship> outgoingFriendships) {
            this.outgoingFriendships = outgoingFriendships;
            return this;
        }

        public ProfileBuilder setIncomingFriendships(List<Friendship> incomingFriendships) {
            this.incomingFriendships = incomingFriendships;
            return this;
        }

        public ProfileBuilder setPosts(List<Post> posts) {
            this.posts = posts;
            return this;
        }

        public ProfileBuilder setProfileTags(List<ProfileTag> profileTags) {
            this.profileTags = profileTags;
            return this;
        }

        public ProfileBuilder setGroupMemberships(List<GroupMembership> groupMemberships) {
            this.groupMemberships = groupMemberships;
            return this;
        }

        public ProfileBuilder setOutgoingMessages(List<Message> outgoingMessages) {
            this.outgoingMessages = outgoingMessages;
            return this;
        }

        public ProfileBuilder setIncomingMessages(List<Message> incomingMessages) {
            this.incomingMessages = incomingMessages;
            return this;
        }

        public ProfileBuilder setLikes(List<Like> likes) {
            this.likes = likes;
            return this;
        }

        public Profile build() {
            Profile profile = new Profile();
            profile.setId(id);
            profile.setFullName(fullName);
            profile.setDateOfBirth(dateOfBirth);
            profile.setGender(gender);
            profile.setBio(bio);
            profile.setPhotoAlbums(photoAlbums);
            profile.setOutgoingFriendships(outgoingFriendships);
            profile.setIncomingFriendships(incomingFriendships);
            profile.setPosts(posts);
            profile.setProfileTags(profileTags);
            profile.setGroupMemberships(groupMemberships);
            profile.setOutgoingMessages(outgoingMessages);
            profile.setIncomingMessages(incomingMessages);
            profile.setLikes(likes);
            return profile;
        }
}
