package com.solvd.socialnetworkdao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T>{
    void create(T t);

    T getById(long id);

    List<T> getAll();

    void update(T t);

    void delete(long id);
}
