package com.challenge.mvc.services;

import com.challenge.mvc.entities.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by furkan on 5/13/14.
 */
public interface IUserService {
    public void save(final User user);

    public void delete(final User user);

    public User findById(final String id);

    public List<User> findAll();
}
