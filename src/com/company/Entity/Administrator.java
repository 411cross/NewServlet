package com.company.Entity;

/**
 * Created by Administrator on 2017/6/29.
 */
public class Administrator {
    private int id;
    private String password;

    public Administrator(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
