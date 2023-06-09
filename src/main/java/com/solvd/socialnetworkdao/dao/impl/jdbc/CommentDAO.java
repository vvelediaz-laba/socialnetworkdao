package com.solvd.socialnetworkdao.dao.impl.jdbc;

import com.solvd.socialnetworkdao.Comment;
import com.solvd.socialnetworkdao.dao.ICommentDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends DAO implements ICommentDAO {

    @Override
    public void insert(Comment comment) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Comment (id, author_profile_id, commented_post_id, content, date_created) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, comment.getId());
            preparedStatement.setLong(2, comment.getAuthorProfile().getId());
            preparedStatement.setLong(3, comment.getCommentedPost().getId());
            preparedStatement.setString(4, comment.getContent());
            preparedStatement.setDate(5, comment.getDateCreated());

            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Comment getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Comment WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createComment(resultSet);
        });
    }

    @Override
    public List<Comment> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Comment");
            return createCommentList(preparedStatement);
        });
    }

    @Override
    public void update(Comment comment) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Comment SET author_profile_id=?, commented_post_id=?, content=?, date_created=? WHERE id=?");
            preparedStatement.setLong(1, comment.getAuthorProfile().getId());
            preparedStatement.setLong(2, comment.getCommentedPost().getId());
            preparedStatement.setString(3, comment.getContent());
            preparedStatement.setDate(4, comment.getDateCreated());
            preparedStatement.setLong(5, comment.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Comment WHERE id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public List<Comment> getCommentsByProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Comment WHERE author_profile_id=?");
            preparedStatement.setLong(1, id);
            return createCommentList(preparedStatement);
        });
    }

    @Override
    public List<Comment> getCommentsByPostId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Comment WHERE commented_post_id=?");
            preparedStatement.setLong(1, id);
            return createCommentList(preparedStatement);
        });
    }

    // Methods to reduce duplication
    private Comment createComment(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        if(resultSet.next()) {
            comment.setId(resultSet.getLong("id"));
            comment.setContent(resultSet.getString("content"));
            comment.setDateCreated(resultSet.getDate("date_created"));
        }
        return comment;
    }

    private List<Comment> createCommentList(PreparedStatement preparedStatement) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Comment comment = createComment(resultSet);
            comments.add(comment);
        }
        return comments;
    }
}
