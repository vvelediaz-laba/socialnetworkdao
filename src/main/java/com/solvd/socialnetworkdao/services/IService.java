package com.solvd.socialnetworkdao.services;

import java.util.List;

public interface IService<T> {
    void insert(T t);
    T getById(long id);
    List<T> getAll();
    void update(T t);
    void delete(long id);
    void setValues(T t);
}