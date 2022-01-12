package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class ViewTransfersDTO {


    private int transferId;
    private BigDecimal amount;
    private String userTo;
    private String userFrom;


    public ViewTransfersDTO() {
    }

    public ViewTransfersDTO(int transferId, BigDecimal amount, String userTo, String userFrom) {
        this.transferId = transferId;
        this.amount = amount;
        this.userTo = userTo;
        this.userFrom = userFrom;
    }

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
}
