package com.catch_my_hand.backend.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "userTBL")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String userid;

    private String password;

    private String nicname;

    public User() {}

    public User(String name, String userid, String password, String nicname) {
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.nicname = nicname;
    }

}
