package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.Message;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.dao.IMessageDAO;
import com.solvd.socialnetworkdao.dao.IProfileDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.MessageDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileDAO;
import com.solvd.socialnetworkdao.services.IMessageService;
import com.solvd.socialnetworkdao.services.IProfileService;

import java.util.List;

public class MessageService implements IMessageService {
    private final IMessageDAO messageDAO = new MessageDAO();
    private final IProfileDAO profileDAO = new ProfileDAO();
    private final IProfileService profileService = new ProfileService();

    @Override
    public void insert(Message message) {
        messageDAO.insert(message);
    }

    @Override
    public Message getById(long id) {
        Message message = messageDAO.getById(id);
        setValues(message);
        return message;
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = messageDAO.getAll();
        for(Message message : messages){
            setValues(message);
        }
        return messages;
    }

    @Override
    public void update(Message message) {
        messageDAO.update(message);
    }

    @Override
    public void delete(long id) {
        messageDAO.delete(id);
    }

    public void setValues(Message message){
        Profile senderProfile = profileDAO.getSenderByMessageId(message.getId());
        profileService.setValues(senderProfile);

        Profile receiverProfile = profileDAO.getReceiverByMessageId(message.getId());
        profileService.setValues(receiverProfile);

        message.setSender(senderProfile);
        message.setReceiver(receiverProfile);
    }
}
