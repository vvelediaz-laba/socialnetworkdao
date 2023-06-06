package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Comment;
import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.ICommentDAO;
import com.solvd.socialnetworkdao.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO implements ICommentDAO {

    @Override
    public void create(Comment comment) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Comment (id, author_profile_id, commented_post_id, content, date_created) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, comment.getId());
            preparedStatement.setLong(2, comment.getAuthorProfile().getId());
            preparedStatement.setLong(3, comment.getCommentedPost().getId());
            preparedStatement.setString(4, comment.getContent());
            preparedStatement.setDate(5, comment.getDateCreated());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Comment getById(long id) {
        Comment comment = new Comment();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Comment WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comment.setId(resultSet.getLong("id"));
                comment.setContent(resultSet.getString("content"));
                comment.setDateCreated(resultSet.getDate("date_created"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> comments = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Comment");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setContent(resultSet.getString("content"));
                comment.setDateCreated(resultSet.getDate("date_created"));

                comments.add(comment);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return comments;
    }

    @Override
    public void update(Comment comment) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Comment SET author_profile_id=?, commented_post_id=?, content=?, date_created=? WHERE id=?");
            preparedStatement.setLong(1, comment.getAuthorProfile().getId());
            preparedStatement.setLong(2, comment.getCommentedPost().getId());
            preparedStatement.setString(3, comment.getContent());
            preparedStatement.setDate(4, comment.getDateCreated());
            preparedStatement.setLong(5, comment.getId());

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

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Comment WHERE id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Comment> getCommentsByProfileId(long id) {
        List<Comment> comments = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Comment WHERE author_profile_id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setContent(resultSet.getString("content"));
                comment.setDateCreated(resultSet.getDate("date_created"));

                comments.add(comment);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return comments;
    }
}
