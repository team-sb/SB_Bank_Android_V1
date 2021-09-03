package com.example.bank.Admin.data;

public class UserListData {
    String id;
    String name;
    String userName;
    String authority;

    public UserListData(String id, String name, String userName, String authority) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.authority = authority;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getAuthority() {
        return authority;
    }
}
