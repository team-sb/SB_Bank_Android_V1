package com.example.bank;

import com.google.gson.JsonObject;

import java.util.List;

public class LoanListResponse {
    List<JsonObject> loans;

    public List<JsonObject> getLoans() {
        return loans;
    }

    public void setLoans(List<JsonObject> loans) {
        this.loans = loans;
    }
}
