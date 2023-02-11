package com.example.redis.bean;

import java.io.Serializable;

public class Studentcourse implements Serializable {
    private int id;
    public int cid;
    public int sid;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public int getSid() {
        return sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    
}
