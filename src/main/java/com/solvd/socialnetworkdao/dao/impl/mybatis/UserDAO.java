package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.User;
import com.solvd.socialnetworkdao.dao.IUserDAO;

import java.util.List;

public class UserDAO extends MyBatisDAO implements IUserDAO {
    @Override
    public User getById(long id) {
        return executeWithSession(session -> {
            IUserDAO userMapper = session.getMapper(IUserDAO.class);
            return userMapper.getById(id);
        });
    }

    @Override
    public List<User> getAll() {
        return executeWithSession(session -> {
            IUserDAO userMapper = session.getMapper(IUserDAO.class);
            return userMapper.getAll();
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IUserDAO userMapper = session.getMapper(IUserDAO.class);
            userMapper.delete(id);
            return null;
        });
    }

    @Override
    public void insert(User user) {
        executeWithSession(session -> {
            IUserDAO userMapper = session.getMapper(IUserDAO.class);
            userMapper.insert(user);
            return null;
        });
    }

    @Override
    public void update(User user) {
        executeWithSession(session -> {
            IUserDAO userMapper = session.getMapper(IUserDAO.class);
            userMapper.update(user);
            return null;
        });
    }
}
