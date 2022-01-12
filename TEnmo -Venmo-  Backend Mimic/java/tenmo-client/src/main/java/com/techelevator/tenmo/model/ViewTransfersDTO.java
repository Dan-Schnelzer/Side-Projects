package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class ViewTransfersDTO {
    private int transferId;
    private BigDecimal amount;
    private String userTo;
    private String userFrom;


    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    @Override
    public String toString() {
        return "ViewTransfersDTO{" +
                "transferId=" + transferId +
                ", amount=" + amount +
                ", userTo='" + userTo + '\'' +
                ", userFrom='" + userFrom + '\'' +
                '}';
    }
}
