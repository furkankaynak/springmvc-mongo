package com.challenge.mvc.entities;

import com.challenge.mvc.util.Model;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by furkan on 5/13/14.
 */
@Document
public class User extends Model {

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @Pattern(regexp = "0[0-9]{10}")
    @Min(11)
    private String phone;

    public User(String name, String lastname, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
    }

    public User(String id, String name, String lastname, String phone) {
        super.setId(id);
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
