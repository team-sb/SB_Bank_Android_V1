package com.example.bank;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.List;

public class TransactionDetailsData {
    private String tv_transactionDate;
    private String tv_transactionName;
    private String tv_transactionTime;
    private String tv_transactionType;
    private String tv_transactionMoney;
    private String tv_transactionPrice;

    public TransactionDetailsData(String tv_transactionDate, String tv_transactionName, String tv_transactionTime, String tv_transactionType, String tv_transactionMoney, String tv_transactionPrice) {
        this.tv_transactionDate = tv_transactionDate;
        this.tv_transactionName = tv_transactionName;
        this.tv_transactionTime = tv_transactionTime;
        this.tv_transactionType = tv_transactionType;
        this.tv_transactionMoney = tv_transactionMoney;
        this.tv_transactionPrice = tv_transactionPrice;
    }

    public String getTv_transactionDate() {
        return tv_transactionDate;
    }

    public void setTv_transactionDate(String tv_transactionDate) {
        this.tv_transactionDate = tv_transactionDate;
    }

    public String getTv_transactionName() {
        return tv_transactionName;
    }

    public void setTv_transactionName(String tv_transactionName) {
        this.tv_transactionName = tv_transactionName;
    }

    public String getTv_transactionTime() {
        return tv_transactionTime;
    }

    public void setTv_transactionTime(String tv_transactionTime) {
        this.tv_transactionTime = tv_transactionTime;
    }

    public String getTv_transactionType() {
        return tv_transactionType;
    }

    public void setTv_transactionType(String tv_transactionType) {
        this.tv_transactionType = tv_transactionType;
    }

    public String getTv_transactionMoney() {
        return tv_transactionMoney;
    }

    public void setTv_transactionMoney(String tv_transactionMoney) {
        this.tv_transactionMoney = tv_transactionMoney;
    }

    public String getTv_transactionPrice() {
        return tv_transactionPrice;
    }

    public void setTv_transactionPrice(String tv_transactionPrice) {
        this.tv_transactionPrice = tv_transactionPrice;
    }
}
