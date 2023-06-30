package com.solvd.socialnetworkdao.decorators;

import com.solvd.socialnetworkdao.Message;
import com.solvd.socialnetworkdao.Profile;

import java.util.*;

public class MessageListDecorator implements List<Message> {
    private List<Message> decoratedList;

    public MessageListDecorator(List<Message> list) {
        this.decoratedList = list;
    }

    @Override
    public int size() {
        return decoratedList.size();
    }

    @Override
    public boolean isEmpty() {
        return decoratedList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return decoratedList.contains(o);
    }

    @Override
    public Iterator<Message> iterator() {
        return decoratedList.iterator();
    }

    @Override
    public Object[] toArray() {
        return decoratedList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return decoratedList.toArray(a);
    }

    @Override
    public boolean add(Message message) {
        return decoratedList.add(message);
    }

    @Override
    public boolean remove(Object o) {
        return decoratedList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return decoratedList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Message> c) {
        return decoratedList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Message> c) {
        return decoratedList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return decoratedList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return decoratedList.retainAll(c);
    }

    @Override
    public void clear() {
        decoratedList.clear();
    }

    @Override
    public Message get(int index) {
        return decoratedList.get(index);
    }

    @Override
    public Message set(int index, Message element) {
        return decoratedList.set(index, element);
    }

    @Override
    public void add(int index, Message element) {
        decoratedList.add(index, element);
    }

    @Override
    public Message remove(int index) {
        return decoratedList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return decoratedList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return decoratedList.lastIndexOf(o);
    }

    @Override
    public ListIterator<Message> listIterator() {
        return decoratedList.listIterator();
    }

    @Override
    public ListIterator<Message> listIterator(int index) {
        return decoratedList.listIterator(index);
    }

    @Override
    public List<Message> subList(int fromIndex, int toIndex) {
        return decoratedList.subList(fromIndex, toIndex);
    }

    public List<Message> filterReceivedMessagesByProfile(Profile profile){
        List<Message> filteredList = new ArrayList<>(decoratedList);
        filteredList.retainAll(profile.getOutgoingMessages());
        return filteredList;
    }

    public List<Message> filterSentMessagesByProfile(Profile profile){
        List<Message> filteredList = new ArrayList<>(decoratedList);
        filteredList.retainAll(profile.getIncomingMessages());
        return filteredList;
    }
}
