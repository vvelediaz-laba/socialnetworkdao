package com.solvd.socialnetworkdao.dao;

import java.util.List;

public interface IDAO<T>{
    T getById(long id);

    List<T> getAll();

    void delete(long id);
}
