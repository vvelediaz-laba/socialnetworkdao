package com.solvd.socialnetworkdao.proxy;

import com.solvd.socialnetworkdao.Message;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NoDuplicateMessageListProxy implements List<Message>{
    private List<Message> list;

    public NoDuplicateMessageListProxy(List<Message> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<Message> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(Message message) {
        for(Message m : list){
            if(m.getId().equals(message.getId())){
                return false;
            }
        }
        return list.add(message);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Message> c) {
        boolean modified = false;
        for (Message message : c) {
            if (!containsId(message.getId())) {
                list.add(message);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Message> c) {
        int insertionIndex = index;
        boolean modified = false;
        for (Message message : c) {
            if (!containsId(message.getId())) {
                list.add(insertionIndex++, message);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Message get(int index) {
        return list.get(index);
    }

    @Override
    public Message set(int index, Message element) {
        if (!containsId(element.getId())) {
            return list.set(index, element);
        } else {
            return list.get(index);
        }
    }

    @Override
    public void add(int index, Message element) {
        if (!containsId(element.getId())) {
            list.add(index, element);
        }
    }

    @Override
    public Message remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<Message> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<Message> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<Message> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    private boolean containsId(Long id) {
        for (Message message : list) {
            if (message.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
