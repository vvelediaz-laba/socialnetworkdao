package com.solvd.socialnetworkdao;

public class Like {
    private long id;
    private Post likedPost;
    private Profile likerProfile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Post getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(Post likedPost) {
        this.likedPost = likedPost;
    }

    public Profile getLikerProfile() {
        return likerProfile;
    }

    public void setLikerProfile(Profile likerProfile) {
        this.likerProfile = likerProfile;
    }
}
