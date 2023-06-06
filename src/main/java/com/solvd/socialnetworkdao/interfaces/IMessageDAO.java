package com.solvd.socialnetworkdao.interfaces;

import com.solvd.socialnetworkdao.Message;

import java.util.List;

public interface IMessageDAO extends IDAO<Message>{
    List<Message> getMessagesById(long id);
}
