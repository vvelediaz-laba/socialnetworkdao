package com.solvd.socialnetworkdao.mapper;

import com.solvd.socialnetworkdao.Post;

import java.util.List;

public interface PostMapper {

    Post getPostById(long id);

    List<Post> getAllPosts();

    void insertPost(Post post, long posterProfileId);

    void updatePost(Post post, long posterProfileId);

    void deletePost(long id);

}
