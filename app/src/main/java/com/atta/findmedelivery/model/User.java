package com.atta.findmedelivery.model;

public class User {

    private int id;
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

}
