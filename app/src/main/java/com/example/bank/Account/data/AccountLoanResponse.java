package com.example.bank.Account.data;

import com.google.gson.annotations.SerializedName;

public class AccountLoanResponse {
    int money;
    float interest;
    String borrowedDate;
    String loanExpirationDate;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getLoanExpirationDate() {
        return loanExpirationDate;
    }

    public void setLoanExpirationDate(String loanExpirationDate) {
        this.loanExpirationDate = loanExpirationDate;
    }
}
