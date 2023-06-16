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
public class Post {
    @JsonProperty
    @XmlElement
    private Long id;
    @JsonSerialize(using = DateSerializer.class)
    @XmlElement(name = "date_created")
    private Date dateCreated;
    @JsonProperty
    @XmlElement
    private String content;
    @JsonIgnore
    private List<Like> likes;
    @JsonProperty
    @XmlElementWrapper(name = "comments")
    @XmlElement(name = "comment")
    private List<Comment> comments;
    @JsonIgnore
    private List<ProfileTag> tags;
    @JsonIgnore
    private List<Photo> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ProfileTag> getTags() {
        return tags;
    }

    public void setTags(List<ProfileTag> tags) {
        this.tags = tags;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
