package com.example.bank.Account.data;

public class SendRequest {
    String target;
    int money;

    public SendRequest(String target, int money) {
        this.target = target;
        this.money = money;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
