package com.challenge.mvc.entities;

import com.challenge.mvc.util.MongoModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by furkan on 5/13/14.
 */
@Document
public class User extends MongoModel {

    private String name;
    private String lastname;

    private String phone;

    public User(String name, String lastname, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
