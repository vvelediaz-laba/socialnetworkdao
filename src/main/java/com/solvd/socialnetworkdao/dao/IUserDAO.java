package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.User;

public interface IUserDAO extends IDAO<User>{
    void insert(User user);
    void update(User user);
}
