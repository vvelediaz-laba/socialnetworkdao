package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.User;
import com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.UserDAO;
import com.solvd.socialnetworkdao.services.IService;

import java.util.List;

public class UserService implements IService<User> {
    private final UserDAO userDAO = new UserDAO();
    private final ProfileDAO profileDAO = new ProfileDAO();
    private final ProfileService profileService = new ProfileService();

    @Override
    public void insert(User user) {
        userDAO.insert(user);
    }

    public User getById(long id){
        User user = userDAO.getById(id);
        setValues(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userDAO.getAll();
        for(User user : users){
            setValues(user);
        }
        return users;
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }


    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    private void setValues(User user){
        Profile profile = profileDAO.getByUserId(user.getId());
        profile = profileService.getById(profile.getId());
        user.setProfile(profile);
    }
}
