package com.example.bank.Admin.data;

import com.google.gson.JsonObject;

import java.util.List;

public class UserListResponse {
    List<JsonObject> usersList;

    public List<JsonObject> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<JsonObject> usersList) {
        this.usersList = usersList;
    }
}
