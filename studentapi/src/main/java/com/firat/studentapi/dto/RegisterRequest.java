package com.firat.studentapi.dto;


public class RegisterRequest {

    private String username;
    private String password;

    // boş constructor (Spring için şart)
    public RegisterRequest() {}

    // getter - setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

