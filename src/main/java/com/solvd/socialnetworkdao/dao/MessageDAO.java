package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Message;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.interfaces.IDAO;
import com.solvd.socialnetworkdao.interfaces.IMessageDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO implements IMessageDAO {

    @Override
    public void create(Message message) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Message (id, content, sender_profile_id, date_sent, receriver_profile_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, message.getId());
            preparedStatement.setString(2, message.getContent());
            preparedStatement.setLong(3, message.getSender().getId());
            preparedStatement.setDate(4, message.getDateSent());
            preparedStatement.setLong(5, message.getReceiver().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Message getById(long id) {
        Message message = new Message();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Message WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                message.setId(resultSet.getLong("id"));
                message.setContent(resultSet.getString("content"));
                message.setDateSent(resultSet.getDate("date_sent"));
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return message;
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Message");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getLong("id"));
                message.setContent(resultSet.getString("content"));
                message.setDateSent(resultSet.getDate("date_sent"));

                messages.add(message);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return messages;
    }

    @Override
    public void update(Message message) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE Message SET content=?, sender_profile_id=?, date_sent=?, receiver_profile_id=? WHERE id=?");
            preparedStatement.setString(1, message.getContent());
            preparedStatement.setLong(2, message.getSender().getId());
            preparedStatement.setDate(3, message.getDateSent());
            preparedStatement.setLong(4, message.getReceiver().getId());
            preparedStatement.setLong(5, message.getId());

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

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Message WHERE id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Message> getMessagesById(long id) {
        List<Message> messages = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Message WHERE sender_profile_id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getLong("id"));
                message.setContent(resultSet.getString("content"));
                message.setDateSent(resultSet.getDate("date_sent"));

                messages.add(message);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return messages;
    }
}
