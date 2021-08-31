package com.example.bank.Auth.data;

import com.google.gson.annotations.SerializedName;

public class SecPasswordResponse {
    @SerializedName("token")
    private String secToken;

    public String getSecToken() {
        return secToken;
    }

    public void setSecToken(String secToken) {
        this.secToken = secToken;
    }
}
