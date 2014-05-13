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
    private List<String> phones;

    public User(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public User(String name, String lastname, List<String> phones) {
        this.name = name;
        this.lastname = lastname;
        this.phones = phones;
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

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

}
