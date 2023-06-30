package com.solvd.socialnetworkdao.strategies;

import com.solvd.socialnetworkdao.Message;

import java.util.List;

public class BubbleSortMessages implements MessageSortStrategy{
    @Override
    public void sort(List<Message> messages) {
        int n = messages.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (messages.get(j).getContent().length() > messages.get(j + 1).getContent().length()) {
                    Message temp = messages.get(j);
                    messages.set(j, messages.get(j + 1));
                    messages.set(j + 1, temp);
                }
            }
        }
    }
}
