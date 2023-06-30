package com.solvd.socialnetworkdao.dao;

import com.solvd.socialnetworkdao.User;

import java.util.List;

public interface IUserDAO extends IDAO<User>{
    void insert(User user);
    void update(User user);
}
