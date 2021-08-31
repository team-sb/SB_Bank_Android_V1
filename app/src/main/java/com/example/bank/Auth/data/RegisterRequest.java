package com.example.bank.Auth.data;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("name")
    String name;
    @SerializedName("username")
    String username;
    @SerializedName("password")
    String password;
    @SerializedName("sec_password")
    String sec_password;

    public RegisterRequest(String name, String username, String password, String sec_password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.sec_password = sec_password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getSec_password() {
        return sec_password;
    }

    public void setSec_password(String sec_password) {
        this.sec_password = sec_password;
    }
}
