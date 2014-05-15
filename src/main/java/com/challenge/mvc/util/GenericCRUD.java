package com.challenge.mvc.util;

import com.challenge.mvc.entities.User;

import java.util.List;

/**
 * Created by furkan on 5/13/14.
 */
public interface GenericCRUD<T extends Model> {

    public void init();

    public void save(final User user);

    public void delete(final String id);

    public T findById(final String id);

    public List<T> findAll();

}
