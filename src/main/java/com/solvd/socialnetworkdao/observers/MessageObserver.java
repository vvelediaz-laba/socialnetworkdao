package com.solvd.socialnetworkdao.observers;

import com.solvd.socialnetworkdao.Message;

public abstract class MessageObserver {
    protected MessageSender messageSender;
    public abstract void update(Message message);
}
