package com.challenge.mvc.services.impl;

import com.challenge.mvc.dao.IUserDAO;
import com.challenge.mvc.entities.User;
import com.challenge.mvc.services.IUserService;

import java.util.List;

/**
 * Created by furkan on 5/13/14.
 *
 * This class implementation of IUserService.
 * Class must implements with IUserService
 *
 * @see com.challenge.mvc.services.IUserService
 */
public class UserService implements IUserService {

    /**
     * This field abstraction for UserDAO. Injected any UserDAO impl by SpringDI
     *
     * @UserDAO will be inject here.
     * @see com.challenge.mvc.dao.impl.mongo.UserDAO
     */
    private IUserDAO userDAO;

    /**
     * This method call save method from IUserDAO
     *
     * @param user
     */
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    /**
     * This method call delete method from IUserDAO
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        userDAO.delete(id);
    }

    /**
     * This method call findById method from IUserDAO
     *
     * @param id
     * @return
     */
    @Override
    public User findById(String id) {
        return userDAO.findById(id);
    }

    /**
     * This method call findAll method from IUserDAO
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
