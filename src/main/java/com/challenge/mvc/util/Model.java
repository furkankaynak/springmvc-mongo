package com.challenge.mvc.util;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by furkan on 5/13/14.
 *
 * This method is a base class for DB models.
 */
public class Model {

    /**
     * This variable is id of the User and identify for user.
     *
     */
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
