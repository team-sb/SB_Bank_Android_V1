package com.example.bank;

public class AccountBorrowResponse {
    String money;
    String interest;
    String borrowExpirationDate;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getBorrowExpirationDate() {
        return borrowExpirationDate;
    }

    public void setBorrowExpirationDate(String borrowExpirationDate) {
        this.borrowExpirationDate = borrowExpirationDate;
    }
}
