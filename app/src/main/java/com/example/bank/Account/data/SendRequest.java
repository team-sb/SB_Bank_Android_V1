package com.example.bank.Account.data;

import com.google.gson.annotations.SerializedName;

public class SendRequest {
    int target_account;
    int money;

    public SendRequest(int target_account, int money) {
        this.target_account = target_account;
        this.money = money;
    }

    public int getTarget_account() {
        return target_account;
    }

    public void setTarget_account(int target_account) {
        this.target_account = target_account;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
