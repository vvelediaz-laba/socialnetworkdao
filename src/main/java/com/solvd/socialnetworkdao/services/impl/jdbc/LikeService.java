package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.Like;
import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.dao.ILikeDAO;
import com.solvd.socialnetworkdao.dao.IPostDAO;
import com.solvd.socialnetworkdao.dao.IProfileDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.LikeDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.PostDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileDAO;
import com.solvd.socialnetworkdao.services.ILikeService;
import com.solvd.socialnetworkdao.services.IPostService;
import com.solvd.socialnetworkdao.services.IProfileService;

import java.util.List;

public class LikeService implements ILikeService {
    private final ILikeDAO likeDAO = new LikeDAO();
    private final IProfileDAO profileDAO = new ProfileDAO();
    private final IProfileService profileService = new ProfileService();
    private final IPostDAO postDAO = new PostDAO();
    private final IPostService postService = new PostService();

    @Override
    public void insert(Like like) {
        likeDAO.insert(like);
    }

    @Override
    public Like getById(long id) {
        Like like = likeDAO.getById(id);
        setValues(like);
        return like;
    }

    @Override
    public List<Like> getAll() {
        List<Like> likes = likeDAO.getAll();
        for(Like like : likes){
            setValues(like);
        }
        return likes;
    }

    @Override
    public void update(Like like) {
        likeDAO.update(like);
    }

    @Override
    public void delete(long id) {
        likeDAO.delete(id);
    }

    private void setValues(Like like){
        Profile likerProfile = profileDAO.getByLikeId(like.getId());
        likerProfile = profileService.getById(likerProfile.getId());
        
        Post likedPost = postDAO.getByLikeId(like.getId());
        likedPost = postService.getById(likedPost.getId());

        like.setLikerProfile(likerProfile);
        like.setLikedPost(likedPost);
    }
}
