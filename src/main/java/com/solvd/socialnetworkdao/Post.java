package com.solvd.socialnetworkdao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.sql.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Post {
    @XmlElement
    private Long id;
    @XmlElement(name = "poster_profile")
    private Profile posterProfile;
    @XmlElement(name = "date_created")
    private Date dateCreated;
    @XmlElement
    private String content;
    private List<Like> likes;
    @XmlElementWrapper(name = "comments")
    @XmlElement(name = "comment")
    private List<Comment> comments;
    private List<Profile> taggedProfiles;
    private List<Photo> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getPosterProfile() {
        return posterProfile;
    }

    public void setPosterProfile(Profile posterProfile) {
        this.posterProfile = posterProfile;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Profile> getTaggedProfiles() {
        return taggedProfiles;
    }

    public void setTaggedProfiles(List<Profile> taggedProfiles) {
        this.taggedProfiles = taggedProfiles;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
