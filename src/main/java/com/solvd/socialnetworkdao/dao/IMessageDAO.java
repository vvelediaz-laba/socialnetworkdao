package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.Message;

import java.util.List;

public interface IMessageDAO extends IDAO<Message>{
    List<Message> getMessagesByProfileId(long id);
}
