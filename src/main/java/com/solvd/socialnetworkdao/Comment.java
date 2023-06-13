package com.solvd.socialnetworkdao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Comment {
    @XmlElement
    private Long id;
    @XmlElement(name = "author_profile")
    private Profile authorProfile;
    @XmlElement(name = "commented_post")
    private Post commentedPost;
    @XmlElement
    private String content;
    @XmlElement(name = "date_created")
    private Date dateCreated;

    public Long getId() {
        return id;
    }

    public Profile getAuthorProfile() {
        return authorProfile;
    }

    public void setAuthorProfile(Profile authorProfile) {
        this.authorProfile = authorProfile;
    }

    public Post getCommentedPost() {
        return commentedPost;
    }

    public void setCommentedPost(Post commentedPost) {
        this.commentedPost = commentedPost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
