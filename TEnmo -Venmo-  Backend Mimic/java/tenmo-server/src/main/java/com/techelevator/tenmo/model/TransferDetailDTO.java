package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDetailDTO {
    private int transferId;
    private String transferStatusDesc;
    private String transferTypeDesc;
    private BigDecimal amount;
    private String userTo;
    private String userFrom;

    public TransferDetailDTO(int transferId, String transferStatusDesc, String transferTypeDesc, BigDecimal amount, String userTo, String userFrom) {
        this.transferId = transferId;
        this.transferStatusDesc = transferStatusDesc;
        this.transferTypeDesc = transferTypeDesc;
        this.amount = amount;
        this.userTo = userTo;
        this.userFrom = userFrom;
    }

    public TransferDetailDTO() {
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getTransferStatusDesc() {
        return transferStatusDesc;
    }

    public void setTransferStatusDesc(String transferStatusDesc) {
        this.transferStatusDesc = transferStatusDesc;
    }

    public String getTransferTypeDesc() {
        return transferTypeDesc;
    }

    public void setTransferTypeDesc(String transferTypeDesc) {
        this.transferTypeDesc = transferTypeDesc;
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
        return "TransferDetailDTO{" +
                "transferId=" + transferId +
                ", transferStatusDesc='" + transferStatusDesc + '\'' +
                ", transferTypeDesc='" + transferTypeDesc + '\'' +
                ", amount=" + amount +
                ", userTo='" + userTo + '\'' +
                ", userFrom='" + userFrom + '\'' +
                '}';
    }
}
