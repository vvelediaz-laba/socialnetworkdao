package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Message;
import java.util.List;

public interface IMessageDAO extends IDAO<Message>{
    void insert(Message message, long senderProfileId, long receiverProfileId);
    void update(Message message, long senderProfileId, long receiverProfileId);
    List<Message> getMessagesBySenderProfileId(long id);
    List<Message> getMessagesByReceiverProfileId(long id);
}
