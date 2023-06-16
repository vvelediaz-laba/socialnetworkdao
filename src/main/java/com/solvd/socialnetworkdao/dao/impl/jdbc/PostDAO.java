package com.solvd.socialnetworkdao.dao.impl.jdbc;

import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.dao.IPostDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends DAO implements IPostDAO {

    @Override
    public void insert(Post post, long profileId) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Post (id, poster_profile_id, date_created, content) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, post.getId());
            preparedStatement.setLong(2, profileId);
            preparedStatement.setDate(3, post.getDateCreated());
            preparedStatement.setString(4, post.getContent());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Post getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Post WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPost(resultSet);
        });
    }

    @Override
    public List<Post> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Post");
            return createPostList(preparedStatement);
        });
    }


    @Override
    public void update(Post post, long profileId) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Post SET poster_profile_id=?, date_created=?, content=? WHERE id=?");
            preparedStatement.setLong(1, profileId);
            preparedStatement.setDate(2, post.getDateCreated());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setLong(4, post.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Post WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public List<Post> getPostsByProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Post WHERE poster_profile_id=?");
            preparedStatement.setLong(1, id);
            return createPostList(preparedStatement);
        });
    }

    @Override
    public Post getByCommentId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Post.* FROM Post, Comment WHERE Post.id = Comment.commented_post_id AND Comment.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPost(resultSet);
        });
    }

    @Override
    public Post getByLikeId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Post.* FROM Post, `Like` WHERE Post.id = `Like`.liked_post_id AND `Like`.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPost(resultSet);
        });
    }

    @Override
    public Post getByPostPhotoId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Post.* FROM Post JOIN Post_Photo ON Post.id = Post_Photo.post_id WHERE Post_Photo.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPost(resultSet);
        });
    }

    @Override
    public Post getByProfileTagId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Post.* FROM Post, Profile_Tag WHERE Post.id = Profile_Tag.tagged_post_id AND Profile_Tag.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createPost(resultSet);
        });
    }

    private Post createPost(ResultSet resultSet) throws SQLException {
        Post post = new Post();
        if(resultSet.next()){
            post.setId(resultSet.getLong("id"));
            post.setDateCreated(resultSet.getDate("date_created"));
            post.setContent(resultSet.getString("content"));
        }
        return post;
    }

    private List<Post> createPostList(PreparedStatement preparedStatement) throws SQLException{
        List<Post> posts = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Post post = new Post();
            post.setId(resultSet.getLong("id"));
            post.setDateCreated(resultSet.getDate("date_created"));
            post.setContent(resultSet.getString("content"));
            posts.add(post);
        }
        return posts;
    }
}
