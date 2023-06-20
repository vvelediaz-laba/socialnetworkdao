package com.solvd.socialnetworkdao.mapper;

import com.solvd.socialnetworkdao.User;
import java.util.List;

public interface UserMapper {
    User selectUserById(long id);

    void insertUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);
}
