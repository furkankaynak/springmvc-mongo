package com.challenge.mvc.util;

import com.challenge.mvc.entities.User;

import java.util.List;

/**
 * Created by furkan on 5/13/14.
 *
 * This is an interface for DAOs. The interface is generic and generic class T should be extend from Model.
 * Method contains classic CRUD methods.
 *
 * @see T
 * @see com.challenge.mvc.util.Model
 */
public interface GenericCRUD<T extends Model> {

    /**
     * init method for any operations when DAO initialize.
     */
    public void init();

    /**
     * For save any given model (Insert or Update).
     *
     * @param t
     */
    public void save(final T t);

    /**
     * for delete any model with given id.
     *
     * @param id
     */
    public void delete(final String id);

    /**
     * for get the model with given id.
     *
     * @param id
     * @return T
     */
    public T findById(final String id);

    /**
     * for get all the model.
     * @return
     */
    public List<T> findAll();

}
