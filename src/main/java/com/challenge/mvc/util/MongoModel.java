package com.challenge.mvc.util;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by furkan on 5/13/14.
 */
public class MongoModel {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
