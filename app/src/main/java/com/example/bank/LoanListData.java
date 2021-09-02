package com.example.bank;

public class LoanListData {
    String money;
    String interest;
    String borrowedDate;
    String loanExpirationDate;

    public LoanListData(String money, String interest, String borrowedDate, String loanExpirationDate) {
        this.money = money;
        this.interest = interest;
        this.borrowedDate = borrowedDate;
        this.loanExpirationDate = loanExpirationDate;
    }

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
