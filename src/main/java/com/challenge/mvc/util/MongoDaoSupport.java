package com.challenge.mvc.util;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by furkan on 5/13/14.
 */
public class MongoDaoSupport {

    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
