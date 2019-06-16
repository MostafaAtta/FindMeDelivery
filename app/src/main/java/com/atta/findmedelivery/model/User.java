package com.atta.findmedelivery.model;

public class User {

    private int id, type;
    private String username, password, phone;


    public User(int id, String username, String password, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }


    public User( String username, String password, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public User(int id, int type, String username, String password, String phone) {
        this.id = id;
        this.type = type;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getType() {
        return type;
    }
}
