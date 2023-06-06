package com.solvd.socialnetworkdao.services;

import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.User;
import com.solvd.socialnetworkdao.dao.ProfileDAO;
import com.solvd.socialnetworkdao.dao.UserDAO;

public class UserService {
    public User getUser(long id){
        User user = new UserDAO().getById(id);
        user.setProfile(new ProfileDAO().getByUserId(id));
        return user;
    }
}
