package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.Message;
import com.solvd.socialnetworkdao.dao.IMessageDAO;
import java.util.List;

public class MessageDAO extends MyBatisDAO implements IMessageDAO {

    @Override
    public Message getById(long id) {
        return executeWithSession(session -> {
            IMessageDAO messageMapper = session.getMapper(IMessageDAO.class);
            return messageMapper.getById(id);
        });
    }

    @Override
    public List<Message> getAll() {
        return executeWithSession(session -> {
            IMessageDAO messageMapper = session.getMapper(IMessageDAO.class);
            return messageMapper.getAll();
        });
    }

    @Override
    public List<Message> getMessagesBySenderProfileId(long senderProfileId) {
        return executeWithSession(session -> {
            IMessageDAO messageMapper = session.getMapper(IMessageDAO.class);
            return messageMapper.getMessagesBySenderProfileId(senderProfileId);
        });
    }

    @Override
    public List<Message> getMessagesByReceiverProfileId(long receiverProfileId) {
        return executeWithSession(session -> {
            IMessageDAO messageMapper = session.getMapper(IMessageDAO.class);
            return messageMapper.getMessagesByReceiverProfileId(receiverProfileId);
        });
    }

    @Override
    public void insert(Message message, long senderProfileId, long receiverProfileId) {
        executeWithSession(session -> {
            IMessageDAO messageMapper = session.getMapper(IMessageDAO.class);
            messageMapper.insert(message, senderProfileId, receiverProfileId);
            return null;
        });
    }

    @Override
    public void update(Message message, long senderProfileId, long receiverProfileId) {
        executeWithSession(session -> {
            IMessageDAO messageMapper = session.getMapper(IMessageDAO.class);
            messageMapper.update(message, receiverProfileId, receiverProfileId);
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IMessageDAO messageMapper = session.getMapper(IMessageDAO.class);
            messageMapper.delete(id);
            return null;
        });
    }
}

