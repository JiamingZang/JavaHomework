package com.example.redis.bean;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String password;
    private String username;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    
}

