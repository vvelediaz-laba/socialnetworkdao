package com.solvd.socialnetworkdao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.socialnetworkdao.parser.json.DateSerializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.sql.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Profile {
    @JsonProperty
    @XmlElement
    private Long id;
    @JsonProperty
    @XmlElement(name = "full_name")
    private String fullName;
    @JsonSerialize(using = DateSerializer.class)
    @XmlElement(name = "date_of_birth")
    private Date dateOfBirth;
    @JsonProperty
    @XmlElement
    private String gender;
    @JsonProperty
    @XmlElement
    private String bio;
    @JsonIgnore
    private List<PhotoAlbum> photoAlbums;
    @JsonIgnore
    private List<Friendship> outgoingFriendships;
    @JsonIgnore
    private List<Friendship> incomingFriendships;
    @JsonProperty("posts")
    @XmlElementWrapper(name = "posts")
    @XmlElement(name = "post")
    private List<Post> posts;
    @JsonIgnore
    private List<ProfileTag> profileTags;
    @JsonIgnore
    private List<GroupMembership> groupMemberships;
    @JsonProperty("messages")
    @XmlElementWrapper(name = "messages")
    @XmlElement(name = "message")
    private List<Message> outgoingMessages;
    @JsonIgnore
    private List<Message> incomingMessages;
    @JsonIgnore
    private List<Like> likes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<PhotoAlbum> getPhotoAlbums() {
        return photoAlbums;
    }

    public void setPhotoAlbums(List<PhotoAlbum> photoAlbums) {
        this.photoAlbums = photoAlbums;
    }

    public List<Friendship> getOutgoingFriendships() {
        return outgoingFriendships;
    }

    public void setOutgoingFriendships(List<Friendship> outgoingFriendships) {
        this.outgoingFriendships = outgoingFriendships;
    }

    public List<Friendship> getIncomingFriendships() {
        return incomingFriendships;
    }

    public void setIncomingFriendships(List<Friendship> incomingFriendships) {
        this.incomingFriendships = incomingFriendships;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<ProfileTag> getProfileTags() {
        return profileTags;
    }

    public void setProfileTags(List<ProfileTag> tags) {
        this.profileTags = tags;
    }

    public List<GroupMembership> getGroupMemberships() {
        return groupMemberships;
    }

    public void setGroupMemberships(List<GroupMembership> groupMemberships) {
        this.groupMemberships = groupMemberships;
    }

    public List<Message> getOutgoingMessages() {
        return outgoingMessages;
    }

    public void setOutgoingMessages(List<Message> outgoingMessages) {
        this.outgoingMessages = outgoingMessages;
    }

    public List<Message> getIncomingMessages() {
        return incomingMessages;
    }

    public void setIncomingMessages(List<Message> incomingMessages) {
        this.incomingMessages = incomingMessages;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", bio='" + bio + '\'' +
                ", photoAlbums=" + photoAlbums +
                ", outgoingFriendships=" + outgoingFriendships +
                ", incomingFriendships=" + incomingFriendships +
                ", posts=" + posts +
                ", tags=" + profileTags +
                ", groupMemberships=" + groupMemberships +
                ", outgoingMessages=" + outgoingMessages +
                ", incomingMessages=" + incomingMessages +
                ", likes=" + likes +
                '}';
    }
}
