package com.challenge.mvc.dao.impl.mongo;

import com.challenge.mvc.dao.IUserDAO;
import com.challenge.mvc.entities.User;
import com.challenge.mvc.util.MongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by furkan on 5/13/14.
 *
 * This class implementation of IUserDAO for MongoDB.
 * Class must extend from MongoDaoSupport and Implements with IUserDAO
 *
 * @see com.challenge.mvc.dao.IUserDAO
 * @see com.challenge.mvc.util.MongoDaoSupport
 */
public class UserDAO extends MongoDaoSupport implements IUserDAO {

    /**
     * This method runs when UserDAO initialize.
     * If User model not exist in MongoDB as a collection collection creates.
     *
     * @see com.challenge.mvc.entities.User
     */
    @Override
    public void init() {
        if (!getMongoTemplate().collectionExists(User.class)) {
            getMongoTemplate().createCollection(User.class);
        }
    }

    /**
     * This method insert user if user not exist in MongoDB User collection.
     * If given user exist in collection, this method update user.
     *
     * @param user
     * @see com.challenge.mvc.entities.User
     */
    @Override
    public void save(final User user) {
        getMongoTemplate().save(user);
    }

    /**
     * This method delete user from User collection if given id is right.
     *
     * @param id
     */
    @Override
    public void delete(final String id) {
        getMongoTemplate().remove(new Query(Criteria.where("id").is(id)), User.class);
    }

    /**
     *This method get user by id.
     *
     * @param id
     * @return User by the given id
     */
    @Override
    public User findById(final String id) {
        return getMongoTemplate().findById(id, User.class);
    }

    /**
     * Get all user in User collection.
     *
     * @return all user in User collection.
     */
    @Override
    public List<User> findAll() {
        return getMongoTemplate().findAll(User.class);
    }
}
