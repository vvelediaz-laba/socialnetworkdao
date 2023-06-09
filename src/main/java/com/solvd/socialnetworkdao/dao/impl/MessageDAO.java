package com.solvd.socialnetworkdao.dao.impl;

import com.solvd.socialnetworkdao.Message;
import com.solvd.socialnetworkdao.dao.IMessageDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO extends DAO implements IMessageDAO {

    @Override
    public void insert(Message message) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Message (id, content, sender_profile_id, date_sent, receiver_profile_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, message.getId());
            preparedStatement.setString(2, message.getContent());
            preparedStatement.setLong(3, message.getSender().getId());
            preparedStatement.setDate(4, message.getDateSent());
            preparedStatement.setLong(5, message.getReceiver().getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public Message getById(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Message WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createMessage(resultSet);
        });
    }

    @Override
    public List<Message> getAll() {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Message");
            return createMessageList(preparedStatement);
        });
    }

    @Override
    public void update(Message message) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE Message SET content=?, sender_profile_id=?, date_sent=?, receiver_profile_id=? WHERE id=?");
            preparedStatement.setString(1, message.getContent());
            preparedStatement.setLong(2, message.getSender().getId());
            preparedStatement.setDate(3, message.getDateSent());
            preparedStatement.setLong(4, message.getReceiver().getId());
            preparedStatement.setLong(5, message.getId());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Message WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    @Override
    public List<Message> getMessagesByProfileId(long id) {
        return executeWithConnection(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Message WHERE sender_profile_id=?");
            preparedStatement.setLong(1, id);
            return createMessageList(preparedStatement);
        });
    }

    private Message createMessage(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        if(resultSet.next()){
            message.setId(resultSet.getLong("id"));
            message.setContent(resultSet.getString("content"));
            message.setDateSent(resultSet.getDate("date_sent"));
        }
        return message;
    }

    private List<Message> createMessageList(PreparedStatement preparedStatement) throws SQLException{
        List<Message> messages = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Message message = createMessage(resultSet);
            messages.add(message);
        }
        return messages;
    }
}
