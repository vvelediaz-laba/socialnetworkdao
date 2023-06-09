package com.solvd.socialnetworkdao.dao;

import java.sql.ResultSet;
import java.util.List;

public interface IDAO<T>{
    void insert(T t);

    T getById(long id);

    List<T> getAll();

    void update(T t);

    void delete(long id);
}
