package com.solvd.socialnetworkdao.strategies;

import com.solvd.socialnetworkdao.Message;

import java.util.List;

public class InsertionSortMessages implements MessageSortStrategy{
    @Override
    public void sort(List<Message> messages) {
        int n = messages.size();
        for (int i = 1; i < n; i++) {
            Message key = messages.get(i);
            int j = i - 1;
            while (j >= 0 && messages.get(j).getContent().length() > key.getContent().length()) {
                messages.set(j + 1, messages.get(j));
                j--;
            }
            messages.set(j + 1, key);
        }
    }
}
