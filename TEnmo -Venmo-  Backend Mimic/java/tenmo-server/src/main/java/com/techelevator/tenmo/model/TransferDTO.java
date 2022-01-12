package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {
    private int userFrom;
    private int userTo;
    private BigDecimal amount;

    public TransferDTO(int userFrom, int userTo, BigDecimal amount) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;
    }

    public TransferDTO() {
    }

    public int getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(int userFrom) {
        this.userFrom = userFrom;
    }

    public int getUserTo() {
        return userTo;
    }

    public void setUserTo(int userTo) {
        this.userTo = userTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", amount=" + amount +
                '}';
    }
}
