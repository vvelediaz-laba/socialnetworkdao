package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IDAO;
import com.solvd.socialnetworkdao.interfaces.IPostDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements IPostDAO{

    @Override
    public void create(Post post) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Post (id, poster_profile_id, date_created, content) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, post.getId());
            preparedStatement.setLong(2, post.getPosterProfile().getId());
            preparedStatement.setDate(3, post.getDateCreated());
            preparedStatement.setString(4, post.getContent());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Post getById(long id) {
        Post post = new Post();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Post WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                post.setId(resultSet.getLong("id"));
                post.setDateCreated(resultSet.getDate("date_created"));
                post.setContent(resultSet.getString("content"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Post");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setDateCreated(resultSet.getDate("date_created"));
                post.setContent(resultSet.getString("content"));

                posts.add(post);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return posts;
    }


    @Override
    public void update(Post post) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Post SET poster_profile_id=?, date_created=?, content=? WHERE id=?");
            preparedStatement.setLong(1, post.getPosterProfile().getId());
            preparedStatement.setDate(2, post.getDateCreated());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setLong(4, post.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(long id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Post WHERE id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Post> getPostsByProfileId(long id) {
        List<Post> posts = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Post WHERE poster_profile_id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setDateCreated(resultSet.getDate("date_created"));
                post.setContent(resultSet.getString("content"));

                posts.add(post);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return posts;
    }

    @Override
    public Post getByCommentId(long id) {
        Post post = new Post();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Post.* FROM Post, Comment WHERE Post.id = Comment.commented_post_id AND Commment.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                post.setId(resultSet.getLong("id"));
                post.setDateCreated(resultSet.getDate("date_created"));
                post.setContent(resultSet.getString("content"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return post;
    }
}
