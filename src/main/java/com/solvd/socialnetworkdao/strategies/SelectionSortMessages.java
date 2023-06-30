package com.solvd.socialnetworkdao.strategies;

import com.solvd.socialnetworkdao.Message;

import java.util.List;

public class SelectionSortMessages implements MessageSortStrategy{
    @Override
    public void sort(List<Message> messages) {
        int n = messages.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (messages.get(j).getContent().length() < messages.get(minIndex).getContent().length()) {
                    minIndex = j;
                }
            }
            Message temp = messages.get(i);
            messages.set(i, messages.get(minIndex));
            messages.set(minIndex, temp);
        }
    }
}
