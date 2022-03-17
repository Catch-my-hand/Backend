package com.catch_my_hand.backend.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNicname() {
        return nicname;
    }

    public void setNicname(String nicname) {
        this.nicname = nicname;
    }
}
