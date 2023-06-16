package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.Profile;

import java.util.List;

public interface IService<T> {
    T getById(long id);
    List<T> getAll();
    void delete(long id);
}