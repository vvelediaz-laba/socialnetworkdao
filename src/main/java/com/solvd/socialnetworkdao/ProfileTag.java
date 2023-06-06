package com.solvd.socialnetworkdao;

public class ProfileTag {
    private Long id;
    private Post taggedPost;
    private Profile taggedProfile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getTaggedPost() {
        return taggedPost;
    }

    public void setTaggedPost(Post taggedPost) {
        this.taggedPost = taggedPost;
    }

    public Profile getTaggedProfile() {
        return taggedProfile;
    }

    public void setTaggedProfile(Profile taggedProfile) {
        this.taggedProfile = taggedProfile;
    }
}
