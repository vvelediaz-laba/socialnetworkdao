package com.solvd.socialnetworkdao.observers;

import com.solvd.socialnetworkdao.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageSender {
    private List<MessageObserver> observers = new ArrayList<>();
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
        notifyAllObservers();
    }

    public void attach(MessageObserver observer){
        observers.add(observer);
    }

    public void detach(MessageObserver observer){
        observers.remove(observer);
    }

    public void notifyAllObservers(){
        for (MessageObserver observer : observers) {
            observer.update(message);
        }
    }
}
