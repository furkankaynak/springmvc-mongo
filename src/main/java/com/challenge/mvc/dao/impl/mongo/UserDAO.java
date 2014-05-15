package com.challenge.mvc.dao.impl.mongo;

import com.challenge.mvc.dao.IUserDAO;
import com.challenge.mvc.entities.User;
import com.challenge.mvc.util.MongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by furkan on 5/13/14.
 */
public class UserDAO extends MongoDaoSupport implements IUserDAO {

    @Override
    public void init() {
        if (!getMongoTemplate().collectionExists(User.class)) {
            getMongoTemplate().createCollection(User.class);
        }
    }

    @Override
    public void save(final User user) {
        getMongoTemplate().save(user);
    }

    @Override
    public void delete(final String id) {
        getMongoTemplate().remove(new Query(Criteria.where("id").is(id)), User.class);
    }

    @Override
    public User findById(final String id) {
        return getMongoTemplate().findById(id, User.class);
    }

    @Override
    public List<User> findAll() {
        return getMongoTemplate().findAll(User.class);
    }
}
