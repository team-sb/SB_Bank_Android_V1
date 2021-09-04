package com.example.bank.Account.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AccountLoanResponse {
    @SerializedName("money")
    private Integer money;

    @SerializedName("interest")
    private double interest;

    @SerializedName("borrowedDate")
    private String borrowedDate;

    @SerializedName("loanExpirationDate")
    private String loanExpirationDate;

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
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
