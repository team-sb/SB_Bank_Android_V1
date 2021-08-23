package com.example.bank;

public class MainTranscationResponse {
    String member_id;
    String target;
    String money;
    String transaction_date;
    String transaction_type;
    String bf_balance;
    String aft_balance;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getBf_balance() {
        return bf_balance;
    }

    public void setBf_balance(String bf_balance) {
        this.bf_balance = bf_balance;
    }

    public String getAft_balance() {
        return aft_balance;
    }

    public void setAft_balance(String aft_balance) {
        this.aft_balance = aft_balance;
    }
}
