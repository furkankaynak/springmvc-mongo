package com.challenge.mvc.services.impl;

import com.challenge.mvc.dao.IUserDAO;
import com.challenge.mvc.entities.User;
import com.challenge.mvc.services.IUserService;

import java.util.List;

/**
 * Created by furkan on 5/13/14.
 */
public class UserService implements IUserService {

    private IUserDAO userDAO;

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public User findById(String id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
