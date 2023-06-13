package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.Profile;

import java.util.List;

public interface IService<T> {
    void insert(T t);
    T getById(long id);
    List<T> getAll();
    void update(T t);
    void delete(long id);
}