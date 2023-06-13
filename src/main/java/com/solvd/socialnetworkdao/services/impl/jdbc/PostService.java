package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.*;
import com.solvd.socialnetworkdao.dao.*;
import com.solvd.socialnetworkdao.dao.impl.jdbc.*;
import com.solvd.socialnetworkdao.services.*;

import java.util.List;

public class PostService implements IPostService {
    private final IProfileDAO profileDAO = new ProfileDAO();
    private final IProfileService profileService = new ProfileService();
    private final IPostDAO postDAO = new PostDAO();
    private final ILikeDAO likeDAO =  new LikeDAO();
    private final ILikeService likeService = new LikeService();
    private final ICommentDAO commentDAO = new CommentDAO();
    private final ICommentService commentService = new CommentService();
    private final IPhotoDAO photoDAO = new PhotoDAO();
    private final IPhotoService photoService = new PhotoService();

    @Override
    public void insert(Post post) {
        postDAO.insert(post);
    }

    @Override
    public Post getById(long id) {
        Post post = postDAO.getById(id);
        setValues(post);
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = postDAO.getAll();
        for(Post post : posts){
            setValues(post);
        }
        return posts;
    }

    @Override
    public void update(Post post) {
        postDAO.update(post);
    }

    @Override
    public void delete(long id) {
        postDAO.delete(id);
    }

    public void setValues(Post post) {
        Profile profile = profileDAO.getByPostId(post.getId());
        profile = profileService.getById(profile.getId());
        post.setPosterProfile(profile);
        
        List<Like> likes = likeDAO.getLikesByPostId(post.getId());
        for(Like like : likes){
            like = likeService.getById(like.getId());
        }

        List<Comment> comments = commentDAO.getCommentsByPostId(post.getId());
        for(Comment comment : comments){
            comment = commentService.getById(comment.getId());
        }

        List<Photo> photos = photoDAO.getPhotosByPostId(post.getId());
        for(Photo photo : photos){
            photo = photoService.getById(photo.getId());
        }

        post.setLikes(likes);
        post.setComments(comments);
        post.setPhotos(photos);
    }
}
