package com.example.bank;

import com.google.gson.JsonObject;

import java.util.List;

public class MainTransactionSendResponse {
    List<JsonObject> transactions;

    public List<JsonObject> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<JsonObject> transactions) {
        this.transactions = transactions;
    }
}
