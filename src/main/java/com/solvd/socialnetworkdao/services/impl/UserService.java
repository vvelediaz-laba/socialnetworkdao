package com.solvd.socialnetworkdao.services.impl;

import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.User;
import com.solvd.socialnetworkdao.dao.IProfileDAO;
import com.solvd.socialnetworkdao.dao.IUserDAO;
import com.solvd.socialnetworkdao.services.IProfileService;
import com.solvd.socialnetworkdao.services.IService;

import java.util.List;

public class UserService implements IService<User> {
    private IUserDAO userDAO;
    private IProfileDAO profileDAO;
    private IProfileService profileService;

    public UserService(IUserDAO userDAO, IProfileDAO profileDAO, IProfileService profileService) {
        this.userDAO = userDAO;
        this.profileDAO = profileDAO;
        this.profileService = profileService;
    }

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

    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public IProfileDAO getProfileDAO() {
        return profileDAO;
    }

    public void setProfileDAO(IProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    public IProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    private void setValues(User user){
        Profile profile = profileDAO.getByUserId(user.getId());
        profile = profileService.getById(profile.getId());
        user.setProfile(profile);
    }
}
