package com.solvd.socialnetworkdao.strategies;

import com.solvd.socialnetworkdao.Message;

import java.util.List;

public interface MessageSortStrategy {
    void sort(List<Message> messages);
}
