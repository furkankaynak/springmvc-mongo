package com.challenge.mvc.util;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by furkan on 5/13/14.
 *
 * This class supports Mongo DAO class.
 */
public class MongoDaoSupport {

    /**
     * Mongo template required for mongo operations.
     */
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
