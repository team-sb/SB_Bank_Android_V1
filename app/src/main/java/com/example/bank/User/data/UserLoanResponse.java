package com.example.bank.User.data;

import com.google.gson.JsonObject;

import java.util.List;

public class UserLoanResponse {
    private List<JsonObject> loans;

    public List<JsonObject> getLoans() {
        return loans;
    }

    public void setLoans(List<JsonObject> loans) {
        this.loans = loans;
    }
}
